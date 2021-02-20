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
		
		Lector l = new Lector();
		Lector l2 = new Lector();
		
		Biblioteca<Copia> b = new Biblioteca<Copia>();
		
		Autor autor = new Autor();
		Libro libro = new Libro();
		
		Multa multa = new Multa();
		
		Prestamo p = new Prestamo();
		
		Date inicio = dateFormat.parse("16-02-2021");
		Date fin = dateFormat.parse("16-03-2021");
		
		
		multa.setfFin(fin);
		multa.setfInicio(inicio);
		
		l.setDireccion("Darragueira 5840");
		l.setNombre("Braian Almada");
		l.setTelefono("47369501");
		
		
		
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
		
		p.setFin(fin);
		p.setInicio(inicio);
		p.setLector(l);
		p.setCopia(libro);
		
		multa.setLector(l);
		
		l.agregarPrestamo(p);
		l.setMulta(multa);
		

		
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ejsHibernate");
		
		EntityManager em = managerFactory.createEntityManager();
		EntityTransaction tran = em.getTransaction();
		tran.begin();
		em.persist(l);
		em.persist(p);
		em.persist(multa);
		tran.commit();
		em.close();

		
		b.pushLectores(l);
		b.pushLectores(l2);
		b.stock();
		

		
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
