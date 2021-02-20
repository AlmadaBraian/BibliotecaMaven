package mavenProyecto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table (name="libros")  @DiscriminatorValue ("copia")
public class Libro extends Copia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6387259137273083829L;
	
	@Column(name="libro")
	private String nombre;
	@Column(name="tipo")
	private LibroTipo tipo;
	@Column
	private int a�o;
	@Column
	private String editorial;

	@ManyToOne()
    @JoinColumn(name = "autor_id")
    Autor autor;
	

	
	
	//public Libro(String nombre, String nacionalidad, Date nacimiento, String nombre2, LibroTipo tipo, int a�o,
			//String editorial) {
		//super();
		//this.nombre = nombre2;
		//this.tipo = tipo;
		//this.a�o = a�o;
		//this.editorial = editorial;
	//}
	
	public Libro() {
		super();
	}
	


	public Autor getAutor() {
		return autor;
	}



	public void setAutor(Autor autor) {
		this.autor = autor;
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + a�o;
		result = prime * result + ((editorial == null) ? 0 : editorial.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}
	


	public String stringLibro() {
		return "nombre=" + nombre + ", tipo=" + tipo + ", a�o=" + a�o + ", editorial=" + editorial + ", copias=";
	}

}
