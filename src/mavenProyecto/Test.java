package mavenProyecto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Test {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
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
		b.pushCopias(new Copia("Katsuhiro �tomo", "Japones", dateFormat.parse("14-04-1954"), "Akira tomo 1", LibroTipo.NOVELA, 1982, "Editorial Ivrea",24));
		b.pushCopias(new Copia("Katsuhiro �tomo", "Japones", dateFormat.parse("14-04-1954"), "Akira tomo 1", LibroTipo.NOVELA, 1982, "Editorial Ivrea",25));
		b.pushCopias(new Copia("Katsuhiro �tomo", "Japones", dateFormat.parse("14-04-1954"), "Akira tomo 1", LibroTipo.NOVELA, 1982, "Editorial Ivrea",26));
		
		b.pushLectores(l);
		b.pushLectores(l2);
		//b.stock();
		
		b.alquilar(l.getnSocio(),26);
		b.alquilar(l.getnSocio(),15);
		b.alquilar(l.getnSocio(),16);
		
		b.alquilar(l2.getnSocio(),25);
		b.alquilar(l2.getnSocio(),17);

		
		System.out.println(b.stockString());
		
		Lector tmp = b.obtenerLector(l.getnSocio()); 
		
		ArrayList<Prestamo> prestamos = tmp.getPrestamos();
		
		for(Prestamo p : prestamos) {
			System.out.println(p.toString());
		}

		b.regresar(l.getnSocio(), 0, pas);
		
		System.out.println("\n");
		
		System.out.println(b.stockString());
		
		System.out.println(tmp.getMulta().getfFin());
		
		System.out.println("\n");
		
		b.alquilar(tmp.getnSocio(),18);
		
		System.out.println("\n");
		
		System.out.println(b.stockString());
	
	}

}
