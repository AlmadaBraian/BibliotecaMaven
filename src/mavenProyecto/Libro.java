package mavenProyecto;

import java.util.Date;

public class Libro extends Autor implements Comparable{

	private String nombre;
	private LibroTipo tipo;
	private int a�o;
	private String editorial;
	private int copias;
	
	
	public Libro(String nombre, String nacionalidad, Date nacimiento, String nombre2, LibroTipo tipo, int a�o,
			String editorial) {
		super(nombre, nacionalidad, nacimiento);
		this.nombre = nombre2;
		this.tipo = tipo;
		this.a�o = a�o;
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
	public int getA�o() {
		return a�o;
	}
	public void setA�o(int a�o) {
		this.a�o = a�o;
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
		result = prime * result + a�o;
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
		return "nombre=" + nombre + ", tipo=" + tipo + ", a�o=" + a�o + ", editorial=" + editorial + ", copias="
				+ copias ;
	}


	@Override
	public int compareTo(Object o) {
		
		return this.hashCode()-o.hashCode();
	}
	
	
	
	
	
	

}
