package mavenProyecto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class CopiasUtil{


	public static Map<Integer, Copia> getCopias (ArrayList<Copia> setA){
		Map<Integer,Copia> copias = new HashMap<Integer, Copia>();
		
		for (Copia copia : setA) {
			copias.put((int) copia.getId(), copia);
		}

		return copias;
		
	}
	
	public static int compare(Copia o1, Copia o2) {
		
		return (int) (o1.getId()-o2.getId());
	}

	
	

	
}
