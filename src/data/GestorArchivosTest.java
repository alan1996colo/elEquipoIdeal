package data;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import negocio.GrafoLista;
import negocio.Persona;

public class GestorArchivosTest {
	GestorArchivos gestor;
	String name, texto;
	String path;

	@Before
	public void setUp() {
		gestor = new GestorArchivos();
		name = "test.txt";
		texto = "1234";
		path = "src/data/";
	}

	@Test
	public void testCrear() {

		assertTrue(gestor.crearArchivo(name));

	}

	@Test
	public void escribirArchivo() {

		assertTrue(gestor.escribirArchivo(name, texto));

	}

	@Test
	public void leerArchivoExistente() {
		gestor.crearArchivo(name);
		assertTrue(gestor.leerArchivo(name));
	}

	@Test // (expected = FileNotFoundException.class)
	public void leerArchivoInexistente() {
		assertFalse(gestor.leerArchivo("nope"));
	}

	@Test
	public void borrarArchivoQueNoExiste() {
		assertFalse(gestor.borrarArchivo(name));
	}

	@Test
	public void borrarArchivoQueSiExiste() {
		gestor.crearArchivo(name);
		assertTrue(gestor.borrarArchivo(name));
	}

	@Test
	public void serializarObjeto() {
		Persona Persona = new Persona("san miguel", 2, "5432");
		assertTrue(gestor.serializarObjeto(Persona));
	}

	@Test
	public void serializarObjetoNulo() {
		assertFalse(gestor.serializarObjeto(null));
	}

	@Test
	public void cargarSerializado() {
		Persona Persona = new Persona("san miguel", 2, "5432");
		gestor.serializarObjeto(Persona);
		Persona Persona2 = (Persona) gestor.dameObjetoSerializado("Persona");
		assertEquals(Persona2.getApellido(), "san miguel");

	}

	@Test
	public void generarJSon() {
		Persona Persona = new Persona("muñiz", 2, "5432");
		assertTrue(gestor.generarJSON("archivojason.json", Persona));

	}

	@Test
	public void generarJsonNULL() {
		assertFalse(gestor.generarJSON("archivaso", null));
	}

	@Test
	public void leerJSON() {
		Persona Persona = new Persona("san miguel", 2, "2345");
		gestor.generarJSON("archivojason", Persona);
		assertEquals(Persona.getApellido(), "san miguel");

	}

	@Test
	public void cargarJsonEnGrafoLista() {
		GrafoLista temp = new GrafoLista(gestor.cargarJsonLista("test.json"));
		System.out.println(temp.getPersonaNum(2).getApellido());
	}

	@Test
	public void cargarJsonEnGrafoListaGetNumber() {
		GrafoLista temp = new GrafoLista(gestor.cargarJsonLista("test.json"));
		System.out.println(temp.getPersonaNum(2).getApellido());
		System.out.println(temp.getPersonaNum(2).getCalificacion());

	}

	@Test
	public void cargarJsonMostrarArista() {
		GrafoLista temp = new GrafoLista(gestor.cargarJsonLista("test.json"));
		System.out.println("lista de compatibles es : " + temp.getPersonaNum(1).getCompatibles().toString());

	}

	@Test
	public void SerializarListaEnJSON() {
		GrafoLista temp = new GrafoLista();
		Persona Persona = new Persona("muz", 2, "523432");
		Persona Persona2 = new Persona("muiz", 2, "51432");
		Persona Persona3 = new Persona("mz", 2, "5432");
		temp.agregarPersona(Persona3);
		temp.agregarPersona(Persona2);
		temp.agregarPersona(Persona);
		assertTrue(gestor.generarJSON("jasonlista.json", temp.getPersonas()));
	}

	@Test
	public void generarJsonListaCongenerarJson() {
		GrafoLista temp = new GrafoLista();
		Persona Persona = new Persona("Alan", 5, "Tech Lead");
		Persona Persona2 = new Persona("Hernan", 2, "Dev Trainee");
		Persona Persona3 = new Persona("Alejandro", 2, "out");
		temp.agregarPersona(Persona3);
		temp.agregarPersona(Persona2);
		temp.agregarPersona(Persona);
		temp.agregarArista(Persona2, Persona3);
		temp.agregarArista(Persona2, Persona);
		assertTrue(gestor.generarJSON("test.json", temp.getPersonas()));

	}

	@After
	public void cleanUp() {
		// System.out.print("cleanUp_");
		gestor.borrarArchivo(name);
		gestor.borrarArchivo("Persona");
		gestor.borrarArchivo("archivojason");
		gestor.borrarArchivo("archivojason.json");
		gestor.borrarArchivo("jasonlista.json");

	}

	@Ignore
	public void testNull() {
	}

}
