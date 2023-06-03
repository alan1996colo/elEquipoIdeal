package Compatibles;

import java.util.ArrayList;
import java.util.Set;

import negocio.GrafoLista;
import negocio.Persona;

public class Prueba {
	
	private static ArrayList<Persona> esperados(){
		ArrayList<Persona> esperados=personas();
		ArrayList<Persona> ret= new ArrayList<Persona>();
		for(int i=0; i<esperados.size();i++)
			if(esperados.get(i).get_rol()=="Lider") {
				ret.add(esperados.get(i));
			}
		
		return ret;
	}
	
	
	private static GrafoLista trianguloConAntena() {
		GrafoLista grafo = new GrafoLista(personas());
		grafo.agregarArista(grafo.getPersonaNum(0), grafo.getPersonaNum(1));
//		grafo.agregarArista(grafo.getPersonaNum(0), grafo.getPersonaNum(2));
//		grafo.agregarArista(grafo.getPersonaNum(1), grafo.getPersonaNum(2));
//		grafo.agregarArista(grafo.getPersonaNum(3), grafo.getPersonaNum(1));
		return grafo;
	}
	public static ArrayList<Persona> personas() {
		ArrayList<Persona> personas = new ArrayList<Persona>();
		personas.add(new Persona("Flores",5,"Lider"));
		personas.add(new Persona("Gomez",4,"Lider"));
//		personas.add(new Persona("Perez",4,"Lider"));
//		personas.add(new Persona("Borja",5,"Tester"));

		return personas;
		
	}

	public static void main(String[] args) {
		
 			Solver solver = new Solver(trianguloConAntena());
			Set<Persona>mejor=solver.Resolver("Lider");   // verifico que me arroja el codigo de busqueda
			//ArrayList<Persona>esperado=esperados();
			
			System.out.println(mejor);
		

		
		
		
	}

}