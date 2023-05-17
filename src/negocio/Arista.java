package negocio;

import java.io.Serializable;

public class Arista implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Persona PersonaOrigen;
	private Persona PersonaDestino;
    

    public Arista(Persona PersonaOrigen, Persona PersonaDestino) {
    	this.PersonaOrigen=PersonaOrigen;
        this.PersonaDestino = PersonaDestino;
        
    }
    public Persona getPersonaOrigen() {
        return PersonaOrigen;
    }

    public Persona getPersonaDestino() {
        return PersonaDestino;
    }

    public String toString(){
    	return this.PersonaOrigen.getApellido()+"<-->"+this.PersonaDestino.getApellido();
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
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final Arista other = (Arista)obj;
        if (!PersonaDestino.equals(other.PersonaDestino)) return false;
        if(!PersonaOrigen.equals(other.PersonaOrigen)) return false;
       
        return true;
    }

    
    
}