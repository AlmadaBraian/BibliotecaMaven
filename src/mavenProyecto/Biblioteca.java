package mavenProyecto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import Excepciones.CopiaYaAlquiladaException;
import Excepciones.LectorExcedeAlquileresException;
import Excepciones.LectorIdException;
import Excepciones.LectorMultaException;
import Excepciones.PrestamoExeption;

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
		
		public void pushLectores (Lector e) {
			
			ArrayList<Lector> tmp = new ArrayList<Lector>();
			
			Iterator<Lector> it = lectores.iterator();
			
			while (it.hasNext()) {
				Lector pe = it.next();
				tmp.add(pe);
			}
			tmp.add(e);
			persistir(e);
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
		
		public void  modEstadoCopia(long l, estadoCopia estado) {
			
			List<Copia> lista = new ArrayList<Copia>();
			
			Iterator<Copia> it = arreglo.iterator();
			
			while (it.hasNext()) {
				Copia pe = it.next();
				if (pe.getId() == l) {
					pe.setEstado(estado);
				}
				lista.add(pe);
			}
			
			this.arreglo.clear();
			this.arreglo.addAll(lista);
		}
		
		public Copia obtenerCopia(long l) {
			
			Iterator<Copia> it = arreglo.iterator();
			
			while (it.hasNext()) {
				Copia pe = it.next();
				if (pe.getId() == l) {
					return pe;
				}
			}
			return null;
		}
		
		public Lector obtenerLector(long l) throws LectorIdException{
			try {
				Iterator<Lector> it = lectores.iterator();
				
				while (it.hasNext()) {
					Lector pe = it.next();
					if (pe.getnSocio() == l) {
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
		
		public void alquilar(long l, Copia copia) throws ParseException, LectorMultaException, LectorIdException, LectorExcedeAlquileresException, CopiaYaAlquiladaException {
			
			Lector a = obtenerLector(l);
			Copia c = obtenerCopia(copia.getId());
			
			if (a != null) {

				if (a.getPrestamos().size()>=3){
					
					throw new LectorExcedeAlquileresException("El lector "+ a.getNombre() + " ah excedido el maximo de alquileres");
					
				}else if(a.prestar()) {
					if(c.getEstado() == estadoCopia.BIBLIOTECA) {
						//a.agregarPrestamo(new Prestamo(a,obtenerCopia(copia.getId())));
						modEstadoCopia(copia.getId(), estadoCopia.PRESTADO);
						Prestamo p = new Prestamo();
						p.setCopia(copia);
						p.setInicio(new Date());
						p.setFin();
						p.setLector(a);
						a.agregarPrestamo(p);
						
								
					}else {
						throw new CopiaYaAlquiladaException("La copia que desea alquilar ya esta alquilada o en reparacion");
					}

					
				}else if (a.getMulta() != null){
					
					SimpleDateFormat dateFormat = a.getMulta().dateFormat;
					throw new LectorMultaException("El lector "+a.getNombre() + " no podra retirar libros hasta el " + dateFormat.format(a.getMulta().getfFin()));
				}

			}
			
		}
		
		public void regresar (long l, Prestamo p, Date fecha) throws ParseException, LectorIdException, PrestamoExeption{
			try {
				Lector a = obtenerLector(l);
				a.obtenerIndicePrestamo(p);
				a.devolver (p, fecha);
				long id = p.getCopia().getId();
				modEstadoCopia(id, estadoCopia.BIBLIOTECA);
			} catch (PrestamoExeption e) {
				throw new PrestamoExeption("El prestamos solicitado no existe");
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
		
		public ArrayList<Multa> getMultas(){
			
			ArrayList<Multa>multas = new ArrayList<Multa>();
			
			for (Lector lector : lectores) {
				if(lector.getMulta()!=null) {
					multas.add(lector.getMulta());
				}
			}
			return multas;
		}
		
		public void pushCopia(Libro l){
			boolean b = false;
			
			ArrayList<Libro> tmp = new ArrayList<Libro>();
			
			Iterator<Libro> it = libros.iterator();
			
			while (it.hasNext()) {
				Libro pe = it.next();
				tmp.add(pe);
			}
			
			for (Libro libro : tmp) {
				
				if(l.equals(libro)){
					b = true;
					break;
				}
			}
			if (b==false) {
				tmp.add(l);
				this.libros.clear();
				this.libros.addAll(tmp);
			}
			persistir(l);
			Copia c = new Copia();
			c.setEstado(l.getEstado());
			c.setId(l.getId());
			
			arreglo.add(c);
			

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
				if(l.equals(libro)) {
					return libro;
				}
			}
			return null;
		}
		

		public void checkVencimientoPrestamos() {
			ArrayList<Prestamo> p = getPrestamos();

			for (Prestamo prestamo : p) {
				if(prestamo.diasDif()>prestamo.getMaxDias()) {
					modEstadoCopia(prestamo.getCopia().getId(), estadoCopia.RETRASO); 
				}
			}

		}
		
		public void persistir(Object o) {
			EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ejsHibernate");
			
			EntityManager em = managerFactory.createEntityManager();
			EntityTransaction tran = em.getTransaction();
			tran.begin();
			em.persist(o);

			tran.commit();
			em.close();
		}
		
		public List findWithName(String name) {
			EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ejsHibernate");
			EntityManager em = managerFactory.createEntityManager();
			
			return em.createQuery(
			"SELECT c FROM Lector c WHERE c.nombre LIKE :nombre")
			.setParameter("nombre", name)
			.setMaxResults(10)
			.getResultList();
			}
		

}
