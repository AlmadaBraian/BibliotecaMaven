package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mavenProyecto.Autor;
import mavenProyecto.Biblioteca;
import mavenProyecto.Copia;
import mavenProyecto.Libro;
import mavenProyecto.LibroTipo;
import mavenProyecto.estadoCopia;

class LibroTest {
	Biblioteca<Copia> b = new Biblioteca<Copia>();
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	Libro libro = new Libro();
	Libro libro2 = new Libro();
	
	@BeforeEach
	void setUp() throws Exception {
		Date nacimiento = dateFormat.parse("10-01-1993");
		Autor autor = new Autor();
		autor.setNacimiento(nacimiento);
		autor.setNacionalidad("Frances");
		autor.setNombreAutor("Arturo Puig");
		
		
		libro.setAño(2005);
		libro.setEditorial("planeta");
		libro.setNombre("Frutos de su tiempo");
		libro.setTipo(LibroTipo.ENSAYO);
		
		libro.setEstado(estadoCopia.BIBLIOTECA);
		
		libro.setAutor(autor);
		
		
		libro2.setAño(2005);
		libro2.setEditorial("planeta");
		libro2.setNombre("Frutos de su tiempo");
		libro2.setTipo(LibroTipo.ENSAYO);
		
		libro2.setEstado(estadoCopia.BIBLIOTECA);
		
		libro2.setAutor(autor);

	}
	
	@Test
    public void testEquals() {
		if(libro.equals(libro2)==false) {
			fail("la igualdad no se cumplio");
		}
	}


}
