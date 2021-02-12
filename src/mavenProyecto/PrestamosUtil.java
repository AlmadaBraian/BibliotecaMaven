package mavenProyecto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PrestamosUtil {
	
	public static Map<Integer, Prestamo> getPrestamos (ArrayList<Prestamo> setA){
		Map<Integer,Prestamo> prestamos = new HashMap<Integer, Prestamo>();
		
		for (Prestamo prestamo : setA) {
			prestamos.put(prestamo.getCopia().getId(), prestamo);
		}

		return prestamos;
		
	}	
	

}
