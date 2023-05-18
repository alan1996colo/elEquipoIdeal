package negocio;

public class Negocio {
	GrafoLista personas;
	public Negocio() {
		this.personas=new GrafoLista();
	}

	public void agregarPersona( String apellido, int calificacion, String _rol) {
		
		Persona agregar=new Persona(apellido,calificacion,_rol);
		if(personas.contains(agregar)) {throw new IllegalArgumentException("No se puede agregar a la misma persona dos veces a la lista");}
		else {	personas.agregarPersona(agregar);}
	
		
	};
	public GrafoLista getPersonas() {
		return this.personas;
	}
}
