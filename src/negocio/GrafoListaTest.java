package negocio;

import static org.junit.Assert.*;

import org.junit.Test;

import data.GestorArchivos;

public class GrafoListaTest {

	@Test
	public void inicializarAristaTest() {
		GestorArchivos gestor = new GestorArchivos();
		GrafoLista temp = new GrafoLista();
		Persona Persona = new Persona("Alan", 2, "Tech Lead");
		Persona Persona2 = new Persona("Hernan", 2, "Dev Trainee");
		Persona Persona3 = new Persona("Alejandro", 2, "out");
		temp.agregarPersona(Persona);
		temp.agregarPersona(Persona2);
		temp.agregarPersona(Persona3);
		temp.agregarArista(Persona, Persona2);
		temp.agregarArista(Persona, Persona3);
		gestor.generarJSON("inicializarAristaTest.json", temp.getPersonas());
		GrafoLista test = new GrafoLista(gestor.cargarJsonLista("inicializarAristaTest.json"));
		test.inicializarAristas();
		System.out.println(test.getPersonaNum(0).getCompatibles().toString());
		assertTrue(test.getPersonaNum(0).estaPersona(Persona3));

	}

}
