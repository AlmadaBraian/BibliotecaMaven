package mavenProyecto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Multa extends Lector{
	
	private Date fInicio;
	private Date fFin;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	

	public Multa(Lector l, Date fInicio, Date fFin) {
		super(l.getnSocio(), l.getNombre(), l.getTelefono(), l.getDireccion());
		this.fInicio = fInicio;
		this.fFin = fFin;
	}

	public Multa(Lector l,Date fInicio, int dias) throws ParseException {
		super(l.getnSocio(), l.getNombre(), l.getTelefono(), l.getDireccion());
		this.fInicio = fInicio;
		this.fFin = sumarDiasFecha(fInicio, dias);
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

	@Override
	public String toString() {
		return "Lector multado: "+ this.getNombre() +" Multado el: " + dateFormat.format(fInicio) + ", Fin multa: " + dateFormat.format(fFin);
	}
	

}
