package mavenProyecto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface LectoresUtil {

		public static Map<Integer, Lector> getLectores (ArrayList<Lector> setA){
			Map<Integer, Lector> lectores = new HashMap<Integer, Lector>();
		
			for (Lector copia : setA) {
				lectores.put(copia.getnSocio(), copia);
			}
		
			Set<Integer> claves = lectores.keySet();

			return lectores;
		
		}

}
