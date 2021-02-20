package mavenProyecto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface LectoresUtil {

		public static Map<Integer, Lector> getLectores (ArrayList<Lector> setA){
			Map<Integer, Lector> lectores = new HashMap<Integer, Lector>();
		
			for (Lector copia : setA) {
				lectores.put((int) copia.getnSocio(), copia);
			}

			return lectores;
		
		}

}
