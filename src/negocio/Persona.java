package negocio;


import java.util.HashSet;
import java.util.Set;

public class Persona {
	
	private String _apellido;
	private int _calificacion;
	private String _rol;
	transient private HashSet<Arista> compatibles = new HashSet<>();


	
	
	public Persona( String apellido, int calificacion, String _rol)
	{
		
		this._apellido=apellido;
		this._calificacion=calificacion;
		this._rol=_rol;
	}
	


	// devuelvo los vecinos de un nodo
	public Set<Arista> getCompatibles() {
		return compatibles;
	}
	
public void agregarCompatible( Persona nodoDestino) {
		
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
		return "Persona  apellido = " + _apellido + ", calificacion = " + _calificacion
				+ ", rol = " + _rol + "]";
	}
	

	
	
	

}
