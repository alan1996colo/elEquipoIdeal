package negocio;

import static org.junit.Assert.*;

import org.junit.Test;

public class NegocioTest {
	Negocio negocio=new Negocio();

	@Test
	public void agregarPersonatest() {
		negocio.agregarPersona("Hernan",1,"dev");
		negocio.agregarPersona("Alejandro",3,"dev");
		assertEquals(2,negocio.getPersonas().getTamanio());
		
	}
	@Test
	public void agregarPersonatestTamanioDiff() {
		negocio.agregarPersona("Hernan",1,"dev");
		negocio.agregarPersona("Alejandro",3,"dev");

		negocio.agregarPersona("Heasd",1,"dedv");
		negocio.agregarPersona("Alasdafndro",3,"dnev");
		assertNotEquals(2,negocio.getPersonas().getTamanio());
		
	}
	@Test
	public void agregarCompatibilidadTest() {
		negocio.agregarPersona("Hernan",1,"dev");
		negocio.agregarPersona("Alejandro",3,"dev");
		System.out.println(negocio.agregarCompatibilidad("Hernan", "Alejandro"));
		
		Persona p1=new Persona("Hernan",1,"dev");
		Persona p2=new Persona("Alejandro",3,"dev");
		
		Arista ar=new Arista(p2,p2);
		System.out.println(negocio.getPersonas().getPersonas().get(0).getCompatibles().toString());
		assertTrue(negocio.getPersonas().getPersonas().get(0).getCompatibles().contains(ar)); //probablemente el hashCode este mal implementado, el metodo agregar compatible funciona correctamente
		//sin embargo el contains no esta haciendo lo esperado.
	//revisar proximamente el equals, quiza este mall implementado, hacer un test	
	}

}
