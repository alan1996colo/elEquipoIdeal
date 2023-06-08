package negocio;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import data.GestorArchivos;

public class GrafoListaTest {

	@Test
	public void inicializarAristaTest() {
		GestorArchivos gestor = new GestorArchivos();
		GrafoLista temp = new GrafoLista();
		Persona Alan = new Persona("Alan", 2, "Liderd");
		Persona Hernan = new Persona("Hernan", 2, "Tester");
		Persona Alejandro = new Persona("Alejandro", 2, "Arquitecto");
		temp.agregarPersona(Alan);
		temp.agregarPersona(Hernan);
		temp.agregarPersona(Alejandro);
		temp.agregarArista(Alan, Hernan);
		temp.agregarArista(Alan, Alejandro);
		temp.agregarArista(Hernan,Alejandro);
		System.out.println("original: "+temp.getPersonaNum(0).getCompatibles().size());
		gestor.generarJSON("inicializarAristaTest.json", temp.getPersonas());
		GrafoLista test = new GrafoLista(gestor.cargarJsonLista("inicializarAristaTest.json"));
		test.inicializarAristas();
		System.out.println("cargado: "+test.getPersonaNum(0).getCompatibles().size());
		System.out.println(test.getPersonaNum(0).getCompatibles().toString());
		assertTrue(test.getPersonaNum(0).estaPersona(Alejandro)&&test.getPersonaNum(0).estaPersona(Hernan));
		//Despues de una larga investigacion descubri que el problema no esta en el metodo que inicializa las aristas
		//sino en el metodo que carga/ desSerializa el archivo json, solo toma el primer elemento de la lista de compatibles,
		//supuestamente esto se debe a que la implementacion de compatibles es un hashSet.

	}
	
	@After
	public void clean() {
		GestorArchivos g=new GestorArchivos();
		//g.borrarArchivo("inicializarAristaTest.json");
	}
	

}
