package negocio;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class NegocioTest {
	Negocio negocio = new Negocio();

	@Test
	public void agregarPersonatest() {
		negocio.agregarPersona("Hernan", 1, "Tester");
		negocio.agregarPersona("Alejandro", 3, "Tester");
		assertEquals(2, negocio.getPersonas().getTamanio());

	}

	@Test
	public void agregarPersonatestTamanioDiff() {
		negocio.agregarPersona("Hernan", 1, "Tester");
		negocio.agregarPersona("Alejandro", 3, "Tester");

		negocio.agregarPersona("Heasd", 1, "Tester");
		negocio.agregarPersona("Alasdafndro", 3, "Tester");
		assertNotEquals(2, negocio.getPersonas().getTamanio());

	}

	@Test
	public void agregarCompatibilidadTest() {
		negocio.agregarPersona("Hernan", 1, "Tester");
		negocio.agregarPersona("Alejandro", 3, "Tester");
		negocio.agregarCompatibilidad("Hernan", "Alejandro");

		Persona p1 = new Persona("Hernan", 1, "Tester");
		Persona p2 = new Persona("Alejandro", 3, "Tester");

		Arista ar = new Arista(p1, p2);
		// System.out.println(negocio.getPersonas().getPersonas().get(0).getCompatibles().toString());
		assertTrue(negocio.getPersonas().getPersonas().get(0).getCompatibles().contains(ar));
		// resuelto, no estaba implementado ni el equals de persona ni el de arista.
		// Ahora esto esta solucionado
	}

	@Test
	public void agregarinCompatibilidadTest() {
		negocio.agregarPersona("Hernan", 1, "Tester");
		negocio.agregarPersona("Alejandro", 3, "Tester");
		negocio.agregarPersona("sdf", 3, "Tester");
		negocio.agregarPersona("dfff", 3, "Tester");

		negocio.agregarCompatibilidad("Hernan", "Alejandro");
		negocio.agregarCompatibilidad("Hernan", "sdf");

		negocio.agregarCompatibilidad("Hernan", "dfff");

		negocio.agregarinCompatibilidad("Hernan", "Alejandro");
		assertTrue(negocio.getPersonas().getPersonas().get(0).getCompatibles().size() == 2);
	}

	// Test arista
	@Test
	public void equalsAristaTest() {
		Arista a1 = new Arista(new Persona("alan", 0, "Tester"), new Persona("asd", 0, "Tester"));
		Arista a2 = new Arista(new Persona("alan", 0, "Tester"), new Persona("asd", 0, "Tester"));
		assertTrue(a1.equals(a2));
	}

	@Test
	@Ignore
	public void cambiarSesionTest() {
		negocio.agregarPersona("nicolas", 0, "Tester");
		negocio.agregarPersona("vanesa", 0, "Tester");
		negocio.agregarCompatibilidad("nicolas", "vanesa");
		negocio.cambiarSesion("test.json");// Este es un archivo recurrente que se usa desde distintos JunitTest.
		System.out.println(negocio.getPersonas().getPersonas().toString());// como la salida es distinta a los valores
																			// ingresados, claramente el cambio
	}

	@Test
	public void getCompatiblesDeTest() {
		negocio.agregarPersona("nicolas", 0, "Tester");
		negocio.agregarPersona("vanesa", 0, "Tester");
		negocio.agregarCompatibilidad("nicolas", "vanesa");
		assertEquals(negocio.getCompatiblesDe("nicolas").get(0), "vanesa");

	}

	@Test
	/*
	 * Estos test son visuales, para test de assertions revisar la clase solverTest
	 */
	public void calcularEquipoIdealTestSimple() {
		negocio.agregarPersona("nicolas", 1, "Tester");
		negocio.agregarPersona("Alberto", 2, "Arquitecto");
		negocio.agregarPersona("Gerardo", 3, "Programador");
		negocio.agregarPersona("Gabriel", 4, "Lider");
		negocio.agregarPersona("Jose", 5, "Tester");
		negocio.agregarCompatibilidad("nicolas", "Alberto");
		negocio.agregarCompatibilidad("nicolas", "Gerardo");
		negocio.agregarCompatibilidad("nicolas", "Gabriel");
		negocio.agregarCompatibilidad("Gerardo", "Jose");
		negocio.agregarCompatibilidad("Alberto", "Jose");
		negocio.agregarCompatibilidad("Gerardo", "Alberto");

		negocio.cargarRequerimientos(0, 1, 1, 1);
		// jose, gerardo, alberto
		System.out.println("test simple" + negocio.calcularEquipoIdeal());
	}

	@Test
	public void calcularEquipoIdealTestTodosConTodos() {
		negocio.agregarPersona("nicolas", 1, "Tester");
		negocio.agregarPersona("Alberto", 2, "Arquitecto");
		negocio.agregarPersona("Gerardo", 3, "Programador");
		negocio.agregarPersona("Gabriel", 4, "Lider");
		negocio.agregarPersona("Jose", 5, "Tester");
		negocio.agregarPersona("juan", 5, "Lider");

		negocio.agregarCompatibilidad("nicolas", "Alberto");
		negocio.agregarCompatibilidad("nicolas", "Gerardo");
		negocio.agregarCompatibilidad("nicolas", "Gabriel");
		negocio.agregarCompatibilidad("nicolas", "Jose");
		negocio.agregarCompatibilidad("nicolas", "juan");

		negocio.agregarCompatibilidad("Alberto", "Gerardo");
		negocio.agregarCompatibilidad("Alberto", "Gabriel");
		negocio.agregarCompatibilidad("Alberto", "Jose");
		negocio.agregarCompatibilidad("Alberto", "juan");

		negocio.agregarCompatibilidad("Gerardo", "Gabriel");
		negocio.agregarCompatibilidad("Gerardo", "Jose");
		negocio.agregarCompatibilidad("Gerardo", "juan");

		negocio.agregarCompatibilidad("Gabriel", "Jose");
		negocio.agregarCompatibilidad("Gabriel", "juan");

		negocio.agregarCompatibilidad("Jose", "juan");
		negocio.cargarRequerimientos(1, 1, 1, 1);
		// nicolas, gerardo, alberto
		System.out.println("Test todos" + negocio.calcularEquipoIdeal());

	}

}
