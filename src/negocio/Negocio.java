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
	
	/*Dado dos apellidos pasados, lo busca en el grafo y agrega la arista
	 * Aun NO TESTEADO, FALTA HACER TEST.*/
	public boolean agregarCompatibilidad(String apellidoOrigen, String apellidoDestino) {
		if(this.personas==null) {throw new RuntimeException("El grafo se encuentra vacio.");}
		Persona temp=new Persona(null,0,null);
		for(Persona iter:this.getPersonas().getPersonas()) {
			if(iter.getApellido().equals(apellidoOrigen)||iter.getApellido().equals(apellidoDestino)) {
				temp=iter;
			}
			if((iter.getApellido().equals(apellidoOrigen)||iter.getApellido().equals(apellidoDestino)) && iter!=temp) {
				iter.agregarCompatible(temp);
				temp.agregarCompatible(iter);
				return true;
			}
		}
		return false;
	}
	
	/**Retorna todos los nombres ingresados*/
	public String[] getNombres() {
		
		String[]ret= new String[this.personas.getTamanio()];
		int cont=0;
		for(Persona iter:this.personas.getPersonas()) {
			ret[cont]=iter.getApellido();
			cont++;
			
		}
		return ret;
	}
}
