package mavenProyecto;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table (name="copias")
public class Copia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8205977888805468258L;
	

	//public Copia(String nombre, String nacionalidad, Date nacimiento, String nombre2, LibroTipo tipo, int año,
			//String editorial, long id) {
		//super();
		//this.id = id;
		//this.estado = estadoCopia.BIBLIOTECA;
	//}
	@Column(name="copia_id")@Id@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private estadoCopia estado;
	
	public Copia() {
		super();
	}



	public long getId() {
		return id;
	}
	public void setId(long id) {
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
		long result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + id;
		return (int) result;
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
	public int compareTo(Copia o) {

		return (int) (this.id-o.getId());
	}

	public boolean equals(Libro obj) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
