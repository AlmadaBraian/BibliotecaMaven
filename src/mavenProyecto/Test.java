package mavenProyecto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Excepciones.CopiaYaAlquiladaException;
import Excepciones.LectorExcedeAlquileresException;
import Excepciones.LectorIdException;
import Excepciones.LectorMultaException;

public class Test {

	public static void main(String[] args) throws ParseException, LectorMultaException, LectorIdException, LectorExcedeAlquileresException, CopiaYaAlquiladaException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		Date pas = dateFormat.parse("15-03-2021");
		Date nacimiento = dateFormat.parse("10-01-1993");
		
		Lector l = new Lector(518, "Bartolo Gimenez", "47369502", "Darragueira 5840");
		Lector l2 = new Lector(519, "Braian Almada", "47369502", "Darragueira 5840");
		
		Biblioteca<Copia> b = new Biblioteca<Copia>();
		
		b.pushCopias(new Copia("Arturo Puig", "Frances", nacimiento, "Frutos de su tiempo", LibroTipo.ENSAYO, 2005, "Editorial Planeta",15));
		b.pushCopias(new Copia("Arturo Puig", "Frances", nacimiento, "Frutos de su tiempo", LibroTipo.ENSAYO, 2005, "Editorial Planeta",16));
		b.pushCopias(new Copia("Arturo Puig", "Frances", nacimiento, "Frutos de su tiempo", LibroTipo.ENSAYO, 2005, "Editorial Planeta",17));
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
		b.stock();
		
		b.alquilar(l.getnSocio(),b.obtenerCopia(26));
		b.alquilar(l.getnSocio(),b.obtenerCopia(15));
		b.alquilar(l.getnSocio(),b.obtenerCopia(16));
		
		b.alquilar(l2.getnSocio(),b.obtenerCopia(18));
		b.alquilar(l2.getnSocio(),b.obtenerCopia(17));

		
		System.out.println(b.stockString());
		
		Lector tmp = b.obtenerLector(l.getnSocio()); 

		b.regresar(tmp.getnSocio(), 1, pas);
		
		System.out.println(b.obtenerMultaLector(tmp.getnSocio()));
		
		System.out.println("\n");
		
		System.out.println(b.stockString());
		
		System.out.println("\n");
		
		//b.alquilar(tmp.getnSocio(),18);
		
		System.out.println("\n");
		
		System.out.println("Stock libros: \n"+b.stockLibrosString());
		
		for (Prestamo pres : b.getPrestamos()) {
			System.out.println(pres);
		}

	
	}

}
