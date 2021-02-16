package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mavenProyecto.Biblioteca;
import mavenProyecto.Copia;
import mavenProyecto.Libro;
import mavenProyecto.LibroTipo;

class LibroTest {
	Biblioteca<Copia> b = new Biblioteca<Copia>();
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
	}

	@Test
	final void testEqualsObject() {
		Libro l1 = b.obtenerCopia(16).getLibro();
		Libro l2 = b.obtenerCopia(15).getLibro();
		
		boolean esperado = true;
		
		assertEquals(esperado, l1.equals(l2), "Fallo la comparacion");
	}

}
