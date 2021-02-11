package mavenProyecto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class LectorUtil{

	public static Map<Integer, Lector> getLectores (ArrayList<Lector> setA){
		Map<Integer,Lector> lectores = new HashMap<Integer, Lector>();
		
		for (Lector lector : setA) {
			lectores.put(lector.getnSocio(), lector);
		}
		
		Set<Integer> claves = lectores.keySet();
		
		
		return lectores;
		
	}
	
	public int compare(Lector o1, Lector o2) {
		
		return o1.getnSocio()-o2.getnSocio();
	}
	
}
