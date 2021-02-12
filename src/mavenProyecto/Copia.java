package mavenProyecto;

import java.util.Date;

public class Copia extends Libro implements Comparable<Copia>{

	public Copia(String nombre, String nacionalidad, Date nacimiento, String nombre2, LibroTipo tipo, int año,
			String editorial, int id) {
		super(nombre, nacionalidad, nacimiento, nombre2, tipo, año, editorial);
		this.id = id;
		this.estado = estadoCopia.BIBLIOTECA;
	}
	private int id;
	
	private estadoCopia estado;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public estadoCopia getEstado() {
		return estado;
	}
	public void setEstado(estadoCopia estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + id;
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
		Copia other = (Copia) obj;
		if (estado != other.estado)
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public int compareTo(Copia o) {

		return this.id-o.getId();
	}
	@Override
	public String toString() {
		return ", Estado= " + getEstado() + ", Libro= " + getNombre()
				+ ", Tipo= " + getTipo() + ", Año publicacion= " + getAño() + ", " + getEditorial()
				+ ", Autor = " + getNombreAutor() + ", Nacionalidad = "
				+ getNacionalidad() + ", Nacimiento= " + dateFormat.format(getNacimiento());
	}
	
	

}
