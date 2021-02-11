package mavenProyecto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class CopiasUtil{
	private static Map<Integer,Copia> copias;
	
	private Set<Integer> claves;
	
	private Iterator<Integer> it = claves.iterator();

	public static Map<Integer, Copia> getCopias (ArrayList<Copia> setA){
		copias = new HashMap<Integer, Copia>();
		
		for (Copia copia : setA) {
			copias.put(copia.getId(), copia);
		}
		
		Set<Integer> claves = copias.keySet();

		return copias;
		
	}
	
	public static int compare(Copia o1, Copia o2) {
		
		return o1.getId()-o2.getId();
	}
	
	public static ArrayList<Copia> ordenarCopias (ArrayList<Copia> setA) {
		Collections.sort(setA);
	    return setA;
	}
	
	public Object peek() {
		
		return CopiasUtil.getCopias().get(0);
		
	}
	
	public void poop(int id) {
		ArrayList<Copia> tmp = new ArrayList<Copia>();
		Set<Integer> claves = CopiasUtil.getCopias().keySet();
		
		Iterator<Integer> it = claves.iterator();
		
		while (it.hasNext()) {
			Integer clave = it.next();
			Copia pe = CopiasUtil.getCopias().get(clave);
			if (pe.getId() != id) {
				tmp.add(pe);
			}
		}
		CopiasUtil.setCopias(CopiasUtil.getCopias(tmp));
		
	}

	public static Map<Integer,Copia> getCopias() {
		return copias;
	}

	public static void setCopias(Map<Integer,Copia> copias) {
		CopiasUtil.copias = copias;
	}
	
	

	
}
