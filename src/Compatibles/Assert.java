package Compatibles;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import negocio.Persona;

public class Assert {

	@Test
	public static  void iguales(Persona[]esperados, Set<Persona> obtenido) {
		
		assertEquals(esperados.length,obtenido.size());
		
		for(int i =0;i<esperados.length;i++) 
			assertTrue(obtenido.contains(esperados[i]));

	}

}
