package mavenProyecto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Biblioteca <T>{
		
		private ArrayList<Copia> arreglo = new ArrayList<Copia>();
		
		private ArrayList<Lector> lectores = new ArrayList<Lector>();
		
		
		public Object peek(ArrayList<T> arr) {
			
			return arr.get(0);
			
		}
		
		public void poopCopias(int id) {
			
			ArrayList<Copia> copias = new ArrayList<Copia>();

			Iterator<Copia> it = arreglo.iterator();
			
			while (it.hasNext()) {
				Copia pe = it.next();

				if (pe.getId() != id) {
					copias.add(pe);
				}
			}
			this.arreglo.clear();
			this.arreglo.addAll(copias);
			
		}
		
		public void poopLectores(int id) {
			
			ArrayList<Lector> copias = new ArrayList<Lector>();
			
			Iterator<Lector> it = lectores.iterator();
			
			while (it.hasNext()) {
				Lector pe = it.next();
				
				if (pe.getnSocio() != id) {
					copias.add(pe);
				}
			}
			this.lectores.clear();
			this.lectores.addAll(copias);
			
		}
		
		public void pushCopias (Copia e) {
			ArrayList<Copia> tmp = new ArrayList<Copia>();
			
			Iterator<Copia> it = arreglo.iterator();
			
			while (it.hasNext()) {
				Copia pe = it.next();
				tmp.add(pe);
			}
			tmp.add(e);
			this.arreglo.clear();
			this.arreglo.addAll(tmp);
		}
		public void pushLectores (Lector e) {
			
			ArrayList<Lector> tmp = new ArrayList<Lector>();
			
			Iterator<Lector> it = lectores.iterator();
			
			while (it.hasNext()) {
				Lector pe = it.next();
				tmp.add(pe);
			}
			tmp.add(e);
			this.lectores.clear();
			this.lectores.addAll(tmp);
		}
		
		
		public String stockString(){
			String s="";
			Iterator<Copia> it = arreglo.iterator();
			
			while (it.hasNext()) {
				Copia pe = it.next();
				
				s+= "Copia: Id: " + pe.getId() +" " + pe.toString() + "\n";
			}
			return s;
			
		}
		
		public void  modEstadoCopia(int id, estadoCopia estado) {
			
			List<Copia> lista = new ArrayList<Copia>();
			
			Iterator<Copia> it = arreglo.iterator();
			
			while (it.hasNext()) {
				Copia pe = it.next();
				if (pe.getId() == id) {
					pe.setEstado(estado);
				}
				lista.add(pe);
			}
			
			this.arreglo.clear();
			this.arreglo.addAll(lista);
		}
		
		public Copia obtenerCopia(int id) {
			
			Iterator<Copia> it = arreglo.iterator();
			
			while (it.hasNext()) {
				Copia pe = it.next();
				if (pe.getId() == id) {
					return pe;
				}
			}
			return null;
		}
		
		public Lector obtenerLector(int id) {
			
			Iterator<Lector> it = lectores.iterator();
			
			while (it.hasNext()) {
				Lector pe = it.next();
				if (pe.getnSocio() == id) {
					return pe;
				}
			}
			return null;
		}
		
		public Multa obtenerMUltaLector (int id) {
			Lector l = obtenerLector(id);
			return l.getMulta();
		}
		
		public ArrayList<Copia> stock(){
			
			ArrayList<Copia> r =new ArrayList<Copia>(); 
			Iterator<Copia> it = arreglo.iterator();
			
			while (it.hasNext()) {
				Copia pe = it.next();
				r.add(pe);
			}
			
			return r;
			
		}
		
		public void alquilar(int idLector, int id) throws ParseException {
			
			Lector a = obtenerLector(idLector);
			
			if(a.prestar(id, new Date())) {
				a.agregarPrestamo(new Prestamo(obtenerCopia(id)));
				modEstadoCopia(id, estadoCopia.PRESTADO);
			}else {
				try {
					
					if (a.getMulta() != null){
						SimpleDateFormat dateFormat = a.getMulta().dateFormat;
						throw new RuntimeException("El lector "+a.getNombre() + " no podra retirar libros hasta el " + dateFormat.format(a.getMulta().getfFin()));
					}
					
				} catch (RuntimeException e) {
					System.out.println(e);
				} 
			}

		}
		
		public void regresar (int idLector, int id, Date fecha) throws ParseException {
			Lector a = obtenerLector(idLector);
			a.devolver (id, fecha);
			modEstadoCopia(id, estadoCopia.BIBLIOTECA);	
		}
		
		

}
