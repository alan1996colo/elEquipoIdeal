package negocio;

public class mainDePrueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	Rol Lider = new Rol("Lider");
	Rol Arquitecto = new Rol("Arquitecto");
	Rol Programador = new Rol("Programador");
	Rol Tester = new Rol("Tester");
	
	Persona A = new Persona(175,"Flores",1,Tester);
	Persona B = new Persona(176,"Perez",2,Lider);
	A.agregarIncompatibles(B);
	System.out.println(A.getListaIncompatibles().get(0).toString());
			
		
	}

}
