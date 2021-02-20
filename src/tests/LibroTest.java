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

	}


}
