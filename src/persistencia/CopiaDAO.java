package persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
	
	/*public Object obtenerCopia(long id) {
		Object c = new Libro();
		javax.persistence.Query q = em.createQuery("SELECT a FROM Libro a WHERE a.id = :id");
		q.setParameter("id", id);
		c=q.getSingleResult();
		return c;
	}*/
	
	public void agregarCopia(Libro c) {

		
		tran.begin();
		em.persist(c);

		tran.commit();
		em.close();
	}

}
