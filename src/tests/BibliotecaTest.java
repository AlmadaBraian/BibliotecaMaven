package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
		b.pushCopias(new Copia("Arturo Puig", "Frances", dateFormat.parse("10-01-1993"), "Frutos de su tiempo", LibroTipo.ENSAYO, 2005, "Editorial Planeta",15));
		b.pushCopias(new Copia("Arturo Puig", "Frances", dateFormat.parse("10-01-1993"), "Frutos de su tiempo", LibroTipo.ENSAYO, 2005, "Editorial Planeta",16));
		b.pushCopias(new Copia("Arturo Puig", "Frances", dateFormat.parse("10-01-1993"), "Frutos de su tiempo", LibroTipo.ENSAYO, 2005, "Editorial Planeta",17));
		b.pushCopias(new Copia("Marito Baracus", "Argentino", dateFormat.parse("12-11-1983"), "Alquilando fulanitos", LibroTipo.NOVELA, 1993, "Editorial Planeta",18));
		b.pushCopias(new Copia("Marito Baracus", "Argentino", dateFormat.parse("12-11-1983"), "Alquilando fulanitos", LibroTipo.NOVELA, 1993, "Editorial Planeta",19));
		b.pushCopias(new Copia("Marito Baracus", "Argentino", dateFormat.parse("12-11-1983"), "Alquilando fulanitos", LibroTipo.NOVELA, 1993, "Editorial Planeta",20));
		b.pushCopias(new Copia("Stephen King Puig", "Estadounidense", dateFormat.parse("21-9-1947"), "El resplandor", LibroTipo.NOVELA, 1977, "Editorial Vintage ",21));
		b.pushCopias(new Copia("Stephen King Puig", "Estadounidense", dateFormat.parse("21-9-1947"), "El resplandor", LibroTipo.NOVELA, 1977, "Editorial Vintage ",22));
		b.pushCopias(new Copia("Stephen King Puig", "Estadounidense", dateFormat.parse("21-9-1947"), "El resplandor", LibroTipo.NOVELA, 1977, "Editorial Vintage ",23));
		b.pushCopias(new Copia("Katsuhiro Ôtomo", "Japones", dateFormat.parse("14-04-1954"), "Akira tomo 1", LibroTipo.NOVELA, 1982, "Editorial Ivrea",24));
		b.pushCopias(new Copia("Katsuhiro Ôtomo", "Japones", dateFormat.parse("14-04-1954"), "Akira tomo 1", LibroTipo.NOVELA, 1982, "Editorial Ivrea",25));
		b.pushCopias(new Copia("Katsuhiro Ôtomo", "Japones", dateFormat.parse("14-04-1954"), "Akira tomo 1", LibroTipo.NOVELA, 1982, "Editorial Ivrea",26));
		
		b.pushLectores(l);
		b.pushLectores(l2);
		
	}

	@Test
	final void testPrestarCuatro(){
		try {
			for (Copia c : this.b.stock()) {
				b.alquilar(l.getnSocio(),c.getId());
			}
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
    public void testPedirYDevolver() throws ParseException, LectorIdException, LectorExcedeAlquileresException {
        //Pedir 3, devolver 1 y pedir 1 de nuevo
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    	Date pas;
		pas = dateFormat.parse("15-03-2021");
		Lector tmp = b.obtenerLector(l2.getnSocio());
		
    	
		try {
			b.alquilar(tmp.getnSocio(),26);
			b.alquilar(tmp.getnSocio(),15);
			b.alquilar(tmp.getnSocio(),16);
			
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
    public void testPedirConMulta() throws ParseException, NullPointerException, LectorIdException{
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    	Date pas;
		pas = dateFormat.parse("15-03-2021");
		Lector tmp = b.obtenerLector(l.getnSocio());
    	
        Copia c = this.b.stock().iterator().next();
       
        try {
        	b.alquilar(l.getnSocio(),c.getId());
        	b.regresar(tmp.getnSocio(), 0, pas);
        	b.alquilar(l.getnSocio(),c.getId());
        	
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
       

}
