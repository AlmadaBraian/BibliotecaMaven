package persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import mavenProyecto.Autor;

public class AutorDAO {
	EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ejsHibernate");
	
	EntityManager em = managerFactory.createEntityManager();
	EntityTransaction tran = em.getTransaction();

	public void agregarAutor(Autor autor) {

		tran.begin();
		em.persist(autor);

		tran.commit();
		em.close();
	}
	
	public ArrayList<Autor> consultarAutores() {
		ArrayList<Autor> autores = new ArrayList<Autor>();
		javax.persistence.Query q = em.createQuery("SELECT a FROM Autor a GROUP BY autor_id");
		@SuppressWarnings("unchecked")
		List results = q.getResultList();
		autores.addAll(results);
		return autores;
	}
	
	
	public Autor obtenerAutor(long id) {
		ArrayList<Autor>l = consultarAutores();
		Iterator<Autor> it = l.iterator();
		
		while (it.hasNext()) {
			Autor pe = it.next();
			if (pe.getId()==id) {
				return pe;
			}
		}

		return null;
	}

}
