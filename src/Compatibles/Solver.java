package Compatibles;

import java.util.HashSet;
import java.util.Set;

import negocio.Arista;
import negocio.GrafoLista;
import negocio.Persona;
import negocio.Requerimiento;

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
	
	
	public Solver(GrafoLista grafoLista) {
		_grafoLista=grafoLista;
		
	}
	
	
	
	/**Si el vertice ingresada es un set que cumple los requerimientos solicitados, TRUE, sino false.**/
	public boolean esSet(Persona p,Requerimiento req) {
		int countLiderDeProyecto=0;
		int countArquitecto=0;
		int countProgramador=0;
		int countTester=0;
		//tambien contamos a la persona sobre la que estamos parados.
		if(p.get_rol().equals("Lider")) {	countLiderDeProyecto++;}
		if(p.get_rol().equals("Arquitecto")) {	countArquitecto++;}
		if(p.get_rol().equals("Programador")) {	countProgramador++;}
		if(p.get_rol().equals("Tester")) {	countTester++;}
		
		//contamos las personas compatibles a la que estamos parado.
		for(Arista ar:p.getCompatibles()) {
			if(ar.getPersonaDestino().get_rol().equals("Lider")) {
				countLiderDeProyecto++;
			}
			if(ar.getPersonaDestino().get_rol().equals("Arquitecto")) {
				countArquitecto++;
			}
			if(ar.getPersonaDestino().get_rol().equals("Programador")) {
				countProgramador++;
			}
			if(ar.getPersonaDestino().get_rol().equals("Tester")) {
				countTester++;
			}
			}
	return	req.cumpleRequerimientos(countLiderDeProyecto, countArquitecto, countProgramador, countTester);
	
	}
	
//	public boolean todosCompatibles(Persona p, Requerimiento req) {
//		int countLiderDeProyecto=req.getCantLiderProyecto();
//		int countArquitecto=req.getCantArquitecto();
//		int countProgramador=req.getCantProgramador();
//		int countTester=req.getCantTester();
//		
//		for(Arista ar:p.getCompatibles()) {
//			if(!esSet(ar.getPersonaDestino(),req)) {
//				return false;
//			}
//			
//			
//			
//			
//		}
//		
//		
//		
//		
//		return false;
//	}
	
	
	
	
	
	/**	Devuelve el clique maximo del rol ingresado.**/
	public Set<Persona> Resolver(){
		_mejor = new HashSet<Persona>();
		_actual = new HashSet<Persona>();
		
		buscarMejorDesde(0);
		return _mejor;
	}
	
	private void buscarMejorDesde(int vertice) {
		//CASO BASE 1
		if(vertice == _grafoLista.getTamanio()-1) {//aca esta el error. estas pidiendo un out of bounds
			if(Auxiliares.sonCompatibles( _actual)  && 
					
					 _actual.size()>_mejor.size() )  {
				_mejor=clonar(_actual);		
				return;
			}
		
		}
		//CASO BASE 2 Backtracking 
		if(!Auxiliares.sonCompatibles( _actual))
			return;
		
		// CASO RECURSIVO
		if(vertice<_grafoLista.getTamanio()) {
		_actual.add(_grafoLista.getPersonaNum(vertice));
		buscarMejorDesde(vertice+1);
		
		_actual.remove(_grafoLista.getPersonaNum(vertice));
		buscarMejorDesde(vertice+1);}

	}

	private Set<Persona> clonar(Set<Persona> _actual2) {
		Set<Persona>ret= new HashSet<Persona>();
		for(Persona i : _actual2) {
			ret.add(i);
		}
		return ret;
	}
	

}
