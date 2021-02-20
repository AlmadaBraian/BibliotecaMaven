package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Excepciones.CopiaYaAlquiladaException;
import Excepciones.LectorExcedeAlquileresException;
import Excepciones.LectorIdException;
import Excepciones.LectorMultaException;
import mavenProyecto.Biblioteca;
import mavenProyecto.Copia;
import mavenProyecto.Lector;
import mavenProyecto.LibroTipo;

class BibliotecaTest {
	Biblioteca<Copia> b = new Biblioteca<Copia>();
	Lector l = new Lector(518, "Bartolo Gimenez", "47369502", "Darragueira 5840");
	Lector l2 = new Lector(519, "Braian Almada", "47369502", "Darragueira 5840");
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	@BeforeEach
	void setUp() throws Exception {

		
		b.pushLectores(l);
		b.pushLectores(l2);
		
	}

	@Test
	final void testPrestarCuatro() throws CopiaYaAlquiladaException{
		try {
			b.alquilar(l.getnSocio(),b.obtenerCopia(15));
			b.alquilar(l.getnSocio(),b.obtenerCopia(16));
			b.alquilar(l.getnSocio(),b.obtenerCopia(17));
			b.alquilar(l.getnSocio(),b.obtenerCopia(18));
			
		} catch (ParseException e) {
			fail("Error de test - Fecha mal formada");
		}catch(LectorMultaException e){
			assertTrue(true);
			
		} catch (LectorIdException e) {
			
			fail("Error de test - id lector formada");
		}catch (LectorExcedeAlquileresException e) {
			fail("alquiler maximo alcanzado");
			
		}

	
	}
	
    @Test
    public void testPedirYDevolver() throws ParseException, LectorIdException, LectorExcedeAlquileresException, CopiaYaAlquiladaException {
        //Pedir 3, devolver 1 y pedir 1 de nuevo
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    	Date pas;
		pas = dateFormat.parse("15-03-2021");
		Lector tmp = b.obtenerLector(l2.getnSocio());
		
    	
		try {
			b.alquilar(tmp.getnSocio(),b.obtenerCopia(26));
			b.alquilar(tmp.getnSocio(),b.obtenerCopia(15));
			b.alquilar(tmp.getnSocio(),b.obtenerCopia(17));
			
		}catch (ParseException e) {
			
			fail("fecha mal formada");
		}catch ( LectorMultaException e) {
			fail("Lector multado");
		}catch(LectorIdException e) {
			fail("id lector no valida");
		}catch (LectorExcedeAlquileresException e) {
			fail(e);
			
		}
		
		b.regresar(tmp.getnSocio(), 1, new Date());


    }
	
    @Test
    public void testPedirConMulta() throws ParseException, NullPointerException, LectorIdException, CopiaYaAlquiladaException{
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    	Date pas;
		pas = dateFormat.parse("15-03-2021");
		Lector tmp = b.obtenerLector(l.getnSocio());
    	
        Copia c = this.b.stock().iterator().next();
       
        try {
        	b.alquilar(l.getnSocio(),c);
        	b.regresar(tmp.getnSocio(), 0, pas);
        	b.alquilar(l.getnSocio(),c);
        	
		} catch (ParseException e) {
			fail("fecha mal formada");
		}catch ( LectorMultaException e) {
			fail("Lector multado");
		}catch(LectorIdException e) {
			fail("id lector no valida");
		}catch (LectorExcedeAlquileresException e) {
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
			b.alquilar(tmp.getnSocio(),b.obtenerCopia(26));
			b.alquilar(tmp.getnSocio(),b.obtenerCopia(15));
			b.alquilar(tmp.getnSocio(),b.obtenerCopia(17));
			
		}catch (ParseException e) {
			
			fail("fecha mal formada");
		}catch ( LectorMultaException e) {
			fail("Lector multado");
		}catch(LectorIdException e) {
			fail("id lector no valida");
		}catch (LectorExcedeAlquileresException e) {
			fail(e);
			
		}catch (CopiaYaAlquiladaException e) {
			fail("Copia ya alquilada");
		}
		
		try {
			b.alquilar(tmp2.getnSocio(),b.obtenerCopia(17));
			
		}catch (ParseException e) {
			fail("fecha mal formada");
			
		}catch ( LectorMultaException e) {
			
			fail("Lector multado");
			
		}catch(LectorIdException e) {
			
			fail("id lector no valida");
			
		}catch (LectorExcedeAlquileresException e) {
			
			fail(e);
			
		}catch (CopiaYaAlquiladaException e) {
			fail("Copia ya alquilada");
		}
    }
       

}
