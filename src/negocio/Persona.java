package negocio;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	private String _apellido;
	private int _calificacion;
	private String _rol;
	private HashSet<Arista> compatibles = new HashSet<>();

	public Persona(String apellido, int calificacion, String _rol) {

		this._apellido = apellido;
		this._calificacion = calificacion;
		this._rol = _rol;
	}

	// devuelvo los vecinos de un nodo
	public Set<Arista> getCompatibles() {
		return compatibles;
	}

	// Hay que testear esto.
	public boolean estaPersona(Persona persona) {
		return this.compatibles.contains(new Arista(this, persona));
	}

	public void agregarCompatible(Persona nodoDestino) {

		if (this.compatibles.contains(new Arista(this, nodoDestino))) {
			throw new IllegalArgumentException("La arista ya existe");
		}
		compatibles.add(new Arista(this, nodoDestino));

	}

	public String getApellido() {
		return _apellido;
	}

	public void setApellido(String _apellido) {
		this._apellido = _apellido;
	}

	public int getCalificacion() {
		return _calificacion;
	}

	public void setCalificacion(int _calificacion) {
		this._calificacion = _calificacion;
	}

	public String get_rol() {
		return _rol;
	}

	@Override
	public String toString() {
		return "Persona  apellido = " + _apellido + ", calificacion = " + _calificacion + ", rol = " + _rol + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this._apellido == null) ? 0 : this._apellido.hashCode());
		result = prime * result + ((this._calificacion == 0) ? 0 : this._calificacion);
		result = prime * result + ((this._rol == null) ? 0 : this._rol.hashCode());

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
		final Persona other = (Persona) obj;
		if (_apellido == null) {
			if (other._apellido != null)
				return false;
		} else if (!_apellido.equals(other._apellido))
			return false;
		if (_rol == null) {
			if (other._rol != null)
				return false;
		} else if (!_rol.equals(other._rol))
			return false;

		if (_calificacion == 0) {
			if (other._calificacion != 0)
				return false;
		} else if (_calificacion != other._calificacion) {
			return false;
		}

		return true;
	}

	public boolean quitarCompatible(Persona destino) {
		if (destino == null) {
			throw new IllegalArgumentException("no se puede eliminar una vertice nulo");
		}
		return this.compatibles.remove(new Arista(this, destino));
	}

}
