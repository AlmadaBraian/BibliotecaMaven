package persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.query.Query;

import mavenProyecto.Lector;
import mavenProyecto.LectorDTO;
import mavenProyecto.Prestamo;

public class LectorDAO {
	
	EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ejsHibernate");
	
	EntityManager em = managerFactory.createEntityManager();
	EntityTransaction tran = em.getTransaction();

	public void agregarLector(LectorDTO lector) {

		
		tran.begin();
		em.persist(lectorCons(lector));

		tran.commit();
		em.close();
	}
	
	public ArrayList<Lector> consultarLectores() {
		ArrayList<Lector> lectores = new ArrayList<Lector>();
		javax.persistence.Query q = em.createQuery("SELECT a FROM Lector a GROUP BY n_socio");
		@SuppressWarnings("unchecked")
		List results = q.getResultList();
		lectores.addAll(results);
		return lectores;
	}
	
	public Lector lectorCons(LectorDTO lector) {
		Lector l = new Lector();
		l.setDireccion(lector.getDireccion());
		l.setNombre(lector.getNombre());
		l.setTelefono(lector.getTelefono());
		
		return l;
	}
	
}
