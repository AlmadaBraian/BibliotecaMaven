package mavenProyecto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Autor {
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	private String nombre;
	private String nacionalidad;
	private Date nacimiento;
	private ArrayList<Libro> obras;
	
	public Autor(String nombre, String nacionalidad, Date nacimiento) {
		//super();
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.nacimiento = nacimiento;
		
	}
	public ArrayList<Libro> getObras() {
		return obras;
	}
	public void setObras(Libro libro) {
		this.obras.add(libro);
	}
	public String getNombreAutor() {
		return nombre;
	}
	public void setNombreAutor(String nombre) {
		this.nombre = nombre;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public Date getNacimiento() {
		return nacimiento;
	
	}
	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}

}
