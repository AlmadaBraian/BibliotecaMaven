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
	
	Date inicio;
	Date fin;

	@BeforeEach
	void setUp() throws Exception {
		l = new Lector(518, "Bartolo Gimenez", "47369502", "Darragueira 5840");
		inicio = dateFormat.parse("16-02-2021");
		fin = dateFormat.parse("16-03-2021");
		multa = new Multa(l, inicio, fin);
	}

	@Test
	final void testDiasDif() throws ParseException {
		fin = dateFormat.parse("19-02-2021");
		int res = multa.diasDif(inicio, fin);
		int esperado = 3;
		
		assertEquals(esperado, res, "Fallo la resta de dias");
		
	}

	@Test
	final void testSumarDiasFecha() throws ParseException {
		Date res = multa.sumarDiasFecha(inicio, 4);
		Date esperado = dateFormat.parse("20-02-2021");
		
		assertEquals(esperado, res, "Fecha no esperada");
		
	}

}
