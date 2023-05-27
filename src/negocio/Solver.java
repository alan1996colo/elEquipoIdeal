package negocio;

import java.util.HashSet;
import java.util.Set;

public class Solver {
	private GrafoLista _grafoLista;
	private Set<Persona> _mejor;
	private Set<Persona> _actual;
	
	
	private Solver(GrafoLista grafoLista) {
		_grafoLista=grafoLista;
		
	}
	
	private void Resolver(String rol){
		_mejor = new HashSet<Persona>();
		_actual = new HashSet<Persona>();
		
		buscarMejorDesde(0,rol);
	}

	private void buscarMejorDesde(int vertice,String rol) {
		//CASO BASE
		if() {
			
			
		}
		
		// CASO RECURSIVO
		_actual.add(_grafoLista.getPersonaNum(vertice));
		buscarMejorDesde(vertice+1,rol);
		
		_actual.remove(_grafoLista.getPersonaNum(vertice));
		buscarMejorDesde(vertice+1,rol);
		
		
		
		
		
		
	}

}
