package mavenProyecto;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Prestamo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3914260519787018107L;

	@Column(name="prestamo_id")@Id@GeneratedValue(strategy = GenerationType.AUTO)
	private long prestamoId;
	@Column
	private Date inicio;
	@Column
	private Date fin;
	@Column
	private int maxDias=30;
	@ManyToOne()
    @JoinColumn(name = "n_socio")
	private Lector lector;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "copia_id", referencedColumnName="copia_id")
	private Copia copia;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	
	
	//public Prestamo(Lector l,Copia copia) throws ParseException {
		//this.lector = l;
		//this.inicio = hoy;
		//this.fin = sumarDiasFecha(hoy, maxDias);
		//this.copia = copia;
	//}
	
	//public Prestamo(Lector l,Copia copia, Date inicio) throws ParseException {
		//this.lector = l;
		//this.inicio = inicio;
		//this.fin = sumarDiasFecha(inicio, maxDias);
		//this.copia = copia;
	//}
	
	
	public Lector getLector() {
		return lector;
	}


	public void setLector(Lector lector) {
		this.lector = lector;
	}


	public int getMaxDias() {
		return maxDias;
	}


	public void setMaxDias(int maxDias) {
		this.maxDias = maxDias;
	}


	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFin() {
		return fin;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}
	public Copia getCopia() {
		return copia;
	}
	public void setCopia(Copia copia) {
		this.copia = copia;
	}
	
	public int diasMulta(int dif) throws ParseException {
		
	    return sumarDiasFechaInt(new Date(), (dif)*2);
	}
	
	public int diasDif() {
	    long startTime = this.fin.getTime();
	    long endTime = new Date().getTime();
	    long diffTime = endTime - startTime;
	    return (int)TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS);
	}
	
	public int diasDif(Date f) {
	    long startTime = new Date().getTime();
	    long endTime = f.getTime();
	    long diffTime = endTime - startTime;
	    return (int)TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS);
	}

	 public int sumarDiasFechaInt(Date fecha, int dias) throws ParseException{

	      Calendar calendar = Calendar.getInstance();

	      calendar.setTime(fecha); // Configuramos la fecha que se recibe

	      calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0

	      String tmp = dateFormat.format(calendar.getTime());  
	      return diasDif(dateFormat.parse(tmp));
	 }
	 
	 public Date sumarDiasFecha(Date fecha, int dias) throws ParseException{

	      Calendar calendar = Calendar.getInstance();

	      calendar.setTime(fecha); // Configuramos la fecha que se recibe

	      calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0

	      String tmp = dateFormat.format(calendar.getTime());  
	      return dateFormat.parse(tmp);
	 }


	@Override
	public String toString() {
		return "Prestamo: \nLector: " + lector.getNombre() + "\nInicio: " + dateFormat.format(inicio) + ", Fin: " + dateFormat.format(fin) + "\nCopia: \n" + copia + "\n ";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((copia == null) ? 0 : copia.hashCode());
		result = prime * result + ((fin == null) ? 0 : fin.hashCode());
		result = prime * result + ((inicio == null) ? 0 : inicio.hashCode());
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
		Prestamo other = (Prestamo) obj;
		if (copia == null) {
			if (other.copia != null)
				return false;
		} else if (!copia.equals(other.copia))
			return false;
		if (fin == null) {
			if (other.fin != null)
				return false;
		} else if (!fin.equals(other.fin))
			return false;
		if (inicio == null) {
			if (other.inicio != null)
				return false;
		} else if (!inicio.equals(other.inicio))
			return false;
		return true;
	}
	
	
	 
	 


	

}
