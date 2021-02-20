package mavenProyecto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import Excepciones.CopiaYaAlquiladaException;
import Excepciones.LectorExcedeAlquileresException;
import Excepciones.LectorIdException;
import Excepciones.LectorMultaException;

public class Test {

	public static void main(String[] args) throws ParseException, LectorMultaException, LectorIdException, LectorExcedeAlquileresException, CopiaYaAlquiladaException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		Date pas = dateFormat.parse("15-03-2021");
		Date nacimiento = dateFormat.parse("10-01-1993");
		
		Lector l = new Lector(518, "Bartolo Gimenez", "47369502", "Darragueira 5840");
		Lector l2 = new Lector(519, "Braian Almada", "47369502", "Darragueira 5840");
		
		Biblioteca<Copia> b = new Biblioteca<Copia>();
		Autor autor = new Autor();
		Libro libro = new Libro();
		
		autor.setNacimiento(nacimiento);
		autor.setNacionalidad("Frances");
		autor.setNombreAutor("Arturo Puig");
		
		
		libro.setAño(2005);
		libro.setEditorial("planeta");
		libro.setNombre("Frutos de su tiempo");
		libro.setTipo(LibroTipo.ENSAYO);
		
		libro.setEstado(estadoCopia.BIBLIOTECA);
		
		libro.setAutor(autor);
		
		autor.pushlibro(libro);

		
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ejsHibernate");
		
		EntityManager em = managerFactory.createEntityManager();
		EntityTransaction tran = em.getTransaction();
		tran.begin();
		em.persist(libro);
		em.persist(autor);
		tran.commit();
		em.close();

		
		b.pushLectores(l);
		b.pushLectores(l2);
		b.stock();
		
		b.alquilar(l.getnSocio(),b.obtenerCopia(26));
		b.alquilar(l.getnSocio(),b.obtenerCopia(15));
		b.alquilar(l.getnSocio(),b.obtenerCopia(16));
		
		b.alquilar(l2.getnSocio(),b.obtenerCopia(18));
		b.alquilar(l2.getnSocio(),b.obtenerCopia(17));

		
		System.out.println(b.stockString());
		
		Lector tmp = b.obtenerLector(l.getnSocio()); 
		Lector tmp2 = b.obtenerLector(l2.getnSocio()); 

		b.regresar(tmp.getnSocio(), 0, pas);
		b.regresar(tmp2.getnSocio(), 1, pas);
		
		System.out.println(b.obtenerMultaLector(tmp.getnSocio()));
		
		System.out.println("\n");
		
		System.out.println(b.stockString());
		
		System.out.println("\n");
		
		//b.alquilar(tmp.getnSocio(),b.obtenerCopia(18));
		
		System.out.println("\n");
		
		System.out.println("Stock libros: \n"+b.stockLibrosString());
		
		for (Prestamo pres : b.getPrestamos()) {
			System.out.println(pres);
		}
		
		for ( Multa m : b.getMultas()) {
			System.out.println(m.toString()+"\n");
		}

	
	}

}
