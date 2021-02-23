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
import Excepciones.PrestamoExeption;
import mavenProyecto.Autor;
import mavenProyecto.Biblioteca;
import mavenProyecto.Copia;
import mavenProyecto.Lector;
import mavenProyecto.Libro;
import mavenProyecto.LibroTipo;
import mavenProyecto.Prestamo;
import mavenProyecto.estadoCopia;

class LectorTest {
	Biblioteca<Copia> b = new Biblioteca<Copia>();
	Lector l = new Lector();
	Lector l2 = new Lector();
	
	Autor autor = new Autor();
	Libro libro = new Libro();
	

	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	Date pas;

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
		
		for (int i=15;i<27;i++) {
			libro.setId((long) i);
			b.pushCopia(libro);
		}

		b.pushLectores(l);
		b.pushLectores(l2);
	}

	@Test
	final void testDevolver() throws ParseException, PrestamoExeption {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		Date pas = dateFormat.parse("15-03-2021");
		autor.setNacimiento(pas);
		autor.setNacionalidad("Frances");
		autor.setNombreAutor("Arturo Puig");
		autor.setId((long) 12);
		
		
		libro.setAño(2005);
		libro.setEditorial("planeta");
		libro.setNombre("Frutos de su tiempo");
		libro.setTipo(LibroTipo.ENSAYO);
		
		libro.setEstado(estadoCopia.BIBLIOTECA);
		
		libro.setAutor(autor);
		
		autor.pushlibro(libro);
		
		try {
			b.alquilar(l,b.obtenerCopia(16));
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
				lect.devolver(lect.getPrestamos().get(0), new Date());
			} catch (ParseException e) {
				fail("Fecha mal formada");
			}
		} catch (LectorIdException e) {
			fail("Id lector no existe");
		}
		
		
	}

	@Test
	final void testAgregarPrestamo() {
		try {
			b.alquilar(l,b.obtenerCopia(16));
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
				Prestamo p = new Prestamo();
				//p.setCopia(libro);
				lect.agregarPrestamo(p);
				
			} catch (NullPointerException e) {
				fail("indice de prestamos inexistente");
			}
		} catch (LectorIdException e) {
			fail("Id lector no existe");
		}
		
	}

	@Test
	final void testPrestarConMulta() throws PrestamoExeption, LectorExcedeAlquileresException, CopiaYaAlquiladaException {
		
		try {
			b.alquilar(l,b.obtenerCopia(16));
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
				
				b.regresar(lect.getnSocio(), lect.getPrestamos().get(0), pas);
				
				b.alquilar(l,b.obtenerCopia(16));
				if(lect.prestar()) {
					fail("Deja alquilar a pesar de estar multado");
				}
			} catch (ParseException e) {
				fail("Fecha mal formada");
			}catch (LectorMultaException e) {
				fail("Lector multado");
			}
		} catch (LectorIdException e) {
			fail("Id lector no existe");
		}
	}
	
	@Test
	final void testPrestarConExcesoPrestamos() throws ParseException, LectorMultaException, CopiaYaAlquiladaException {
		try {
			b.alquilar(l,b.obtenerCopia(16));
			b.alquilar(l,b.obtenerCopia(17));
			b.alquilar(l,b.obtenerCopia(18));
			
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
				b.alquilar(l,b.obtenerCopia(18));
			}catch (LectorExcedeAlquileresException e) {
				fail("Lector exede alquileres permitidos");
			}catch (NullPointerException e) {
				fail("indice de prestamos inexistente");
			}
		} catch (LectorIdException e) {
			fail("Id lector no existe");
		}
	}

}
