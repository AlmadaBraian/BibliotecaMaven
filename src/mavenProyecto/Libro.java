package mavenProyecto;

import java.util.Date;

public class Libro extends Autor implements Comparable{

	private String nombre;
	private LibroTipo tipo;
	private int año;
	private String editorial;
	private int copias;
	
	
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
	
	public Libro getLibro() {
		return this;
	}
	
	


	public int getCopias() {
		return copias;
	}


	public void setCopias(int copias) {
		this.copias = copias;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + año;
		result = prime * result + copias;
		result = prime * result + ((editorial == null) ? 0 : editorial.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}
	
	
	
	@Override
	public boolean equals(Libro obj) {

		if(this.getNombre().equals(obj.getNombre()) && this.getNombreAutor().equals(obj.getNombreAutor())) {
			return true;
		}
		 return false;
	}


	public String stringLibro() {
		return "nombre=" + nombre + ", tipo=" + tipo + ", año=" + año + ", editorial=" + editorial + ", copias="
				+ copias ;
	}


	@Override
	public int compareTo(Object o) {
		
		return this.hashCode()-o.hashCode();
	}
	
	
	
	
	
	

}
