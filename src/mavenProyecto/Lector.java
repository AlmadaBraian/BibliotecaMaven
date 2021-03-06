package mavenProyecto;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import Excepciones.LectorExcedeAlquileresException;
import Excepciones.PrestamoExeption;

@Entity
@Table(name="lectores")
public class Lector implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7397198799032369360L;
	@Column(name="n_socio")@Id@GeneratedValue(strategy = GenerationType.AUTO)
	private long nSocio;
	@Column
	private String nombre;
	@Column
	private String telefono;
	@Column
	private String direccion;
	@Column(name="multa")
	private boolean multado;
	@OneToOne(mappedBy = "lector", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "multa_id")
	private Multa multa;
	
	@OneToMany(targetEntity=Prestamo.class, mappedBy="lector", fetch=FetchType.EAGER)
	private List<Prestamo> prestamos = new ArrayList<Prestamo>();
	
	public Lector() {
	}
	


	public void setMulta(Multa multa) {
		this.multa = multa;
		this.multado=true;
	}

	public void setPrestamos(ArrayList<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	public long getnSocio() {
		return nSocio;
	}
	public void setnSocio(long nSocio) {
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
	
	public List<Prestamo> getPrestamos() {
		
		return this.prestamos;
	}
	
	public Multa getMulta() {
		return multa;
	}
	
	public void devolver (Prestamo p, Date fechaAct) throws ParseException, PrestamoExeption{
		
		int i = obtenerIndicePrestamo(p);
		if(i>=0) {
			if (p.diasDif(fechaAct)>0) {
				multar(p.diasDif(fechaAct)*2);
			}
			
			borrarPrestamo(i);
			this.multado=false;
		}
		else
			throw new PrestamoExeption("El prestamos solicitado no existe");
		
	}
	
	
	public void agregarPrestamo(Prestamo p) {
		
		ArrayList<Prestamo> tmp = new ArrayList<Prestamo>();
		
		Iterator<Prestamo> it = prestamos.iterator();
		
		while (it.hasNext()) {
			Prestamo pe = it.next();
			tmp.add(pe);
		}
		
		tmp.add(p);
		this.prestamos.clear();
		this.prestamos.addAll(tmp);
	}
	
	
	public boolean prestar(){
		
		if (this.multa == null){
			
			if (prestamos.size()<3) {
				return true;
				
			}
		}
		return false;

	}
	
	private void multar(int dias) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Multa m = new Multa();
		m.setfInicio(dateFormat.format(new Date()));
		m.setfFin(dias);
		m.setLector(this);
		
		this.setMulta(m);
		

	}
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = (int) (prime * result + nSocio);
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
	
	public int obtenerIndicePrestamo(Prestamo p) {
		for (int i=0; i<prestamos.size();i++) {
			if(prestamos.get(i).getPrestamoId()==p.getPrestamoId()) {
				return i;
			}
		}
		return -1;
		
	}
	
	
	public void  borrarPrestamo(int index) {
		
		List<Prestamo> lista = new ArrayList<Prestamo>();
		
		Iterator<Prestamo> it = prestamos.iterator();
		
		while (it.hasNext()) {
			Prestamo pe = it.next();
			
			if (pe.equals(prestamos.get(index)) == false) {
				lista.add(pe);
			}
		}
		
		this.prestamos.clear();
		this.prestamos.addAll(lista);
	}




	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}
	
	
	
}
