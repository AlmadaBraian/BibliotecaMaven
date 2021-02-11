package mavenProyecto;

import java.util.Date;

public class Libro extends Autor{

	private String nombre;
	private LibroTipo tipo;
	private int año;
	private String editorial;
	
	
	public Libro(String nombre, String nacionalidad, Date nacimiento, String nombre2, LibroTipo tipo, int año,
			String editorial) {
		super(nombre, nacionalidad, nacimiento);
		this.nombre = nombre2;
		this.tipo = tipo;
		this.año = año;
		this.editorial = editorial;
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LibroTipo getTipo() {
		return tipo;
	}
	public void setTipo(LibroTipo tipo) {
		this.tipo = tipo;
	}
	public int getAño() {
		return año;
	}
	public void setAño(int año) {
		this.año = año;
	}
	
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	
	

}
