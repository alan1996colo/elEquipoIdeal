package negocio;

import java.util.ArrayList;

public class Persona {
	private int _legajo;
	private String _apellido;
	private int _calificacion;
	private Rol _rol;
	private ArrayList<Persona> _listaIncompatibles = new ArrayList<Persona>();
	
	
	public Persona(int legajo, String apellido, int calificacion, Rol _rol)
	{
		this._legajo=legajo;
		this._apellido=apellido;
		this._calificacion=calificacion;
		this._rol=_rol;
	}
	
	public void agregarIncompatibles(Persona persona)
	{
		this._listaIncompatibles.add(persona);
		
	}
	public ArrayList<Persona> getListaIncompatibles() {
		return _listaIncompatibles;
	}
	
	public int geLegajo() {
		return _legajo;
	}

	public void setLegajo(int _legajo) {
		this._legajo = _legajo;
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


	@Override
	public String toString() {
		return "Persona [legajo = " + _legajo + ", apellido = " + _apellido + ", calificacion = " + _calificacion
				+ ", rol = " + _rol + "]";
	}
	

	
	
	

}
