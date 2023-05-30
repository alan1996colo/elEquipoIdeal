package negocio;

import java.io.Serializable;
import java.util.*;

public class GrafoLista implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<Persona> Personas;// lista de Personas

	public GrafoLista() {// OK
		this.Personas = new ArrayList<>();
	}

	public GrafoLista(ArrayList<Persona> Personas) { // OK// construir grafo lista con una lista de Personas ya creada.
		this.Personas = Personas;
	}

	/* Cambia la lista de Personas a la lista pasada */
	public void setPersonas(ArrayList<Persona> Personas) { // OK
		this.Personas = Personas;
	}

	public Persona getPersonaNum(int n) { // OK
		return this.Personas.get(n);
	}

	public void agregarPersona(Persona Persona) {// OK
		if (this.Personas.contains(Persona)) {
			throw new IllegalArgumentException("El Persona ya estaba incluido en el grafo");
		} else {
			Personas.add(Persona);
		}
	}



	/*
	 * Intenta agreagar la arista entre dos Personas compatibles
	 */
	public void agregarArista(Persona PersonaOrigen, Persona PersonaDestino) { // OK
		if (PersonaOrigen.equals(PersonaDestino)) {
			throw new IllegalArgumentException("No se permiten bucles");
		} else {
			if(PersonaOrigen.getCompatibles()==null) {
			
			}
			if(PersonaDestino.getCompatibles()==null) {
				
			}
		
			PersonaOrigen.agregarCompatible(PersonaDestino);
			PersonaDestino.agregarCompatible( PersonaOrigen);
		}
	}

	// devuelvo los Personas del grafo
	public List<Persona> getPersonas() {// OK
		return Personas;
	}

	public int getTamanio() {// OK 
		return this.Personas.size();
	}

	// cambiar nombre a getVecinos mas tarde
	public Set<Arista> getVecinos(int i) { // NO SE USA
		return this.Personas.get(i).getCompatibles();

	}


	/**
	 * True =contiene al Persona en su lista False= el Persona no está
	 **/
	public boolean contains(Persona Persona) { // NO SE USA
		for (Persona iter : this.Personas) {
			if (iter.equals(Persona)) {
				return true;
			}
		}
		return false;
	}



	

	public void mostrarGrafo() { // NO SE USA
		// TODO Auto-generated method stub
		for (Persona iter : this.Personas) {
			System.out.println(iter.toString());
		}

	}

	public void mostrarGrafoConAristas() { // OK
		for (Persona iter : this.Personas) {
			System.out.println(iter.getApellido() + " :");
		//	iter.mostrarAristas(); <--desarrollar
		}
	}

	public boolean sonVecinos(Persona persona1, Persona persona2) {
		
		for(Persona per: Personas) {
			if(per.equals(persona1)) {
				return per.estaPersona(persona2);
			}
		}
		
		return false;
	}

}
