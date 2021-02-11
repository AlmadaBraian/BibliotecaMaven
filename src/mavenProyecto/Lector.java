package mavenProyecto;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Lector{
	
	private int nSocio;
	private String nombre;
	private String telefono;
	private String direccion;
	private Multa multa;
	private ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();

	public Lector(int nSocio, String nombre, String telefono, String direccion) {
		super();
		this.nSocio = nSocio;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
	}


	public int getnSocio() {
		return nSocio;
	}
	public void setnSocio(int nSocio) {
		this.nSocio = nSocio;
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
	
	public void devolver (int id, Date fechaAct) throws ParseException{
		
		Prestamo p = obtenerPrestamo(id);
		
		if (p.diasDif()>0) {
			multar(p.diasDif()*2);
		}
		
		borrarPrestamo(id);
		
	}
	
	public void agregarPrestamo(Prestamo p) {
		this.prestamos.add(p);
	}
	
	public boolean prestar(int id, Date fechaAct){
		
		if (this.multa == null){
			
			if (prestamos.size()<2) {
				return true;
				
			}
		}
		return false;

	}
	
	private void multar(int dias) throws ParseException {
		
		this.multa = new Multa(new Date(), dias);
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + nSocio;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
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
		Lector other = (Lector) obj;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (nSocio != other.nSocio)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}
	
	public Prestamo obtenerPrestamo(int id) {
		
		Map<Integer, Prestamo> lista2 = PrestamosUtil.getPrestamos(this.prestamos);
		
		Set<Integer> claves = lista2.keySet();
		
		Iterator<Integer> it = claves.iterator();
		
		while (it.hasNext()) {
			Integer clave = it.next();
			Prestamo pe = lista2.get(clave);
			if (pe.getCopia().getId() == id) {
				return pe;
			}
		}
		return null;
	}
	
	public void  borrarPrestamo(int id) {
		
		List<Prestamo> lista = new ArrayList<Prestamo>();
		
		Map<Integer, Prestamo> lista2 = PrestamosUtil.getPrestamos(this.prestamos);
		
		Set<Integer> claves = lista2.keySet();
		
		Iterator<Integer> it = claves.iterator();
		
		while (it.hasNext()) {
			Integer clave = it.next();
			Prestamo pe = lista2.get(clave);
			if (pe.getCopia().getId() != id) {
				lista.add(pe);
			}
		}
		
		this.prestamos.clear();
		this.prestamos.addAll(lista);
	}
	
	
}
