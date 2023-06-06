package Compatibles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import negocio.Arista;
import negocio.GrafoLista;
import negocio.Persona;
import negocio.Requerimiento;

public class Solver {
	private GrafoLista grafo;
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

	/** Recibe como entrada un grafo */
	public Solver(GrafoLista grafoLista) {
		grafo = grafoLista;

	}

	/**
	 * Algoritmo principal
	 * 
	 * Dado una lista de vertices, recorrera todos los vertices aplicando un primer
	 * filtrado a los que contengan un conjunto que supere requerimientos.
	 * 
	 * Luego tomara esos conjuntos y creara en cada conjunto todos los subcontontos
	 * posibles, y aplicara otro filtrado quedandose solo con los que cumplan los
	 * requisitos.
	 * 
	 * Finalmente de los que quedaron, revisara que sean todos compatibles con todos
	 * 
	 * y por ultimo de los que sean todos compatibles con todos tomara el
	 * subconjunto mayor calificado.
	 ***/

	public Set<Persona> calcular(Requerimiento req) {
		ArrayList<HashSet<Persona>> primerFiltroCumplenRequerimientos = new ArrayList<>();
		// recorro todos los vertices y me quedo con los set que cumplenrequerimientos.
		for (Persona iter : grafo.getPersonas()) {
			
			if (conjuntoSuperaRequerimientos(transformarPersonaToSet(iter), req)) {
				primerFiltroCumplenRequerimientos.add(transformarPersonaToSet(iter));
			}
		}
		// A esta altura ya me quede solo con los sets que cumplen requerimientos, pero
		// debo crear todos los subconjuntos posibles para hacerle otra
		// vez el mismo filtro.
		//en mejorCandidato guardo el set con candidatos mejores calificados.

		HashSet<Persona> mejorCandidato = new HashSet<Persona>();
		// recorro la lista del primer filtro y solicito todos los subconjuntos posibles

		for (int i = 0; i < primerFiltroCumplenRequerimientos.size(); i++) {
			ArrayList<HashSet<Persona>> todosLosSubConjuntosPosibles = obtenerTodosLosSubConjuntosPosibles(
					primerFiltroCumplenRequerimientos.get(i));
			//una vez que tengo todos los subconjuntos posibles en una lista, recorro la lista y actualizo el valor del mejor candidato
			//al subconjunto que cumpla los requisitos estrictos, sean todos compatibles entre si sea mejor calificado que el candidato anterior.
			
			
			for (int j = 0; j < todosLosSubConjuntosPosibles.size(); j++) {
				
				if (conjuntoCumpleRequerimientos(todosLosSubConjuntosPosibles.get(j), req)
						&& Auxiliares.sonCompatibles(todosLosSubConjuntosPosibles.get(j))
						&& esMejorCalificadoQueElSet(todosLosSubConjuntosPosibles.get(j), mejorCandidato)
						) {
					
					mejorCandidato = new HashSet<>(todosLosSubConjuntosPosibles.get(j));
					// voy dejando el mejor candidato, el mas calificado.
				}
				
			}
			//debo repetir el procedimiento con los demas conjuntos en la lista del primer filtrado para garantizar tener al conjunto o subconjunto mejor calificado.
			

		}

		return mejorCandidato;
	}

	/*
	 * Si el conjunto A es mejor calificado que el conjunto B , devuelve True, si no
	 * False, si son iguales, False
	 */
	private boolean esMejorCalificadoQueElSet(HashSet<Persona> A, HashSet<Persona> B) {
		int calificacionA = 0;
		int calificacionB = 0;
		for (Persona iter : A) {
			calificacionA = calificacionA + iter.getCalificacion();
		}
		for (Persona it : B) {
			calificacionB = calificacionB + it.getCalificacion();
		}
		return calificacionA > calificacionB;
	}

	/**
	 * Dado una Persona /Vertice devuevle un set de Personas/Vertices en la que
	 * estan la persona pasada y todos sus vecinos.
	 **/
	private HashSet<Persona> transformarPersonaToSet(Persona input) {
		HashSet<Persona> ret = new HashSet<Persona>();
		ret.add(input);
		for (Arista ar : input.getCompatibles()) {
			ret.add(ar.getPersonaDestino());
		}

		return ret;

	}

