package servicios;

import java.util.ArrayList;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import mavenProyecto.Autor;
import persistencia.AutorDAO;

public class AutorServices {
	@WebMethod(operationName="agregarAutorWS")
	public boolean agregarAutor(@WebParam(name="autor")Autor l) {
		AutorDAO dao = new AutorDAO();
		try {
			dao.agregarAutor(l);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@WebMethod(operationName="obtenerAutoresWS")
	public ArrayList<Autor> obtenerLectores() {
		AutorDAO dao = new AutorDAO();
		ArrayList<Autor> autores = new ArrayList<Autor>();
		try {
			autores = dao.consultarAutores();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return autores;
	}
	@WebMethod(operationName="obtenerAutorWS")
	public Autor obtenerLector(@WebParam(name="AutorId")long id) {
		AutorDAO dao = new AutorDAO();
		return dao.obtenerAutor(id);
	}

}
