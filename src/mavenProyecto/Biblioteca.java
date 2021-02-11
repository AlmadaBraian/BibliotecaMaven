package mavenProyecto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Biblioteca <T>{
		
		private ArrayList<Copia> arreglo = new ArrayList<Copia>();
		
		private ArrayList<Lector> lectores = new ArrayList<Lector>();
		
		
		public Object peek(ArrayList<T> arr) {
			
			return arr.get(0);
			
		}
		
		public void poopCopias(int id) {
			
			ArrayList<Copia> copias = new ArrayList<Copia>();
			
			Set<Integer> claves = CopiasUtil.getCopias().keySet();
			
			Iterator<Integer> it = claves.iterator();
			
			while (it.hasNext()) {
				Integer clave = it.next();
				Copia pe = CopiasUtil.getCopias().get(clave);
				if (pe.getId() != id) {
					copias.add(pe);
				}
			}
			this.arreglo.clear();
			this.arreglo.addAll(copias);
			
		}
		
		public void poopLectores(int id) {
			
			ArrayList<Lector> copias = new ArrayList<Lector>();
			
			Set<Integer> claves = LectoresUtil.getLectores(copias).keySet();
			
			Iterator<Integer> it = claves.iterator();
			
			while (it.hasNext()) {
				Integer clave = it.next();
				Lector pe = LectoresUtil.getLectores(copias).get(clave);
				if (pe.getnSocio() != id) {
					copias.add(pe);
				}
			}
			this.lectores.clear();
			this.lectores.addAll(copias);
			
		}
		
		public void pushCopias (T e) {
			List lista = new ArrayList();
			for(Object o:arreglo)  {
				lista.add(o);
			}
			lista.add(e);
			arreglo.clear();
			arreglo.addAll(lista);
			
		}
		public void pushLectores (Lector e) {
			
			ArrayList<Lector> tmp = new ArrayList<Lector>();
			
			Set<Integer> claves = LectoresUtil.getLectores(lectores).keySet();
			
			Iterator<Integer> it = claves.iterator();
			
			while (it.hasNext()) {
				Integer clave = it.next();
				Lector pe = LectoresUtil.getLectores(lectores).get(clave);
				tmp.add(pe);
			}
			tmp.add(e);
			this.lectores.clear();
			this.lectores.addAll(tmp);
		}
		
		public void reverse() {
			List lista = new ArrayList();
			for(int i=arreglo.size()-1; i>=0 ; i--) {
				lista.add(arreglo.get(i));
			}
			arreglo.clear();
			arreglo.addAll(lista);
			
		}
		
		
		public String stockString(){
			String s="";
			Map<Integer, Copia> lista2 = CopiasUtil.getCopias(this.arreglo);
			Set<Integer> claves = lista2.keySet();
			Iterator<Integer> it = claves.iterator();
			while (it.hasNext()) {
				Integer clave = it.next();
				Copia pe = lista2.get(clave);
				s+= "Copia: Id: " + clave +" " + pe.toString() + "\n";
			}
			return s;
			
		}
		
		public void  modEstadoCopia(int id, estadoCopia estado) {
			
			List<Copia> lista = new ArrayList<Copia>();
			
			Map<Integer, Copia> lista2 = CopiasUtil.getCopias(this.arreglo);
			
			Set<Integer> claves = lista2.keySet();
			
			Iterator<Integer> it = claves.iterator();
			
			while (it.hasNext()) {
				Integer clave = it.next();
				Copia pe = lista2.get(clave);
				if (pe.getId() == id) {
					pe.setEstado(estado);
				}
				lista.add(pe);
			}
			
			this.arreglo.clear();
			this.arreglo.addAll(lista);
		}
		
		public Copia obtenerCopia(int id) {
			
			Map<Integer, Copia> lista2 = CopiasUtil.getCopias(this.arreglo);
			
			Set<Integer> claves = lista2.keySet();
			
			Iterator<Integer> it = claves.iterator();
			
			while (it.hasNext()) {
				Integer clave = it.next();
				Copia pe = lista2.get(clave);
				if (pe.getId() == id) {
					return pe;
				}
			}
			return null;
		}
		
		public Lector obtenerLector(int id) {
			
			Map<Integer, Lector> lista2 = LectoresUtil.getLectores(this.lectores);
			
			Set<Integer> claves = lista2.keySet();
			
			Iterator<Integer> it = claves.iterator();
			
			while (it.hasNext()) {
				Integer clave = it.next();
				Lector pe = lista2.get(clave);
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
			
			Map<Integer, Copia> lista2 = CopiasUtil.getCopias(this.arreglo);
			ArrayList<Copia> r =new ArrayList(); 
			Set<Integer> claves = lista2.keySet();
			Iterator<Integer> it = claves.iterator();
			while (it.hasNext()) {
				Integer clave = it.next();
				Copia pe = lista2.get(clave);
				r.add(pe);
			}
			
			return r;
			
		}
		
		public void alquilar(int idLector, int id) throws ParseException {
			Lector a = obtenerLector(idLector);
			if(a.prestar(id, new Date())) {
				a.agregarPrestamo(new Prestamo(obtenerCopia(id)));
				modEstadoCopia(id, estadoCopia.PRESTADO);
			}
			else if (a.getMulta() != null){
				SimpleDateFormat dateFormat = a.getMulta().dateFormat;
				System.out.println("El lector "+a.getNombre() + " no podra retirar libros hasta el " + dateFormat.format(a.getMulta().getfFin()));
			}
		}
		
		public void regresar (int idLector, int id, Date fecha) throws ParseException {
			Lector a = obtenerLector(idLector);
			a.devolver (id, fecha);
			modEstadoCopia(id, estadoCopia.BIBLIOTECA);	
		}
		
		

}
