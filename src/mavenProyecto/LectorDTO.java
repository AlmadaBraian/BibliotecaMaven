package mavenProyecto;


public class LectorDTO {

	
	private String nombre;
	
	private String telefono;
	
	private String direccion;
	
	private boolean multado;
	
	public void LectorDTO(){
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public boolean isMultado() {
		return multado;
	}

	public void setMultado(boolean multado) {
		this.multado = multado;
	}

	
}
