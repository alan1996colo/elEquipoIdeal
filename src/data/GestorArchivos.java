package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.Element;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import negocio.Persona;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
//import java.io.FileReader;

public class GestorArchivos implements Serializable {

	private static final long serialVersionUID = 1L;
	String path = "src/data/";

	/* Constructor vacio, usa el path predeterminado "src/fileManager/ */
	public GestorArchivos() {
	}

	/**
	 * Desde Ahora version 4.5.2023, GestorArchivos tiene constructor y puede
	 * recibir un path como parametro, para que use ese path en todas las demas
	 * operaciones.
	 */
	public GestorArchivos(String path) {
		this.path = path;
	}

	public boolean serializarObjeto(Object o) {
		if (o == null) {
			return false;
		} else {
			String name = o.getClass().getSimpleName();
			try {
				FileOutputStream fos = new FileOutputStream(this.path + name);
				ObjectOutputStream out = new ObjectOutputStream(fos);
				out.writeObject(o);
				out.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	/** Retorna el objeto serializado. */
	public Object dameObjetoSerializado(String fname) {
		try // Debe estar en un try/catch
		{
			FileInputStream fis = new FileInputStream(this.path + fname);
			ObjectInputStream in = new ObjectInputStream(fis);

			Object ret = (Object) in.readObject();
			in.close();
			return ret;
		} catch (Exception ex) {
			System.out.println("Hubo un problema al cargar el objeto a memoria");
			return null;
		}
	}

	/*
	 * Crea un archivo , con el nombre pasado, true-> se logro, false->algo fallo
	 */
	public boolean crearArchivo(String fname) {
		try {
			FileOutputStream fos = new FileOutputStream(this.path + fname);
			OutputStreamWriter out = new OutputStreamWriter(fos);

			out.close();
			return true;

		} catch (Exception e) {
			System.out.println("Hubo algun problema con el manejo de archivos");
			return false;
		}
	}

	/**
	 * Escribe una linea de texto(palabra) en el archivo(fname)
	 **/
	public boolean escribirArchivo(String fname, String palabra) {
		try {
			FileOutputStream fos = new FileOutputStream(this.path + fname, true);
			OutputStreamWriter out = new OutputStreamWriter(fos);
			out.write(palabra + "\r\n");

			out.close();
			return true;

		} catch (Exception e) {
			System.out.println("Hubo algun problema con el manejo de archivos");
			return false;
		}
	}

	/**
	 * Elimina el archivo pasado por parametro.
	 **/
	public boolean borrarArchivo(String fname) {
		File fichero = new File(this.path + fname);
		if (fichero.delete()) {
			// System.out.println("El fichero ha sido borrado satisfactoriamente");
			return true;
		} else {
			// System.out.println("El fichero no puede ser borrado");
			return false;
		}

	}

	/**
	 * Muestra por terminal el texto del archivo pasado (fname)
	 **/
	public boolean leerArchivo(String fname) {
		try {
			FileInputStream fis = new FileInputStream(this.path + fname);
			Scanner scan = new Scanner(fis);
			while (scan.hasNextLine()) {
				String s1 = scan.nextLine();
				System.out.println(s1);
			}
			scan.close();
			return true;
		} catch (FileNotFoundException e) {
			// e.printStackTrace(); esto es re molesto verlo en la consola, dejemoslo en
			// return false
			return false;

		}

	}

	/**
	 * Serializa el (UN) objeto(o) en un archivo(fname) estilo json preety Si el
	 * archivo ya existe lo sobrescribe. NOTA: Por el momento no estoy pudiendo
	 * serializar
	 **/
	public boolean generarJSON(String fname, Object o) {
		if (o == null || o.equals(null)) {
			return false;
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(o);

		try {
			FileWriter writer = new FileWriter(this.path + fname);
			FileInputStream temp = new FileInputStream(this.path + fname);

			writer.write(json);
			writer.close();

			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
			// System.out.println("Algo salio mal al crear el archivo Json.");
		}
	}

	/**
	 * Retorna el objeto que fue serializado en el archivo "fname" del tipo json
	 **/
	public Object cargarJSON(String fname) {
		Gson gson = new Gson();
		Object ret = null;

		try {
			BufferedReader br = new BufferedReader(new FileReader(this.path + fname));
			ret = gson.fromJson(br, Object.class);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ALgo salio mal al cargar el archivo JSON a objeto");
		}
		return ret;
	}

	/**
	 * Recibe el nombre del archivo json, y devuelve un ArrayList de Nodos --Pensado
	 * para que la clase GrafoList lo use, tomando ese arrayList busque la ciudad
	 * que necesite.--
	 **/
	public ArrayList<Persona> cargarJsonLista(String fname) {// cargamos el archivo json en una lista de nodos
		Gson gson = new Gson();
		try {

			ArrayList<Persona> nodos = gson.fromJson(new FileReader(this.path + fname),
					new TypeToken<ArrayList<Persona>>() {
					}.getType());

			return nodos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	/*
	 * Cambia el path con el que se está trabajando al pasado por parametro. Por
	 * defecto el path con el que se trabaja es "src/fileManager/"
	 */
	public void cambiarPath(String path) {
		if (path.equals(null) || path.equals("")) {
			throw new IllegalArgumentException("estas ingresando un path vacio");
		}
		this.path = path;
	}

	/* Retorna el patho con el que se esta trabajando actualmente. **/
	public String getPath() {
		return this.path;
	}

}
