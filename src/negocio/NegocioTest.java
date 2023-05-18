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

}
