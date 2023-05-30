package Compatibles;

import java.util.Set;

import negocio.GrafoLista;
import negocio.Persona;

public class Auxiliares {
	
	public static boolean sonCompatibles(GrafoLista grafo,Set<Persona> conjunto) {
		for(Persona i: conjunto)
		for(Persona j: conjunto) {
			if(i!=j && grafo.sonVecinos(i,j)==false) {
				return false;
			}
		}
		return true;
	}

}
