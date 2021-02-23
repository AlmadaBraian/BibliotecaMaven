package mavenProyecto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;




import Excepciones.CopiaYaAlquiladaException;
import Excepciones.LectorExcedeAlquileresException;
import Excepciones.LectorIdException;
import Excepciones.LectorMultaException;
import jakarta.xml.ws.Endpoint;
import servicios.LectorServices;

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
		
		//Copia copia = new Copia();
		
		Date inicio = dateFormat.parse("16-02-2021");
		Date fin = dateFormat.parse("16-03-2021");
		
		
		multa.setfFin(fin);
		multa.setfInicio(inicio);
		
		l.setDireccion("Velez Sarfield 5840");
		l.setNombre("Roberto Gomez");
		l.setTelefono("47369501");
		
		
		
		autor.setNacimiento(nacimiento);
		autor.setNacionalidad("Frances");
		autor.setNombreAutor("Arturo Puig");
		
		
		libro.setAño(2005);
		libro.setEditorial("planeta");
		libro.setNombre("Frutos de su tiempo");
		libro.setTipo(LibroTipo.ENSAYO);
		
		libro.setEstado(estadoCopia.BIBLIOTECA);
		
		//libro.setId((long) 17);
		libro.setAutor(autor);
		
		autor.pushlibro(libro);
		
		//b.pushCopia(libro);
		
		Endpoint endPoint = Endpoint.publish("http://localhost:8080/LectorServices", new LectorServices());

	
		//List<Lector>lectores = b.findWithName("Braian Almada");
		
		//System.out.println(lectores.get(0).getNombre());
		/**
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ejsHibernate");
		
		EntityManager em = managerFactory.createEntityManager();
		EntityTransaction tran = em.getTransaction();
		
		tran.begin();
		em.persist(l);
		tran.commit();
		em.close();**/

		/**
		b.pushLectores(l);
		b.stock();
		
		Copia c = new Copia();
		c.setEstado(libro.getEstado());
		c.setId(libro.getId());
		
		b.alquilar(l,c);
		
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

	**/
	}
}
