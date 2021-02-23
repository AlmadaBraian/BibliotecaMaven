package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mavenProyecto.Lector;
import mavenProyecto.Multa;

class MultaTest {
	Multa multa;
	Lector l;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	String inicio;
	String fin;

	@BeforeEach
	void setUp() throws Exception {
		l = new Lector();
		l.setDireccion("Darragueira 5840");
		l.setNombre("Braian Almada");
		l.setTelefono("47369501");
		
		inicio = "16-02-2021";
		fin = "16-03-2021";
		
		multa = new Multa();
		multa.setfFin(fin);
		multa.setfInicio(inicio);
		
		l.setMulta(multa);
	}

	@Test
	final void testDiasDif() throws ParseException {
		Date f = dateFormat.parse("19-02-2021");
		int res = multa.diasDif(dateFormat.parse(inicio), f);
		int esperado = 3;
		
		assertEquals(esperado, res, "Fallo la resta de dias");
		
	}

	@Test
	final void testSumarDiasFecha() throws ParseException {
		Date res = multa.sumarDiasFecha(dateFormat.parse(inicio), 4);
		Date esperado = dateFormat.parse("20-02-2021");
		
		assertEquals(esperado, res, "Fecha no esperada");
		
	}

}
