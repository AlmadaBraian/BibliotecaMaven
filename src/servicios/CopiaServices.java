package servicios;

import java.util.ArrayList;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import mavenProyecto.Copia;
import mavenProyecto.Libro;
import persistencia.CopiaDAO;


@WebService
public class CopiaServices {
	
	@WebMethod(operationName="agregarCopiaWS")
	public boolean agregarLCopia(@WebParam(name="copia")Libro l) {
		CopiaDAO dao = new CopiaDAO();
		try {
			dao.agregarCopia(l);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@WebMethod(operationName="obtenerCopiasWS")
	public ArrayList<Libro> consultarCopias() {
		CopiaDAO dao = new CopiaDAO();
		ArrayList<Libro> copias = new ArrayList<Libro>();
		try {
			copias = dao.consultarCopias();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return copias;
	}
	@WebMethod(operationName="obtenerCopiaWS")
	public Copia obtenerCopia(@WebParam(name="copiaid")long id) {
		CopiaDAO dao = new CopiaDAO();
		Libro c = dao.obtenerCopia(id);
		return c;	
	}

}
