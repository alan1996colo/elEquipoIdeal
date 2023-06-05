package Compatibles;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.Test;

import negocio.GrafoLista;
import negocio.Persona;

public class SolverTest {

	@Test
	public void SonCompatiblesTest() {
		Solver solver = new Solver(trianguloConAntena());
		Set<Persona>mejor=solver.Resolver();
		
//		Persona[]esperado= {personas[1]};
//		Assert.iguales(esperado, mejor);
	}
	
	
	private GrafoLista trianguloConAntena() {
		GrafoLista grafo = new GrafoLista(personas());
		grafo.agregarArista(grafo.getPersonaNum(0), grafo.getPersonaNum(1));
		grafo.agregarArista(grafo.getPersonaNum(0), grafo.getPersonaNum(2));
		grafo.agregarArista(grafo.getPersonaNum(1), grafo.getPersonaNum(2));
		grafo.agregarArista(grafo.getPersonaNum(3), grafo.getPersonaNum(1));
		return grafo;
	}
	public ArrayList<Persona> personas() {
		ArrayList<Persona> personas = new ArrayList<Persona>();
		personas.add(new Persona("Flores",1,"Tester"));
		personas.add(new Persona("Gomez",3,"Lider"));
		personas.add(new Persona("Perez",4,"Lider"));
		personas.add(new Persona("Borja",5,"Lider"));
		personas.add(new Persona("Alex",6,"Lider"));
		
		return personas;
		
	}
	

}
