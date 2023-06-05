package Compatibles;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import negocio.GrafoLista;
import negocio.Persona;

public class AuxiliaresTest {

	@Test
	public void todosAisladosTest() {
		GrafoLista grafo = new GrafoLista(personas());
		
		Set<Persona> conjunto = new HashSet<Persona>();
		conjunto.add(new Persona("Flores",1,"Tester"));
		conjunto.add(new Persona("Gomez",3,"Lider"));
		conjunto.add(new Persona("Perez",4,"Lider"));
		
		assertFalse(Auxiliares.sonCompatibles( conjunto));
		
	}
	@Test
	public void vacioTest() {
		GrafoLista grafo = new GrafoLista(personas());
		
		Set<Persona> conjunto = new HashSet<Persona>();
		assertTrue(Auxiliares.sonCompatibles( conjunto));
		
	}
	
	@Test
	public void trianguloTest() {
		GrafoLista grafo = trianguloConAntena();
		
		
		Set<Persona> conjunto = new HashSet<Persona>();
		conjunto.add(new Persona("Flores",1,"Tester"));
		conjunto.add(new Persona("Gomez",3,"Lider"));
		conjunto.add(new Persona("Perez",4,"Lider"));
		
		assertTrue(Auxiliares.sonCompatibles( conjunto));
		
	}

	@Test
	public void noTrianguloTest() {
		GrafoLista grafo = trianguloConAntena();
		
		
		Set<Persona> conjunto = new HashSet<Persona>();
		conjunto.add(new Persona("Flores",1,"Tester"));
		conjunto.add(new Persona("Gomez",3,"Lider"));
		conjunto.add(new Persona("Borja",5,"Lider"));
		
		assertFalse(Auxiliares.sonCompatibles( conjunto));
		
	}
	@Test
	public void aristaTest() {
		GrafoLista grafo = trianguloConAntena();
		
		
		Set<Persona> conjunto = new HashSet<Persona>();
		conjunto.add(new Persona("Flores",1,"Tester"));
		conjunto.add(new Persona("Gomez",3,"Lider"));
		
		
		assertTrue(Auxiliares.sonCompatibles( conjunto));
		
	}
	

	
	private ArrayList<Persona> personas() {
		ArrayList<Persona> personas = new ArrayList<Persona>();
		personas.add(new Persona("Flores",1,"Tester"));
		personas.add(new Persona("Gomez",3,"Lider"));
		personas.add(new Persona("Perez",4,"Lider"));
		personas.add(new Persona("Borja",5,"Lider"));
		personas.add(new Persona("Alex",6,"Lider"));
		
		return personas;
		
	}
	private GrafoLista trianguloConAntena() {
		GrafoLista grafo = new GrafoLista(personas());
		grafo.agregarArista(grafo.getPersonaNum(0), grafo.getPersonaNum(1));
		grafo.agregarArista(grafo.getPersonaNum(0), grafo.getPersonaNum(2));
		grafo.agregarArista(grafo.getPersonaNum(1), grafo.getPersonaNum(2));
		grafo.agregarArista(grafo.getPersonaNum(3), grafo.getPersonaNum(1));
		return grafo;
	}
}
