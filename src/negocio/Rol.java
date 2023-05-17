package negocio;
import java.util.*;


public class Rol {
	private String _rol;
	private ArrayList<Rol> listaRoles=new ArrayList<>();
	
	public Rol(String rol)
	{
		this._rol=rol;
		listaRoles.add(this);
	}

	
    public String getRol() {
        return this._rol;
    }
    
    public ArrayList<Rol> getListaRoles() {
        return this.listaRoles;
    }


	@Override
	public String toString() {
		return "Rol [rol = " + _rol + "]";
	}
    
    
	
}