	/**
	 * Si el vertice ingresada es un set que cumple los requerimientos solicitados,
	 * TRUE, sino false.
	 **/
	public boolean conjuntoCumpleRequerimientos(Set<Persona> conjunto, Requerimiento req) {
		int countLiderDeProyecto = 0;
		int countArquitecto = 0;
		int countProgramador = 0;
		int countTester = 0;

		// contamos las personas compatibles a la que estamos parado.
		for (Persona p : conjunto) {
			String rol = p.get_rol();
			

			if ("Lider".equals(rol)) {
				countLiderDeProyecto=countLiderDeProyecto+1;
			} else if ("Arquitecto".equals(rol)) {
				countArquitecto=countArquitecto+1;
			} else if ("Programador".equals(rol)) {
				countProgramador=countProgramador+1;
			} else if ("Tester".equals(rol)) {
				countTester=countTester+1;
			}
			
		}
		return req.cumpleRequerimientos(countLiderDeProyecto, countArquitecto, countProgramador, countTester);

	}
	
	
	
	/**
	 * Si el vertice ingresada es un set que supera los requerimientos solicitados,
	 * TRUE, sino false.
	 **/
	public boolean conjuntoSuperaRequerimientos(Set<Persona> conjunto, Requerimiento req) {
		int countLiderDeProyecto = 0;
		int countArquitecto = 0;
		int countProgramador = 0;
		int countTester = 0;

		// contamos las personas compatibles a la que estamos parado.
		for (Persona p : conjunto) {
			String rol = p.get_rol();
			

			if ("Lider".equals(rol)) {
				countLiderDeProyecto=countLiderDeProyecto+1;
			} else if ("Arquitecto".equals(rol)) {
				countArquitecto=countArquitecto+1;
			} else if ("Programador".equals(rol)) {
				countProgramador=countProgramador+1;
			} else if ("Tester".equals(rol)) {
				countTester=countTester+1;
			}
			
		}
		return req.superaRequerimientos(countLiderDeProyecto, countArquitecto, countProgramador, countTester);

	}

	/***
	 * Dado un subconjunto pasado, devuelve un arrayList con todos los subconjuntos
	 * posibles
	 ***/
	private ArrayList<HashSet<Persona>> obtenerTodosLosSubConjuntosPosibles(Set<Persona> conjuntoBase) {
		ArrayList<HashSet<Persona>> subconjuntosAdevolver = new ArrayList<>();
		obtenerSubconjuntosAux(new ArrayList<>(conjuntoBase), 0, new HashSet<>(), subconjuntosAdevolver);
		return subconjuntosAdevolver;

	}

	/// **Metodo auxiliar recursivo para ir agregando subconjuntos a la lista de
	/// subconjuntos.*//
	private void obtenerSubconjuntosAux(ArrayList<Persona> conjuntoBase, int indice, HashSet<Persona> subconjuntoActual,
			ArrayList<HashSet<Persona>> subconjuntosAdevolver) {
		// caso Base
		subconjuntosAdevolver.add(new HashSet<>(subconjuntoActual));
		// paso recursivo.
		for (int i = indice; i < conjuntoBase.size(); i++) {
			subconjuntoActual.add(conjuntoBase.get(i));
			obtenerSubconjuntosAux(conjuntoBase, i + 1, subconjuntoActual, subconjuntosAdevolver);
			subconjuntoActual.remove(conjuntoBase.get(i));
		}

	}

	/** Devuelve el clique maximo del rol ingresado.---NO SE USA **/
	public Set<Persona> Resolver() {
		_mejor = new HashSet<Persona>();
		_actual = new HashSet<Persona>();

		buscarMejorDesde(0);
		return _mejor;
	}

	private void buscarMejorDesde(int vertice) {
		// CASO BASE 1
		if (vertice == grafo.getTamanio() - 1) {// aca esta el error. estas pidiendo un out of bounds
			if (Auxiliares.sonCompatibles(_actual) &&

					_actual.size() > _mejor.size()) {
				_mejor = clonar(_actual);
				return;
			}

		}
		// CASO BASE 2 Backtracking
		if (!Auxiliares.sonCompatibles(_actual))
			return;

		// CASO RECURSIVO
		if (vertice < grafo.getTamanio()) {
			_actual.add(grafo.getPersonaNum(vertice));
			buscarMejorDesde(vertice + 1);

			_actual.remove(grafo.getPersonaNum(vertice));
			buscarMejorDesde(vertice + 1);
		}

	}

	private Set<Persona> clonar(Set<Persona> _actual2) {
		Set<Persona> ret = new HashSet<Persona>();
		for (Persona i : _actual2) {
			ret.add(i);
		}
		return ret;
	}

}
