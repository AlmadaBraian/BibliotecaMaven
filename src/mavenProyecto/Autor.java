package mavenProyecto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Autor implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8503078945885609819L;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	@Column(name="autor_id")@Id@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String nombreAutor;
	@Column
	private String nacionalidad;
	@Column
	private Date nacimiento;
	@OneToMany(targetEntity=Libro.class, mappedBy="autor", fetch=FetchType.EAGER)
	private List<Libro> obras = new ArrayList<Libro>();
	
	//public Autor(String nombre, String nacionalidad, Date nacimiento) {
		//super();
		//this.nombre = nombre;
		//this.nacionalidad = nacionalidad;
		//this.nacimiento = nacimiento;
		
	//}
	
	public Autor() {
		super();
	}
	
	
	
	public void setObras(List<Libro> obras) {
		this.obras = obras;
	}

	public long getId() {
		return id;
	}
	
	public void pushlibro(Libro libro) {
		this.obras.add(libro);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreAutor() {
		return nombreAutor;
	}

	public void setNombreAutor(String nombre) {
		this.nombreAutor = nombre;
	}

	public void setObras(ArrayList<Libro> obras) {
		this.obras = obras;
	}

	public List<Libro> getObras() {
		return obras;
	}
	public void setObras(Libro libro) {
		this.obras.add(libro);
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
		result = prime * result + ((nombreAutor == null) ? 0 : nombreAutor.hashCode());
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
		if (nombreAutor == null) {
			if (other.nombreAutor != null)
				return false;
		} else if (!nombreAutor.equals(other.nombreAutor))
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
	public boolean equals(Libro obj) {
		
		return false;
	}
	
	
	
	

}
