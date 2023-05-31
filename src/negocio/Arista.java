package negocio;

import java.io.Serializable;

public class Arista implements Serializable {

	private static final long serialVersionUID = 1L;
	transient private Persona PersonaOrigen;
	transient private Persona PersonaDestino;
	private String personaOrigenNombre;
	private String personaDestinoNombre;

	public Arista(Persona PersonaOrigen, Persona PersonaDestino) {
		this.PersonaOrigen = PersonaOrigen;
		this.PersonaDestino = PersonaDestino;
		this.personaOrigenNombre = PersonaOrigen.getApellido();
		this.personaDestinoNombre = PersonaDestino.getApellido();
	}

	public Persona getPersonaOrigen() {
		return PersonaOrigen;
	}

	public Persona getPersonaDestino() {
		return PersonaDestino;
	}

	public String getPersonaOrigenNombre() {
		return this.personaOrigenNombre;
	}

	public String getPersonaDestinoNombre() {
		return this.personaDestinoNombre;
	}

	public String toString() {
		return this.personaOrigenNombre + "<-->" + this.personaDestinoNombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((PersonaDestino == null) ? 0 : PersonaDestino.hashCode());
		result = prime * result + ((PersonaOrigen == null) ? 0 : PersonaOrigen.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Arista other = (Arista) obj;
		if (PersonaOrigen == null) {
			if (other.PersonaOrigen != null)
				return false;
		} else if (!PersonaOrigen.equals(other.PersonaOrigen))
			return false;
		if (PersonaDestino == null) {
			if (other.PersonaDestino != null)
				return false;
		} else if (!PersonaDestino.equals(other.PersonaDestino))
			return false;
		return true;
	}

}