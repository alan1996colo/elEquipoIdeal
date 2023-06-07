package negocio;

import java.util.ArrayList;
import java.util.HashSet;

import Compatibles.Solver;
import data.GestorArchivos;

public class Negocio {
	GrafoLista personas;
	Requerimiento req;

	public Negocio() {
		this.personas = new GrafoLista();
	}

	public void agregarPersona(String apellido, int calificacion, String _rol) {

		Persona agregar = new Persona(apellido, calificacion, _rol);
		if (personas.contains(agregar)) {
			throw new IllegalArgumentException("No se puede agregar a la misma persona dos veces a la lista");
		} else {
			personas.agregarPersona(agregar);
		}

	};

	/**
	 * carga los requerimientos, si existe algun valor negativo o la cantidad es
	 * superior a los disponibles lanza excepciones
	 ***/
	public void cargarRequerimientos(int cantLiderProyecto, int cantArquitecto, int cantProgramador, int cantTester) {
		if (cantLiderProyecto < 0 || cantArquitecto < 0 || cantProgramador < 0 || cantTester < 0) {
			throw new IllegalArgumentException("No se puede solicitar una cantidad negativa de personas");
		} else if (cantLiderProyecto + cantArquitecto + cantProgramador + cantTester > this.personas.getTamanio()) {

			throw new IllegalArgumentException(
					"La cantidad de personas requeridas es superior a la cantidad de personas disponibles.");
		} else {
			this.req = new Requerimiento(cantLiderProyecto, cantArquitecto, cantProgramador, cantTester);
		}
	}

	/** Calcula el equipo ideal del enunciado del tp usando hilos como se solicitaba. **/
	public String calcularEquipoIdeal() {
		HashSet <Persona> mejor=new HashSet<Persona>();
		Object bloqueo = new Object();
		ArrayList<Thread> hilos = new ArrayList<>(); 
		for(Persona p:this.personas.getPersonas()) {
			GrafoLista temp=new GrafoLista();
			temp.agregarPersona(p);
			Solver solver = new Solver(temp, this.req, mejor, bloqueo);
			Thread t1=new Thread(solver);
			 hilos.add(t1);
			t1.start();
		}
		for (Thread t : hilos) {
	        try {
	            t.join();
	        } catch (InterruptedException e) {
	            System.out.print(e);
	        }
	    }
		

		return mejor.toString();
	}
	
	
	
	

	/**
	 * Reemplaza el grafo actual por el cargado desde el archivo y lo inicializa.
	 **/
	public void cambiarSesion(String fname) {
		GestorArchivos gestor = new GestorArchivos();
		this.personas.setPersonas(gestor.cargarJsonLista(fname));
		this.personas.inicializarAristas();

	}

	public void guardarSesion(String fname) {
		GestorArchivos gestor = new GestorArchivos();
		gestor.generarJSON(fname, this.personas.getPersonas());
	}

	public GrafoLista getPersonas() {
		return this.personas;
	}

	/* Dado dos apellidos pasados, lo busca en el grafo y agrega la arista */
	public boolean agregarCompatibilidad(String apellidoOrigen, String apellidoDestino) {
		if (this.personas == null) {
			throw new RuntimeException("El grafo se encuentra vacio.");
		}

		Persona origen = null;
		Persona destino = null;

		for (Persona persona : this.getPersonas().getPersonas()) {
			if (persona.getApellido().equals(apellidoOrigen)) {
				origen = persona;
			} else if (persona.getApellido().equals(apellidoDestino)) {
				destino = persona;
			}

			if (origen != null && destino != null) {
				origen.agregarCompatible(destino);
				destino.agregarCompatible(origen);
				return true;
			}
		}
		throw new IllegalArgumentException(
				"NO se pudo agregar la compatibilidad , revise si los parametros ingresados son correctos");

	}

	/** Retorna todos los nombres ingresados */
	public String[] getNombres() {

		String[] ret = new String[this.personas.getTamanio()];
		int cont = 0;
		for (Persona iter : this.personas.getPersonas()) {
			ret[cont] = iter.getApellido();
			cont++;

		}
		return ret;
	}

	/******
	 * Busca la persona en el grafo por el nombre, cuando la encuentra devuelve un
	 * arraylist de sus compatibles.
	 * 
	 * @param nombre
	 * @return
	 */
	public ArrayList<String> getCompatiblesDe(String nombre) {

		if (this.personas == null) {
			throw new RuntimeException("No se puede buscar compatibles de un grafo vacio.");

		} else {

			for (Persona persona : this.personas.getPersonas()) {
				if (persona.getApellido().equals(nombre)) {
					if (persona.getCompatibles() != null) {
						ArrayList<String> ret = new ArrayList<String>();
						for (Arista ar : persona.getCompatibles()) {
							ret.add(ar.getPersonaDestinoNombre());
						}
						return ret;
					} else {
						return null;
					}
				}

			}
			throw new RuntimeException("No existe tal persona el el grafo.");
		}

	}

	public boolean agregarinCompatibilidad(String apellidoOrigen, String apellidoDestino) {
		if (this.personas == null) {
			throw new RuntimeException("El grafo se encuentra vacio.");
		}

		Persona origen = null;
		Persona destino = null;

		for (Persona persona : this.getPersonas().getPersonas()) {
			if (persona.getApellido().equals(apellidoOrigen)) {
				origen = persona;
			} else if (persona.getApellido().equals(apellidoDestino)) {
				destino = persona;
			}

			if (origen != null && destino != null) {
				origen.quitarCompatible(destino);
				destino.quitarCompatible(origen);
				return true;
			}
		}

		return false;
	}
}
