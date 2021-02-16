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
import mavenProyecto.Prestamo;

class LectorTest {
	Biblioteca<Copia> b = new Biblioteca<Copia>();
	Lector l = new Lector(518, "Bartolo Gimenez", "47369502", "Darragueira 5840");
	Lector l2 = new Lector(519, "Braian Almada", "47369502", "Darragueira 5840");
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	Date pas;

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
	final void testDevolver() {
		
		try {
			b.alquilar(l.getnSocio(),b.obtenerCopia(16));
		} catch (ParseException e) {
			fail("Fecha mal formada");
		} catch (LectorMultaException e) {
			fail("Lector multado");
		} catch (LectorIdException e) {
			fail("Id lector no existe");
		} catch (LectorExcedeAlquileresException e) {
			fail("Maximo alquileres alcanzado");
		}catch (CopiaYaAlquiladaException e) {
			fail("Copia ya alquilada");
		}
		
		try {
			Lector lect = b.obtenerLector(l.getnSocio());
			
			try {
				lect.devolver(0, new Date());
			} catch (ParseException e) {
				fail("Fecha mal formada");
			}catch (NullPointerException e) {
				fail("indice de prestamos inexistente");
			}
		} catch (LectorIdException e) {
			fail("Id lector no existe");
		}
		
		
	}

	@Test
	final void testAgregarPrestamo() {
		try {
			b.alquilar(l.getnSocio(),b.obtenerCopia(16));
		} catch (ParseException e) {
			fail("Fecha mal formada");
		} catch (LectorMultaException e) {
			fail("Lector multado");
		} catch (LectorIdException e) {
			fail("Id lector no existe");
		} catch (LectorExcedeAlquileresException e) {
			fail("Maximo alquileres alcanzado");
		}catch (CopiaYaAlquiladaException e) {
			fail("Copia ya alquilada");
		}
		
		try {
			Lector lect = b.obtenerLector(l.getnSocio());
			
			try {
				lect.agregarPrestamo(new Prestamo(lect,b.obtenerCopia(15)));
			} catch (ParseException e) {
				fail("Fecha mal formada");
			}catch (NullPointerException e) {
				fail("indice de prestamos inexistente");
			}
		} catch (LectorIdException e) {
			fail("Id lector no existe");
		}
		
	}

	@Test
	final void testPrestarConMulta() {
		try {
			b.alquilar(l.getnSocio(),b.obtenerCopia(16));
		} catch (ParseException e) {
			fail("Fecha mal formada");
		} catch (LectorMultaException e) {
			fail("Lector multado");
		} catch (LectorIdException e) {
			fail("Id lector no existe");
		} catch (LectorExcedeAlquileresException e) {
			fail("Maximo alquileres alcanzado");
		}catch (CopiaYaAlquiladaException e) {
			fail("Copia ya alquilada");
		}
		
		try {
			Lector lect = b.obtenerLector(l.getnSocio());
			
			try {
				pas = dateFormat.parse("15-03-2021");
				
				b.regresar(lect.getnSocio(), 0, pas);
				
				
				if(lect.prestar()) {
					fail("Deja alquilar a pesar de estar multado");
				}
			} catch (ParseException e) {
				fail("Fecha mal formada");
			}catch (NullPointerException e) {
				fail("indice de prestamos inexistente");
			}
		} catch (LectorIdException e) {
			fail("Id lector no existe");
		}
	}
	
	@Test
	final void testPrestarConExcesoPrestamos() {
		try {
			b.alquilar(l.getnSocio(),b.obtenerCopia(16));
			b.alquilar(l.getnSocio(),b.obtenerCopia(17));
			b.alquilar(l.getnSocio(),b.obtenerCopia(18));
			
		} catch (ParseException e) {
			fail("Fecha mal formada");
		} catch (LectorMultaException e) {
			fail("Lector multado");
		} catch (LectorIdException e) {
			fail("Id lector no existe");
		} catch (LectorExcedeAlquileresException e) {
			fail("Maximo alquileres alcanzado");
		} catch (CopiaYaAlquiladaException e) {
			fail("Copia ya alquilada");
		}
		
		try {
			Lector lect = b.obtenerLector(l.getnSocio());
			
			try {
				
				if(lect.prestar()) {
					fail("Deja alquilar a pesar de estar excedido en prestamos");
				}
			}catch (NullPointerException e) {
				fail("indice de prestamos inexistente");
			}
		} catch (LectorIdException e) {
			fail("Id lector no existe");
		}
	}

}
