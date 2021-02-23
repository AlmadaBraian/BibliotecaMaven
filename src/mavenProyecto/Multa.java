package mavenProyecto;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="multas")
public class Multa{
	
	/**
	 * 
	 */

	private static final long serialVersionUID = 101139859434992764L;
	@Column(name="multa_id")@Id@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	@Column
	private Date fInicio;
	@Column
	private Date fFin;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "n_socio")
	private Lector lector;
    
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	

	public Multa() {
		super();
	}
	
	
	
	public long getId() {
		return Id;
	}



	public void setId(long id) {
		Id = id;
	}



	public Lector getLector() {
		return lector;
	}



	public void setLector(Lector lector) {
		this.lector = lector;
	}



	public Date getfInicio() {
		return fInicio;
	}
	public void setfInicio(Date fInicio) {
		this.fInicio = fInicio;
	}
	public Date getfFin() {
		return fFin;
	}
	public void setfFin(Date fFin) {
		this.fFin = fFin;
	}
	
	public void setfFin(int dias) throws ParseException {
		this.fFin = sumarDiasFecha(dias);
	}
	
	public int diasDif(Date inicio, Date fin) {
	    long startTime = inicio.getTime();
	    long endTime = fin.getTime();
	    long diffTime = endTime - startTime;
	    return (int)TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS);
	}
	
	 public Date sumarDiasFecha(Date fecha, int dias) throws ParseException{

	      Calendar calendar = Calendar.getInstance();

	      calendar.setTime(fecha); // Configuramos la fecha que se recibe

	      calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0

	      String tmp = dateFormat.format(calendar.getTime());  
	      return dateFormat.parse(tmp);
	 }
	 public Date sumarDiasFecha(int dias) throws ParseException{

	      Calendar calendar = Calendar.getInstance();

	      calendar.setTime(fInicio); // Configuramos la fecha que se recibe

	      calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0

	      String tmp = dateFormat.format(calendar.getTime());  
	      return dateFormat.parse(tmp);
	 }	

}
