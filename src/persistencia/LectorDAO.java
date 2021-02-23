package persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.query.Query;

import mavenProyecto.Lector;

public class LectorDAO {
	
	EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ejsHibernate");
	
	EntityManager em = managerFactory.createEntityManager();
	EntityTransaction tran = em.getTransaction();

	public void agregarLector(Lector l) {
		
		tran.begin();
		em.persist(l);

		tran.commit();
		em.close();
	}
	
	public ArrayList<Lector> consultarLectores() {
		ArrayList<Lector> lectores = new ArrayList<Lector>();
		javax.persistence.Query q = em.createQuery("SELECT nombre FROM lectores a GROUP BY n_socio");
		@SuppressWarnings("unchecked")
		List results = q.getResultList();
		lectores.addAll(results);
		return lectores;
	}
}
