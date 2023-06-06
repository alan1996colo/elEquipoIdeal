package Compatibles;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import negocio.GrafoLista;
import negocio.Persona;
import negocio.Requerimiento;

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
	@Test
	public void conjuntoCumpleRequerimientosTest() {
		HashSet<Persona> conjunto=new HashSet<Persona>();
		conjunto.add(new Persona("Flores",1,"Tester"));
		conjunto.add(new Persona("Gomez",3,"Lider"));
		conjunto.add(new Persona("Perez",4,"Arquitecto"));
		conjunto.add(new Persona("Borja",5,"Programador"));
		
		Requerimiento req=new Requerimiento(1,1,1,1);
		Solver solver=new Solver(new GrafoLista ());		
		
		assertTrue(solver.conjuntoCumpleRequerimientos(conjunto, req));
		
		
	}
	@Test
	public void calcularTest() {
		GrafoLista grafo= new GrafoLista ();
		Persona Alan= new Persona("Alan",5,"Lider");
		Persona Hernan= new Persona("Hernan",1,"Programador");
		Persona Alejandro=new Persona("Alejandro",1,"Tester");
		Persona Ruth = new Persona("Ruth",3,"Arquitecto");
		
		grafo.agregarPersona(Ruth);
		grafo.agregarPersona(Hernan);
		grafo.agregarPersona(Alejandro);
		grafo.agregarPersona(Alan);
		
		grafo.agregarArista(Ruth, Hernan);
		grafo.agregarArista(Ruth, Alejandro);
		grafo.agregarArista(Ruth, Alan);
		
		grafo.agregarArista(Hernan, Alejandro);
		grafo.agregarArista(Hernan, Alan);
		
		grafo.agregarArista(Alejandro, Alan);
		Requerimiento req=new Requerimiento(1,1,1,1);
		Solver solver=new Solver(grafo);	
		
		assertTrue(solver.calcular(req).contains(Alan)&&solver.calcular(req).contains(Hernan)&&solver.calcular(req).contains(Alejandro)&&solver.calcular(req).contains(Ruth));
	}
	@Test

	public void calcular2gruposTesterYprogramador() {
		GrafoLista grafo= new GrafoLista ();
		Persona Alan= new Persona("Alan",5,"Tester");
		Persona Hernan= new Persona("Hernan",1,"Programador");
		Persona Alejandro=new Persona("Alejandro",1,"Tester");
		Persona Ruth = new Persona("Ruth",3,"Programador");
		
		grafo.agregarPersona(Ruth);
		grafo.agregarPersona(Hernan);
		grafo.agregarPersona(Alejandro);
		grafo.agregarPersona(Alan);
		
		grafo.agregarArista(Ruth, Hernan);
		grafo.agregarArista(Ruth, Alejandro);
		grafo.agregarArista(Ruth, Alan);
		
		grafo.agregarArista(Hernan, Alejandro);
		grafo.agregarArista(Hernan, Alan);
		
		grafo.agregarArista(Alejandro, Alan);
		Requerimiento req=new Requerimiento(0,0,1,1);
		Solver solver=new Solver(grafo);	
		
		assertTrue(solver.calcular(req).contains(Alan)&&solver.calcular(req).contains(Ruth));

		
		
	}
	@Test
	public void calcularNoCompatibleNoEstaEnElConjunto() {
		GrafoLista grafo= new GrafoLista ();
		Persona Alan= new Persona("Alan",5,"Lider");
		Persona Hernan= new Persona("Hernan",1,"Programador");
		Persona Alejandro=new Persona("Alejandro",1,"Tester");
		Persona Ruth = new Persona("Ruth",3,"Arquitecto");
		Persona vanesa = new Persona("vanesa",5,"Tester");
		
		grafo.agregarPersona(Ruth);
		grafo.agregarPersona(Hernan);
		grafo.agregarPersona(Alejandro);
		grafo.agregarPersona(Alan);
		grafo.agregarPersona(vanesa);
		
		grafo.agregarArista(Ruth, Hernan);
		grafo.agregarArista(Ruth, Alejandro);
		grafo.agregarArista(Ruth, Alan);
		grafo.agregarArista(vanesa, Alan);

		
		grafo.agregarArista(Hernan, Alejandro);
		grafo.agregarArista(Hernan, Alan);
		
		grafo.agregarArista(Alejandro, Alan);
		Requerimiento req=new Requerimiento(1,1,1,1);
		Solver solver=new Solver(grafo);	
		
		assertFalse(solver.calcular(req).contains(vanesa));
	}
}
