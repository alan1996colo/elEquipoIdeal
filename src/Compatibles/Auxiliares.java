package Compatibles;

import java.util.Set;

import negocio.Arista;
import negocio.Persona;

public class Auxiliares {
	/**Revisa si todas las personas del conjunto son compatibles.
	 * */
	public static boolean sonCompatibles(Set<Persona> conjunto) {
		for(Persona i: conjunto)
		for(Persona j: conjunto) {
			Arista ar=new Arista(i,j);
			if(i!=j && !i.getCompatibles().contains(ar)) {
				return false;
			}
		}
		return true;
	}

}
