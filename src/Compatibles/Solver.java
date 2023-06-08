package Compatibles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import negocio.Arista;
import negocio.GrafoLista;
import negocio.Persona;
import negocio.Requerimiento;

public class Solver implements Runnable {
	private GrafoLista grafo;
	private HashSet<Persona> _mejor;
	private Requerimiento _req;
	private Object bloqueo;

	public Set<Persona> get_mejor() {
		return _mejor;
	}

	public void set_mejor(HashSet<Persona> _mejor) {
		this._mejor = _mejor;
	}

	/**
	 * Constructor con hilos, Recibe como entrada un grafo, los requerimientos,el
	 * mejor conjunto conocido, y un objeto de bloqueo para manejar concurrencia de
	 * los hilos
	 */
	public Solver(GrafoLista grafoLista, Requerimiento req, HashSet<Persona> mejor, Object bloqueo) {
		grafo = grafoLista;
		_req = req;
		this._mejor = mejor;
		this.bloqueo = bloqueo;

	}

	/** Constructor sin hilos, recibe como entrada un grafo y requerimiento **/
	public Solver(GrafoLista grafoLista, Requerimiento req) {
		grafo = grafoLista;
		_req = req;

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
	 * subconjunto mayor calificado repitiendo el ciclo para todos los conjuntos del
	 * filtrado inicial..
	 ***/

	public HashSet<Persona> calcular(Requerimiento req) {
		ArrayList<HashSet<Persona>> primerFiltroSuperanRequerimientos = new ArrayList<>();
		filtrarLosQueSuperanRequerimiento(req, primerFiltroSuperanRequerimientos);

		HashSet<Persona> mejorCandidato = new HashSet<>();
		for (int i = 0; i < primerFiltroSuperanRequerimientos.size(); i++) {
			ArrayList<HashSet<Persona>> todosLosSubConjuntosPosibles = obtenerTodosLosSubConjuntosPosibles(
					primerFiltroSuperanRequerimientos.get(i));

			for (int j = 0; j < todosLosSubConjuntosPosibles.size(); j++) {
				HashSet<Persona> subconjuntoActual = todosLosSubConjuntosPosibles.get(j);

				if (conjuntoCumpleRequerimientos(subconjuntoActual, req) && Auxiliares.sonCompatibles(subconjuntoActual)
						&& esMejorCalificadoQueElSet(subconjuntoActual, mejorCandidato)) {
					mejorCandidato = new HashSet<>(subconjuntoActual);
				}
			}
		}

		return mejorCandidato;
	}

	/**
	 * Recorre los vertices/personas del grafo y agrega a la lista solo los
	 * conjuntos de personas que superan los requerimientos
	 */
	private void filtrarLosQueSuperanRequerimiento(Requerimiento req,
			ArrayList<HashSet<Persona>> primerFiltroSuperanRequerimientos) {
		for (Persona iter : grafo.getPersonas()) {

			if (conjuntoSuperaRequerimientos(transformarPersonaToSet(iter), req)) {
				primerFiltroSuperanRequerimientos.add(transformarPersonaToSet(iter));
			}
		}
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
	 * Si el conjunto ingresada cumple (1==1) los requerimientos solicitados, TRUE,
	 * sino false.
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
				countLiderDeProyecto = countLiderDeProyecto + 1;
			} else if ("Arquitecto".equals(rol)) {
				countArquitecto = countArquitecto + 1;
			} else if ("Programador".equals(rol)) {
				countProgramador = countProgramador + 1;
			} else if ("Tester".equals(rol)) {
				countTester = countTester + 1;
			}

		}
		return req.cumpleRequerimientos(countLiderDeProyecto, countArquitecto, countProgramador, countTester);

	}

	/**
	 * Si el conjunto ingresada supera (1>=1)los requerimientos solicitados, TRUE,
	 * sino false.
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
				countLiderDeProyecto = countLiderDeProyecto + 1;
			} else if ("Arquitecto".equals(rol)) {
				countArquitecto = countArquitecto + 1;
			} else if ("Programador".equals(rol)) {
				countProgramador = countProgramador + 1;
			} else if ("Tester".equals(rol)) {
				countTester = countTester + 1;
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

	/**
	 * Metodo auxiliar recursivo para ir agregando subconjuntos a la lista de
	 * subconjuntos.
	 **/
	private void obtenerSubconjuntosAux(ArrayList<Persona> conjuntoBase, int indice, HashSet<Persona> subconjuntoActual,
			ArrayList<HashSet<Persona>> subconjuntosAdevolver) {
		// caso Base
		subconjuntosAdevolver.add(new HashSet<>(subconjuntoActual));
		// paso recursivo.
		for (int i = indice; i < conjuntoBase.size(); i++) {
			subconjuntoActual.add(conjuntoBase.get(i));
			obtenerSubconjuntosAux(conjuntoBase, i + 1, subconjuntoActual, subconjuntosAdevolver);
			subconjuntoActual.remove(conjuntoBase.get(i));// backtracking
		}

	}

	/****
	 * En esta heuristica se va intentar resolver el problema del enunciado del tp,
	 * pero no se usara el metodo de backTracking que genera todos los subconjuntos
	 * posibles Solo revisara los subconjuntos iniciales y se quedara con el que
	 * todos sean compatibles con todos y superen requerimientos.
	 */
	public HashSet<Persona> resolverHeuristica(Requerimiento req) {

		ArrayList<HashSet<Persona>> primerFiltroSuperanRequerimientos = new ArrayList<>();
		filtrarLosQueSuperanRequerimiento(req, primerFiltroSuperanRequerimientos);

		HashSet<Persona> mejorCandidato = new HashSet<>();
		for (int i = 0; i < primerFiltroSuperanRequerimientos.size(); i++) {

			if (Auxiliares.sonCompatibles(primerFiltroSuperanRequerimientos.get(i))
					&& esMejorCalificadoQueElSet(primerFiltroSuperanRequerimientos.get(i), mejorCandidato)) {
				mejorCandidato = new HashSet<>(primerFiltroSuperanRequerimientos.get(i));
				if (isTheBestSet(primerFiltroSuperanRequerimientos.get(i), req)) {
					reducirConjunto(req, mejorCandidato);
					return mejorCandidato;

				}
			}

		}
		reducirConjunto(req, mejorCandidato);
		return mejorCandidato;

	}

	/** Se va usar para el algoritmo euristico. */
	private boolean isTheBestSet(Set<Persona> conjunto, Requerimiento req) {
		int maximoPosible = req.getCantArquitecto() * 5 + req.getCantLiderProyecto() * 5 + req.getCantProgramador() * 5
				+ req.getCantTester() * 5;
		int calificacionConjunto = 0;
		for (Persona p : conjunto) {
			calificacionConjunto = calificacionConjunto + p.getCalificacion();
		}
		return maximoPosible <= calificacionConjunto;

	}

	/**
	 * Reduce el conjunto pasado a un conjunto que cumpla estrictamente los
	 * requerimientos.
	 **/
	private void reducirConjunto(Requerimiento req, HashSet<Persona> mejorCandidato) {

		int countLiderDeProyecto = 0;
		int countArquitecto = 0;
		int countProgramador = 0;
		int countTester = 0;

		Iterator<Persona> iterator = mejorCandidato.iterator();
		while (iterator.hasNext()) {
			Persona p = iterator.next();
			String rol = p.get_rol();

			if ("Lider".equals(rol) && countLiderDeProyecto < req.getCantLiderProyecto()) {
				countLiderDeProyecto = countLiderDeProyecto + 1;
			} else if ("Arquitecto".equals(rol) && countArquitecto < req.getCantArquitecto()) {
				countArquitecto = countArquitecto + 1;
			} else if ("Programador".equals(rol) && countProgramador < req.getCantProgramador()) {
				countProgramador = countProgramador + 1;
			} else if ("Tester".equals(rol) && countTester < req.getCantTester()) {
				countTester = countTester + 1;
			}

			else {
				iterator.remove();

			}

		}
	}

	@Override
	public void run() {
		HashSet<Persona> actual = calcular(this._req);

		synchronized (bloqueo) {
			if (esMejorCalificadoQueElSet(actual, this._mejor)) {
				this._mejor.clear(); // Limpiar el contenido actual de _mejor
				this._mejor.addAll(actual); // Agregar los elementos de actual a _mejor
			}
		}
	}

}
