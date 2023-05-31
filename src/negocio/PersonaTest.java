package negocio;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class PersonaTest {

	@Test
	public void mainTest() {
		Persona A = new Persona("Flores", 1, "Tester");
		Persona B = new Persona("Perez", 2, "Lider");
		A.agregarCompatible(B);
		Iterator<Arista> iterator = A.getCompatibles().iterator();
		if (iterator.hasNext()) {
			Arista primerElemento = iterator.next();
			// Realiza operaciones con el primer elemento

			System.out.println(primerElemento.toString());
		}
	}

	@Test
	public void personaEqualsTest() {
		Persona a = new Persona("alg", 2, "asd");
		Persona b = new Persona("alg", 2, "asd");
		assertTrue(a.equals(b));
	}

	@Test
	public void personaEqualsTest2() {
		Persona a = new Persona("alg", 2, "asd");
		Persona b = a;
		assertTrue(a.equals(b));
	}

	@Test
	public void estaPersonaTest() {
		Persona a = new Persona("Alan", 2, "dev");
		Persona b = new Persona("matias", 3, "tester");

		a.agregarCompatible(b);

		assertTrue(a.estaPersona(b));

	}

	@Test
	public void quitarCompatibleTest() {
		GrafoLista grafo = new GrafoLista();
		Persona p1 = new Persona("matias", 1, "barbero");
		Persona p2 = new Persona("marcos", 1, "peluquero");
		grafo.agregarPersona(p1);
		grafo.agregarPersona(p2);
		grafo.agregarArista(p1, p2);
		p2.quitarCompatible(p1);
		assertTrue(p2.getCompatibles().size() == 0);
	}

	@Test
	public void quitarCompatibleTest2() {
		GrafoLista grafo = new GrafoLista();
		Persona p1 = new Persona("matias", 1, "barbero");
		Persona p2 = new Persona("marcos", 1, "peluquero");
		Persona p3 = new Persona("mars", 1, "peluqro");
		grafo.agregarPersona(p1);
		grafo.agregarPersona(p2);
		grafo.agregarPersona(p3);

		grafo.agregarArista(p1, p2);
		grafo.agregarArista(p3, p2);

		p2.quitarCompatible(p1);
		assertTrue(p2.getCompatibles().size() == 1);
	}

}
