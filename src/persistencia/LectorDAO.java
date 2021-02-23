package persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.query.Query;

import mavenProyecto.Lector;
import mavenProyecto.LectorDTO;
import mavenProyecto.Libro;
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
	
	public Lector obtenerLector(long id) {
		ArrayList<Lector>l = consultarLectores();
		Iterator<Lector> it = l.iterator();
		
		while (it.hasNext()) {
			Lector pe = it.next();
			if (pe.getnSocio()==id) {
				return pe;
			}
		}

		return null;
	}
	
}
