package mavenProyecto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PrestamosUtil {
	
	public static Map<Integer, Prestamo> getPrestamos (ArrayList<Prestamo> setA){
		Map<Integer,Prestamo> prestamos = new HashMap<Integer, Prestamo>();
		
		for (Prestamo prestamo : setA) {
			prestamos.put(prestamo.getCopia().getId(), prestamo);
		}
		
		Set<Integer> claves = prestamos.keySet();
		
		
		return prestamos;
		
	}	
	

}
