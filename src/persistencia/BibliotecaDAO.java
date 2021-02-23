package persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import mavenProyecto.Biblioteca;
import mavenProyecto.Copia;
import mavenProyecto.Libro;


public class BibliotecaDAO {
	
	EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ejsHibernate");
	
	EntityManager em = managerFactory.createEntityManager();
	EntityTransaction tran = em.getTransaction();
	
	public void obtenerBiblioteca(Biblioteca<?> b) {
		
		LectorDAO ldao = new LectorDAO();
		CopiaDAO cdao = new CopiaDAO();
		b.setLectores(ldao.consultarLectores());
		b.setLibros(cdao.consultarCopias());
		
		ArrayList<Copia> copias = new ArrayList<Copia>();

		Iterator<Libro> it = b.getLibros().iterator();
		
		while (it.hasNext()) {
			Libro pe = it.next();
			Copia c = new Copia();
			c.setEstado(pe.getEstado());
			c.setId(pe.getId());
			copias.add(c);
			}
		
		b.setArreglo(copias);
	}
	
	/*public void  modEstadoCopia(Libro c, estadoCopia estado) {
		
		javax.persistence.Query q = em.createQuery("UPDATE Copia e SET e.estado = :est "+ "WHERE e.id = :id");
		q.setParameter("est", estado);
		q.setParameter("id", c.getId());
	}*/

}
