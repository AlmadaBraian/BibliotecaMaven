package mavenProyecto;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Biblioteca <T>{
		
		private ArrayList<Copia> arreglo = new ArrayList<Copia>();	
		
		public Object peek() {
			
			return arreglo.get(0);
			
		}
		
		public void poop() {
			
			List lista = new ArrayList();
			
			for(int i=1; i< arreglo.size(); i++) {
				
				lista.add(arreglo.get(i));
			}
			arreglo.clear();
			arreglo.addAll(lista);
			
		}
		
		public void push (T e) {
			List lista = new ArrayList();
			for(Object o:arreglo)  {
				lista.add(o);
			}
			lista.add(e);
			arreglo.clear();
			arreglo.addAll(lista);
			
		}
		
		public void reverse() {
			List lista = new ArrayList();
			for(int i=arreglo.size()-1; i>=0 ; i--) {
				lista.add(arreglo.get(i));
			}
			arreglo.clear();
			arreglo.addAll(lista);
			
		}

		@Override
		public String toString() {

			return "Libros [arreglo=" + this.arreglo+ "]";
		}
		
		void printCollection(Collection<?> c) {
			
		    for (Object e : c) {
		        System.out.println(e);
		    }
		}
		
		
		
		public void stockString(){
			
			Map<Integer, Copia> lista2 = CopiasUtil.getCopias(this.arreglo);
			Set<Integer> claves = lista2.keySet();
			Iterator<Integer> it = claves.iterator();
			while (it.hasNext()) {
				Integer clave = it.next();
				Copia pe = lista2.get(clave);
				System.out.printf("Copia: Id: " + clave +" " + pe.toString());
				System.out.println("\n");
			}
			
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

		public int compareTo(Lector o) {
			return 0;
			
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
		
		public void alquilar(Lector a, int id) {
			
			if(a.prestar(id, new Date())) {
				modEstadoCopia(id, estadoCopia.PRESTADO);
			}
		}
		
		public void regresar (Lector a, int id) throws ParseException {
			a.devolver (id, new Date());
			modEstadoCopia(id, estadoCopia.BIBLIOTECA);
			
		}
		
		

}
