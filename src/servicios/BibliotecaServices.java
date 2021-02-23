package servicios;

import java.util.ArrayList;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import mavenProyecto.Biblioteca;
import mavenProyecto.Copia;
import mavenProyecto.Lector;
import mavenProyecto.LectorDTO;
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
	public Libro obtenerCopia(@WebParam(name="copiaID")long id) {
		CopiaDAO cdao = new CopiaDAO();
		return cdao.obtenerCopia(id);
	}
	
	@WebMethod(operationName="agregarCopiaWS")
	public void agregarCopia(@WebParam(name="copia")Libro l) {
		CopiaDAO cdao = new CopiaDAO();
		cdao.agregarCopia(l);
	}
	
	@WebMethod(operationName="agregarLectorWS")
	public void agregarLector(@WebParam(name="lector")Lector l) {
		LectorDAO ldao = new LectorDAO();
		LectorDTO aux = new LectorDTO();
		aux.setDireccion(l.getDireccion());
		aux.setNombre(l.getNombre());
		aux.setTelefono(l.getTelefono());
		ldao.agregarLector(aux);
	}


}
