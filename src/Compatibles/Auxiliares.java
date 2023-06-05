package Compatibles;

import java.util.Set;

import negocio.Arista;
import negocio.GrafoLista;
import negocio.Persona;

public class Auxiliares {
	/**Revisa entre un grafo y el conjunto
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
