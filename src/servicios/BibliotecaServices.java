package servicios;

import java.util.ArrayList;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import mavenProyecto.Biblioteca;
import mavenProyecto.Copia;
import mavenProyecto.Lector;
import mavenProyecto.Libro;
import mavenProyecto.estadoCopia;
import persistencia.BibliotecaDAO;
import persistencia.CopiaDAO;
import persistencia.LectorDAO;

@WebService
public class BibliotecaServices {
	
	@WebMethod(operationName="obtenetDBWS")
	public Biblioteca obtenerBiblioteca() {
		BibliotecaDAO dao = new BibliotecaDAO();
		Biblioteca b = new Biblioteca();
		try {
			dao.obtenerBiblioteca(b);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return b;
	}
	@WebMethod(operationName="modificarEstadoCopiaWS")
	public void modEstadoCopia(@WebParam(name="copiaID")long c, @WebParam(name="estadoCopia")estadoCopia e) {
		CopiaDAO daoc= new CopiaDAO();
		Libro copia =daoc.obtenerCopia(c);
		BibliotecaDAO dao = new BibliotecaDAO();
		dao.modEstadoCopia(copia, e);
	}
	
	@WebMethod(operationName="obtenerLectorWS")
	public Lector obtenerLector(@WebParam(name="lectorID")long id) {
		LectorDAO ldao = new LectorDAO();
		return ldao.obtenerLector(id);
	}
	
	@WebMethod(operationName="obtenerCopiaWS")
	public Libro obtenerCopia(@WebParam(name="lectorID")long id) {
		CopiaDAO cdao = new CopiaDAO();
		return cdao.obtenerCopia(id);
	}


}
