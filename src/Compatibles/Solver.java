package Compatibles;

import java.util.HashSet;
import java.util.Set;

import negocio.GrafoLista;
import negocio.Persona;

public class Solver {
	private GrafoLista _grafoLista;
	private Set<Persona> _mejor;
	public Set<Persona> get_mejor() {
		return _mejor;
	}

	public void set_mejor(Set<Persona> _mejor) {
		this._mejor = _mejor;
	}

	public Set<Persona> get_actual() {
		return _actual;
	}

	public void set_actual(Set<Persona> _actual) {
		this._actual = _actual;
	}

	private Set<Persona> _actual;
	
	
	private Solver(GrafoLista grafoLista) {
		_grafoLista=grafoLista;
		
	}
	
	public void Resolver(String rol){
		_mejor = new HashSet<Persona>();
		_actual = new HashSet<Persona>();
		
		buscarMejorDesde(0,rol);
	}
	
	private void buscarMejorDesde(int vertice,String rol) {
		//CASO BASE 1
		if(vertice == _grafoLista.getTamanio()) {
			if(Auxiliares.sonCompatibles(_grafoLista, _actual)  &&   _grafoLista.getPersonaNum(vertice).get_rol()==rol  )  {
				_mejor=clonar(_actual);		
				return;
			}
		
		}
		//CASO BASE 2 Backtracking 
		if(Auxiliares.sonCompatibles(_grafoLista, _actual)==false)
			return;
		
		// CASO RECURSIVO
		_actual.add(_grafoLista.getPersonaNum(vertice));
		buscarMejorDesde(vertice+1,rol);
		
		_actual.remove(_grafoLista.getPersonaNum(vertice));
		buscarMejorDesde(vertice+1,rol);

	}

	private Set<Persona> clonar(Set<Persona> _actual2) {
		Set<Persona>ret= new HashSet<Persona>();
		for(Persona i : _actual2) {
			ret.add(i);
		}
		return ret;
	}
	

}
