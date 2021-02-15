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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateFormat == null) ? 0 : dateFormat.hashCode());
		result = prime * result + ((nacimiento == null) ? 0 : nacimiento.hashCode());
		result = prime * result + ((nacionalidad == null) ? 0 : nacionalidad.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((obras == null) ? 0 : obras.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		if (dateFormat == null) {
			if (other.dateFormat != null)
				return false;
		} else if (!dateFormat.equals(other.dateFormat))
			return false;
		if (nacimiento == null) {
			if (other.nacimiento != null)
				return false;
		} else if (!nacimiento.equals(other.nacimiento))
			return false;
		if (nacionalidad == null) {
			if (other.nacionalidad != null)
				return false;
		} else if (!nacionalidad.equals(other.nacionalidad))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (obras == null) {
			if (other.obras != null)
				return false;
		} else if (!obras.equals(other.obras))
			return false;
		return true;
	}
	public int compareTo(Copia o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	

}
