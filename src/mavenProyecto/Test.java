package mavenProyecto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Test {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		Date pas = dateFormat.parse("10-01-2021");
		Date nacimiento = dateFormat.parse("10-01-1993");
		
		Lector l = new Lector(518, "Braian Almada", "47369502", "Darragueira 5840");
		Lector l2 = new Lector(518, "Braian Almada", "47369502", "Darragueira 5840");
		
		Biblioteca<Copia> b = new Biblioteca<Copia>();
		
		b.push(new Copia("Arturo Puig", "Frances", nacimiento, "Frutos de su tiempo", LibroTipo.ENSAYO, 2005, "Editorial Planeta",15));
		b.push(new Copia("Arturo Puig", "Frances", nacimiento, "Frutos de su tiempo", LibroTipo.ENSAYO, 2005, "Editorial Planeta",16));
		b.push(new Copia("Arturo Puig", "Frances", nacimiento, "Frutos de su tiempo", LibroTipo.ENSAYO, 2005, "Editorial Planeta",17));
		b.push(new Copia("Marito Baracus", "Argentino", dateFormat.parse("12-11-1983"), "Alquilando fulanitos", LibroTipo.NOVELA, 1993, "Editorial Planeta",18));
		b.push(new Copia("Marito Baracus", "Argentino", dateFormat.parse("12-11-1983"), "Alquilando fulanitos", LibroTipo.NOVELA, 1993, "Editorial Planeta",19));
		b.push(new Copia("Marito Baracus", "Argentino", dateFormat.parse("12-11-1983"), "Alquilando fulanitos", LibroTipo.NOVELA, 1993, "Editorial Planeta",20));
		b.push(new Copia("Stephen King Puig", "Estadounidense", dateFormat.parse("21-9-1947"), "El resplandor", LibroTipo.NOVELA, 1977, "Editorial Vintage ",21));
		b.push(new Copia("Stephen King Puig", "Estadounidense", dateFormat.parse("21-9-1947"), "El resplandor", LibroTipo.NOVELA, 1977, "Editorial Vintage ",22));
		b.push(new Copia("Stephen King Puig", "Estadounidense", dateFormat.parse("21-9-1947"), "El resplandor", LibroTipo.NOVELA, 1977, "Editorial Vintage ",23));
		b.push(new Copia("Katsuhiro Ôtomo", "Japones", dateFormat.parse("14-04-1954"), "Akira tomo 1", LibroTipo.NOVELA, 1982, "Editorial Ivrea",24));
		b.push(new Copia("Katsuhiro Ôtomo", "Japones", dateFormat.parse("14-04-1954"), "Akira tomo 1", LibroTipo.NOVELA, 1982, "Editorial Ivrea",25));
		b.push(new Copia("Katsuhiro Ôtomo", "Japones", dateFormat.parse("14-04-1954"), "Akira tomo 1", LibroTipo.NOVELA, 1982, "Editorial Ivrea",26));
		
		//b.stock();
		
		b.alquilar(l,26);
		b.alquilar(l,15);
		b.alquilar(l,16);
		
		b.alquilar(l2,26);
		b.alquilar(l2,17);
		
		
		
		b.stockString();
		
		
		
		
	}

}
