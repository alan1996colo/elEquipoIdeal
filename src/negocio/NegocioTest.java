package negocio;

import static org.junit.Assert.*;

import org.junit.Test;

public class NegocioTest {
	Negocio negocio = new Negocio();

	@Test
	public void agregarPersonatest() {
		negocio.agregarPersona("Hernan", 1, "dev");
		negocio.agregarPersona("Alejandro", 3, "dev");
		assertEquals(2, negocio.getPersonas().getTamanio());

	}

	@Test
	public void agregarPersonatestTamanioDiff() {
		negocio.agregarPersona("Hernan", 1, "dev");
		negocio.agregarPersona("Alejandro", 3, "dev");

		negocio.agregarPersona("Heasd", 1, "dedv");
		negocio.agregarPersona("Alasdafndro", 3, "dnev");
		assertNotEquals(2, negocio.getPersonas().getTamanio());

	}

	@Test
	public void agregarCompatibilidadTest() {
		negocio.agregarPersona("Hernan", 1, "dev");
		negocio.agregarPersona("Alejandro", 3, "dev");
		negocio.agregarCompatibilidad("Hernan", "Alejandro");

		Persona p1 = new Persona("Hernan", 1, "dev");
		Persona p2 = new Persona("Alejandro", 3, "dev");

		Arista ar = new Arista(p1, p2);
		//System.out.println(negocio.getPersonas().getPersonas().get(0).getCompatibles().toString());
		assertTrue(negocio.getPersonas().getPersonas().get(0).getCompatibles().contains(ar));
		// resuelto, no estaba implementado ni el equals de persona ni el de arista.
		// Ahora esto esta solucionado
	}

	@Test
	public void agregarinCompatibilidadTest() {
		negocio.agregarPersona("Hernan", 1, "dev");
		negocio.agregarPersona("Alejandro", 3, "dev");
		negocio.agregarPersona("sdf", 3, "dev");
		negocio.agregarPersona("dfff", 3, "dev");

		negocio.agregarCompatibilidad("Hernan", "Alejandro");
		negocio.agregarCompatibilidad("Hernan", "sdf");

		negocio.agregarCompatibilidad("Hernan", "dfff");

		negocio.agregarinCompatibilidad("Hernan", "Alejandro");
		assertTrue(negocio.getPersonas().getPersonas().get(0).getCompatibles().size() == 2);
	}

	// Test arista
	@Test
	public void equalsAristaTest() {
		Arista a1 = new Arista(new Persona("alan", 0, "perro"), new Persona("asd", 0, "asdf"));
		Arista a2 = new Arista(new Persona("alan", 0, "perro"), new Persona("asd", 0, "asdf"));
		assertTrue(a1.equals(a2));
	}
	@Test
	public void cambiarSesionTest() {
		negocio.agregarPersona("nicolas", 0, "null");
		negocio.agregarPersona("vanesa", 0, "moza");
		negocio.agregarCompatibilidad("nicolas", "vanesa");
		negocio.cambiarSesion("test.json");//Este es un archivo recurrente que se usa desde distintos JunitTest.
		System.out.println(negocio.getPersonas().getPersonas().toString());//como la salida es distinta a los valores ingresados, claramente el cambio 
	}

}
