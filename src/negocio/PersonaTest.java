package negocio;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class PersonaTest {

	@Test
	public void mainTest() {
		Persona A = new Persona("Flores",1,"Tester");
		Persona B = new Persona("Perez",2,"Lider");
		A.agregarCompatible(B);
		Iterator<Arista> iterator = A.getCompatibles().iterator();
		if (iterator.hasNext()) {
		    Arista primerElemento = iterator.next();
		    // Realiza operaciones con el primer elemento
		
		System.out.println(primerElemento.toString());
		}
	}

}
