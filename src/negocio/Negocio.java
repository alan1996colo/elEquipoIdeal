package negocio;

import java.util.ArrayList;

import data.GestorArchivos;

public class Negocio {
	GrafoLista personas;

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

		return false;
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
