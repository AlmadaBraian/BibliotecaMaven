package persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import mavenProyecto.Autor;
import mavenProyecto.Copia;
import mavenProyecto.Libro;

public class CopiaDAO {
	EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ejsHibernate");
	
	EntityManager em = managerFactory.createEntityManager();
	EntityTransaction tran = em.getTransaction();
	
	public ArrayList<Libro> consultarCopias() {
		ArrayList<Libro> copias = new ArrayList<Libro>();
		javax.persistence.Query q = em.createQuery("SELECT a FROM Libro a GROUP BY copia_id");
		@SuppressWarnings("unchecked")
		List results = q.getResultList();
		copias.addAll(results);
		return copias;
	}
	
	public Libro obtenerCopia(long id) {
		ArrayList<Libro>l = consultarCopias();
		Iterator<Libro> it = l.iterator();
		
		while (it.hasNext()) {
			Libro pe = it.next();
			if (pe.getId()==id) {
				return pe;
			}
		}

		return null;
	}
	
	public void agregarCopia(Libro c) {
		
		tran.begin();
		em.persist(c);
		tran.commit();

		eliminarNulos();

	}
	
	public void eliminarNulos() {
		ArrayList<Libro> libros = consultarCopias();
		Iterator<Libro> it = libros.iterator();
		
		while (it.hasNext()) {
			Libro pe = it.next();
			if (pe.getNombre()==null && pe.getEditorial()==null) {
				Libro l = em.find(Libro.class, pe.getId());
				em.getTransaction().begin();
				em.remove(l);
				em.getTransaction().commit();
				
			}
		}
		em.close();
	}

}
