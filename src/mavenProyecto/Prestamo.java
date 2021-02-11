package mavenProyecto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Prestamo{
	
	private Date inicio;
	private Date fin;
	private Copia copia;
	private Date hoy = new Date();
	private int maxDias=30;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	public Prestamo(Copia copia) throws ParseException {
		super();
		this.inicio = hoy;
		this.fin = sumarDiasFecha(hoy, maxDias);
		this.copia = copia;
	}
	
	
	public Date getHoy() {
		return hoy;
	}


	public void setHoy(Date hoy) {
		this.hoy = hoy;
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
		
	    return sumarDiasFechaInt(hoy, (dif)*2);
	}
	
	public int diasDif() {
	    long startTime = this.fin.getTime();
	    long endTime = hoy.getTime();
	    long diffTime = endTime - startTime;
	    return (int)TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS);
	}
	
	public int diasDif(Date f) {
	    long startTime = hoy.getTime();
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
		return "Prestamo [inicio=" + dateFormat.format(inicio) + ", fin=" + dateFormat.format(fin) + ", copia=" + copia + "]";
	}
	 
	 


	

}
