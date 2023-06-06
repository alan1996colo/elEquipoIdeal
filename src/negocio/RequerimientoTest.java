package negocio;

import static org.junit.Assert.*;

import org.junit.Test;

public class RequerimientoTest {

	@Test
	public void cumpleRequerimientoTest() {
		Requerimiento req = new Requerimiento(1, 2, 3, 4);
		assertTrue(req.cumpleRequerimientos(1, 2, 3, 4));
	}

	@Test
	public void noCumpleRequerimiento() {
		Requerimiento req = new Requerimiento(1, 1, 1, 1);
		assertFalse(req.cumpleRequerimientos(0, 0, 0, 0));
	}

}
