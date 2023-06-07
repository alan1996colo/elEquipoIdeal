package Compatibles;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;

import negocio.GrafoLista;
import negocio.Persona;
import negocio.Requerimiento;

public class SolverTest {


	
	
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
		Solver solver=new Solver(new GrafoLista (),req,null,null);		
		
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
		Solver solver=new Solver(grafo,req);	
		
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
		Solver solver=new Solver(grafo,req);	
		
		assertTrue(solver.calcular(req).contains(Alan)&&solver.calcular(req).contains(Ruth));

		
		
	}
	@Test
	public void calcularNoCompatibleNoEstaEnElConjunto() {
		GrafoLista grafo= new GrafoLista ();
		Persona Alan= new Persona("Alan",5,"Lider");
		Persona Hernan= new Persona("Hernan",1,"Programador");
		Persona Alejandro=new Persona("Alejandro",1,"Tester");
		Persona Ruth = new Persona("Ruth",3,"Arquitecto");
		Persona noCompatible = new Persona("vanesa",5,"Tester");
		
		grafo.agregarPersona(Ruth);
		grafo.agregarPersona(Hernan);
		grafo.agregarPersona(Alejandro);
		grafo.agregarPersona(Alan);
		grafo.agregarPersona(noCompatible);
		
		grafo.agregarArista(Ruth, Hernan);
		grafo.agregarArista(Ruth, Alejandro);
		grafo.agregarArista(Ruth, Alan);
		grafo.agregarArista(noCompatible, Alan);

		
		grafo.agregarArista(Hernan, Alejandro);
		grafo.agregarArista(Hernan, Alan);
		
		grafo.agregarArista(Alejandro, Alan);
		Requerimiento req=new Requerimiento(1,1,1,1);
		Solver solver=new Solver(grafo,req);	
		
		assertFalse(solver.calcular(req).contains(noCompatible));
	}
	
	@Test
	public void calcularRequisito0ConjuntoVacio() {
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
		Requerimiento req=new Requerimiento(0,0,0,0);
		Solver solver=new Solver(grafo,req);	
		
		assertEquals(solver.calcular(req).size(),0);
	}
	@Test
	public void calcular1requerimientoElijeAlMejor() {
		GrafoLista grafo= new GrafoLista ();
		Persona Alan= new Persona("Alan",5,"Lider");
		Persona Hernan= new Persona("Hernan",4,"Lider");
		Persona Alejandro=new Persona("Alejandro",3,"Lider");
		Persona Ruth = new Persona("Ruth",2,"Lider");
		Persona vanesa = new Persona("vanesa",1,"Lider");
		
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
		Requerimiento req=new Requerimiento(1,0,0,0);
		Solver solver=new Solver(grafo,req);	
		
		assertTrue(solver.calcular(req).contains(Alan)&&solver.calcular(req).size()==1);
	}
	@Test
	public void calcular1requerimientoNadieCompatibleElijeAlMejor() {
		GrafoLista grafo= new GrafoLista ();
		Persona Alan= new Persona("Alan",5,"Lider");
		Persona Hernan= new Persona("Hernan",4,"Lider");
		Persona Alejandro=new Persona("Alejandro",3,"Lider");
		Persona Ruth = new Persona("Ruth",2,"Lider");
		Persona vanesa = new Persona("vanesa",1,"Lider");
		
		grafo.agregarPersona(Ruth);
		grafo.agregarPersona(Hernan);
		grafo.agregarPersona(Alejandro);
		grafo.agregarPersona(Alan);
		grafo.agregarPersona(vanesa);
	
		Requerimiento req=new Requerimiento(1,0,0,0);
		Solver solver=new Solver(grafo,req);	
		
		assertTrue(solver.calcular(req).contains(Alan)&&solver.calcular(req).size()==1);
	}
	@Test
	public void resolverHeuristica1requerimientoNadieCompatibleElijeAlMejor() {
		GrafoLista grafo= new GrafoLista ();
		Persona Alan= new Persona("Alan",5,"Lider");
		Persona Hernan= new Persona("Hernan",4,"Lider");
		Persona Alejandro=new Persona("Alejandro",3,"Lider");
		Persona Ruth = new Persona("Ruth",2,"Lider");
		Persona vanesa = new Persona("vanesa",1,"Lider");
		
		grafo.agregarPersona(Ruth);
		grafo.agregarPersona(Hernan);
		grafo.agregarPersona(Alejandro);
		grafo.agregarPersona(Alan);
		grafo.agregarPersona(vanesa);
	
		Requerimiento req=new Requerimiento(1,0,0,0);
		Solver solver=new Solver(grafo,req);	
		assertTrue(solver.resolverHeuristica(req).contains(Alan)&&solver.resolverHeuristica(req).size()==1);
	}
	
	@Test
	public void resolverHeuristicaRequisito0ConjuntoVacio() {
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
		Requerimiento req=new Requerimiento(0,0,0,0);
		Solver solver=new Solver(grafo,req);	
		
		assertEquals(solver.resolverHeuristica(req).size(),0);
	}
	
	
	@Test
	public void resolverHeuristicaNoCompatibleNoEstaEnElConjunto() {
		GrafoLista grafo= new GrafoLista ();
		Persona Alan= new Persona("Alan",5,"Lider");
		Persona Hernan= new Persona("Hernan",1,"Programador");
		Persona Alejandro=new Persona("Alejandro",1,"Tester");
		Persona Ruth = new Persona("Ruth",3,"Arquitecto");
		Persona noCompatible = new Persona("vanesa",5,"Tester");
		
		grafo.agregarPersona(Ruth);
		grafo.agregarPersona(Hernan);
		grafo.agregarPersona(Alejandro);
		grafo.agregarPersona(Alan);
		grafo.agregarPersona(noCompatible);
		
		grafo.agregarArista(Ruth, Hernan);
		grafo.agregarArista(Ruth, Alejandro);
		grafo.agregarArista(Ruth, Alan);
		grafo.agregarArista(noCompatible, Alan);

		
		grafo.agregarArista(Hernan, Alejandro);
		grafo.agregarArista(Hernan, Alan);
		
		grafo.agregarArista(Alejandro, Alan);
		Requerimiento req=new Requerimiento(1,1,1,1);
		Solver solver=new Solver(grafo,req);	
		
		assertFalse(solver.resolverHeuristica(req).contains(noCompatible));
	}
	
	@Test
	public void resolverHeuristicaTestSimple() {
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
		Solver solver=new Solver(grafo,req);	
		
		assertTrue(solver.resolverHeuristica(req).contains(Alan)&&solver.resolverHeuristica(req).contains(Hernan)&&solver.resolverHeuristica(req).contains(Alejandro)&&solver.resolverHeuristica(req).contains(Ruth));
	}
	
	
	@Test

	public void resolverHeuristica2gruposTesterYprogramador() {
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
		Solver solver=new Solver(grafo,req);	
		
		//aca se ve lo heuristico, se quedó con el mejor primer grupo que agarro 6= (5+1), sin embargo habia otra combinacion mejor 8=(5+3)
		//si hubiera revisado el resto del subconjunto
		System.out.println(solver.resolverHeuristica(req));

		
		
	}
}
