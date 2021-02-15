package mavenProyecto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import Excepciones.LectorExcedeAlquileresException;
import Excepciones.LectorIdException;
import Excepciones.LectorMultaException;

public class Biblioteca <T>{
		
		private ArrayList<Copia> arreglo = new ArrayList<Copia>();
		
		private ArrayList<Lector> lectores = new ArrayList<Lector>();
		
		private ArrayList<Libro> libros = new ArrayList<Libro>();
		
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
			pushLibro(e.getLibro());
			setCantCopiasLibro(e.getLibro());
			
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
				
				s+= "Copia: " + pe.toString() + "\n";
			}
			return s;
			
		}
		
		public String stockLibrosString(){
			String s="";
			Iterator<Libro> it = libros.iterator();
			
			while (it.hasNext()) {
				Libro pe = it.next();
				
				s+= "Libro: " + pe.stringLibro() + "\n";
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
		
		public Lector obtenerLector(int id) throws LectorIdException{
			try {
				Iterator<Lector> it = lectores.iterator();
				
				while (it.hasNext()) {
					Lector pe = it.next();
					if (pe.getnSocio() == id) {
						return pe;
					}
				}throw new LectorIdException("Id de lector inexistente");
			} catch (LectorIdException e) {
				System.out.println(e);
				return null;
			}
			
			
		}
		
		public Multa obtenerMultaLector (int id) throws LectorIdException {
			
			Lector l = obtenerLector(id);
			return l.getMulta();
			
		}
		
		public ArrayList<Copia> stock(){
			return this.arreglo;
		}
		
		public void alquilar(int idLector, int id) throws ParseException, LectorMultaException, LectorIdException, LectorExcedeAlquileresException {
			
			Lector a = obtenerLector(idLector);
			if (a != null) {
				if(a.prestar(new Date())) {
					
					a.agregarPrestamo(new Prestamo(a,obtenerCopia(id)));
					modEstadoCopia(id, estadoCopia.PRESTADO);
					
				}else if (a.getPrestamos().size()>3){
					
					throw new LectorExcedeAlquileresException("El lector "+ a.getNombre() + " ah excedido el maximo de alquileres");
					
				}else if (a.getMulta() != null){
					
					SimpleDateFormat dateFormat = a.getMulta().dateFormat;
					throw new LectorMultaException("El lector "+a.getNombre() + " no podra retirar libros hasta el " + dateFormat.format(a.getMulta().getfFin()));
				}

			}
			
		}
		
		public void regresar (int idLector, int id, Date fecha) throws ParseException, LectorIdException, NullPointerException{
			try {
				Lector a = obtenerLector(idLector);
				a.devolver (id, fecha);
				modEstadoCopia(id, estadoCopia.BIBLIOTECA);
			} catch (NullPointerException e) {
				System.out.println(e);
			}

		}
		
		public ArrayList<Prestamo> getPrestamos(){
			
			ArrayList<Prestamo> r =new ArrayList<Prestamo>(); 
			Iterator<Lector> it = lectores.iterator();
			
			while (it.hasNext()) {
				Lector pe = it.next();
				r.addAll(pe.getPrestamos());
			}
			
			return r;
			
		}
		
		public void pushLibro(Libro l){
			boolean b = false;
			
			ArrayList<Libro> tmp = new ArrayList<Libro>();
			
			Iterator<Libro> it = libros.iterator();
			
			while (it.hasNext()) {
				Libro pe = it.next();
				tmp.add(pe);
			}
			
			System.out.println(tmp);
			
			for (Libro libro : tmp) {
				
				if(l.mismoLibro(libro)){
					b = true;
					break;
				}
			}
			if (b==false) {
				tmp.add(l);
				this.libros.clear();
				this.libros.addAll(tmp);
			}

		}
		
		public void setCantCopiasLibro(Libro l) {
			Libro tmp=getLibro(l);
			int cant = 0;
			for (Copia copia : arreglo) {
				if(tmp.mismoLibro(copia.getLibro())) {
					cant++;	
				}
			}
			tmp.setCopias(cant);
		}

		public ArrayList<Lector> getLectores() {
			return lectores;
		}

		public void setLectores(ArrayList<Lector> lectores) {
			this.lectores = lectores;
		}

		public ArrayList<Libro> getLibros() {
			return libros;
		}

		public void setLibros(ArrayList<Libro> libros) {
			this.libros = libros;
		}
		
		public Libro getLibro(Libro l) {
			for (Libro libro : libros) {
				if(l.mismoLibro(libro)) {
					return libro;
				}
			}
			return null;
		}
		
		

		
		
		

}
