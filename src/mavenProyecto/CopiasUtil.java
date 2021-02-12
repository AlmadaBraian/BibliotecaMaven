package mavenProyecto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class CopiasUtil{


	public static Map<Integer, Copia> getCopias (ArrayList<Copia> setA){
		Map<Integer,Copia> copias = new HashMap<Integer, Copia>();
		
		for (Copia copia : setA) {
			copias.put(copia.getId(), copia);
		}

		return copias;
		
	}
	
	public static int compare(Copia o1, Copia o2) {
		
		return o1.getId()-o2.getId();
	}
	
	public static ArrayList<Copia> ordenarCopias (ArrayList<Copia> setA) {
		Collections.sort(setA);
	    return setA;
	}

	
	

	
}
