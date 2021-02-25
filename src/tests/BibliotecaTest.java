package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Excepciones.CopiaYaAlquiladaException;
import Excepciones.LectorExcedeAlquileresException;
import Excepciones.LectorIdException;
import Excepciones.LectorMultaException;
import Excepciones.PrestamoExeption;
import mavenProyecto.Autor;
import mavenProyecto.Biblioteca;
import mavenProyecto.Copia;
import mavenProyecto.Lector;
import mavenProyecto.Libro;
import mavenProyecto.LibroTipo;
import mavenProyecto.Prestamo;
import mavenProyecto.estadoCopia;

class BibliotecaTest {
	Biblioteca<Copia> b = new Biblioteca<Copia>();
	Lector l = new Lector();
	Lector l2 = new Lector();
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	@BeforeEach
	void setUp() throws Exception {
		Date nacimiento = dateFormat.parse("10-01-1993");
		Autor autor = new Autor();
		autor.setNacimiento(nacimiento);
		autor.setNacionalidad("Frances");
		autor.setNombreAutor("Arturo Puig");
		
		Libro libro = new Libro();
		libro.setAño(2005);
		libro.setEditorial("planeta");
		libro.setNombre("Frutos de su tiempo");
		libro.setTipo(LibroTipo.ENSAYO);
		
		libro.setEstado(estadoCopia.BIBLIOTECA);
		
		libro.setAutor(autor);
		
		libro.setId((long) 15);
		
		b.pushCopia(libro);
		
		Libro libro2 = new Libro();
		libro2.setAño(2005);
		libro2.setEditorial("planeta");
		libro2.setNombre("Frutos de su tiempo");
		libro2.setTipo(LibroTipo.ENSAYO);
		
		libro2.setEstado(estadoCopia.BIBLIOTECA);
		
		libro2.setAutor(autor);
		
		libro2.setId((long) 16);
		
		b.pushCopia(libro2);
		
		Libro libro3 = new Libro();
		libro3.setAño(2005);
		libro3.setEditorial("planeta");
		libro3.setNombre("Frutos de su tiempo");
		libro3.setTipo(LibroTipo.ENSAYO);
		
		libro3.setEstado(estadoCopia.BIBLIOTECA);
		
		libro3.setAutor(autor);
		
		libro3.setId((long) 17);
		
		b.pushCopia(libro3);
		
		Libro libro4 = new Libro();
		libro4.setAño(2005);
		libro4.setEditorial("planeta");
		libro4.setNombre("Frutos de su tiempo");
		libro4.setTipo(LibroTipo.ENSAYO);
		
		libro4.setEstado(estadoCopia.BIBLIOTECA);
		
		libro4.setAutor(autor);
		
		libro4.setId((long) 18);
		
		b.pushCopia(libro4);
		
		b.pushLectores(l);
		b.pushLectores(l2);
		
	}

	@Test
	final void testPrestarCuatro() throws CopiaYaAlquiladaException{
		try {
			b.alquilar(l,b.obtenerCopia(15));
			b.alquilar(l,b.obtenerCopia(16));
			b.alquilar(l,b.obtenerCopia(17));
			b.alquilar(l,b.obtenerCopia(18));
			
		} catch (ParseException e) {
			fail("Error de test - Fecha mal formada");
		}catch(LectorMultaException e){
			assertTrue(true);
			
		} catch (LectorIdException e) {
			
			fail("Error de test - id lector formada");
		}catch (LectorExcedeAlquileresException e) {
			
		}

	
	}
	
    @Test
    public void testPedirYDevolver() throws ParseException, LectorIdException, LectorExcedeAlquileresException, CopiaYaAlquiladaException, PrestamoExeption {
        //Pedir 3, devolver 1 y pedir 1 de nuevo
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    	Date pas;
		pas = dateFormat.parse("15-03-2021");
		Lector tmp = b.obtenerLector(l2.getnSocio());

		try {
			b.alquilar(tmp,b.obtenerCopia(16));
			b.alquilar(tmp,b.obtenerCopia(15));
			b.alquilar(tmp,b.obtenerCopia(17));
			
		}catch (ParseException e) {
			
			fail("fecha mal formada");
		}catch ( LectorMultaException e) {
			fail("Lector multado");
		}catch(LectorIdException e) {
			fail("id lector no valida");
		}catch (LectorExcedeAlquileresException e) {
			fail(e);
			
		}
		
		b.regresar(tmp.getnSocio(), tmp.getPrestamos().get(0), new Date());


    }
	
    @Test
    public void testPedirConMulta() throws ParseException, NullPointerException, LectorIdException, CopiaYaAlquiladaException, PrestamoExeption{
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    	Date pas;
		pas = dateFormat.parse("15-03-2021");
		Lector tmp = b.obtenerLector(l.getnSocio());

        Copia c = this.b.stock().iterator().next();
        
        
       
        try {
        	b.alquilar(l,c);
        	List<Prestamo>prestamos = tmp.getPrestamos();
        	b.regresar(tmp.getnSocio(), prestamos.get(0), pas);
        	b.alquilar(l,c);
        	
		} catch (ParseException e) {
			fail("fecha mal formada");
		}catch ( LectorMultaException e) {
			
		}catch(LectorIdException e) {
			fail("id lector no valida");
		}catch (LectorExcedeAlquileresException e) {
			fail("alquiler maximo alcanzado");
			
		}catch(CopiaYaAlquiladaException e){
			fail("alquiler maximo alcanzado");
		}
	}
    
    @Test
    public void testPedirCopiaAlquilada() throws ParseException, LectorIdException{
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    	Date pas;
		pas = dateFormat.parse("15-03-2021");
		Lector tmp = b.obtenerLector(l.getnSocio());
		Lector tmp2 = b.obtenerLector(l2.getnSocio());
		
    	
		try {
			b.alquilar(tmp,b.obtenerCopia(16));
			b.alquilar(tmp,b.obtenerCopia(15));
			b.alquilar(tmp,b.obtenerCopia(17));
			
		}catch (ParseException e) {
			
			fail("fecha mal formada");
		}catch ( LectorMultaException e) {
			fail("Lector multado");
		}catch(LectorIdException e) {
			fail("id lector no valida");
		}catch (LectorExcedeAlquileresException e) {
			fail(e);
			
		}catch (CopiaYaAlquiladaException e) {
			
		}
		
		try {
			b.alquilar(tmp2,b.obtenerCopia(17));
			
		}catch (ParseException e) {
			fail("fecha mal formada");
			
		}catch ( LectorMultaException e) {
			
			fail("Lector multado");
			
		}catch(LectorIdException e) {
			
			fail("id lector no valida");
			
		}catch (LectorExcedeAlquileresException e) {
			
			fail(e);
			
		}catch (CopiaYaAlquiladaException e) {
		}
    }
       

}
