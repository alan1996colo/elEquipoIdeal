package presentacion;

import negocio.Negocio;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import java.awt.Graphics;
import java.awt.Image;

public class InterfazVisual {

	private Negocio negocio;
	private JFrame frame;
	private JTextField tfName;
	private JTextArea textArea;
	private JMenuItem cargarArchivoItem, guardarArchivoItem, requerimientoItem, compatiblesMenu, incompatiblesMenu,
			empleadoItem;
	private JButton botonAgregarPersona, botonAgregarCompatibilidad, botonAgregarIncompatibilidad;
	private JComboBox<String> rolElegido;
	private JComboBox<Integer> calificacionElegida;
	private JPanel pantallaPrincipal;
	private JComboBox<String> personasDisponiblesOrigen;
	private JCheckBox[] personasDisponiblesDestino;
	private int tamanio = 2;
	private JButton botonAgregarRequerimiento;
	private String nombres[] = { "" };
	private mxGraph graph;
	private ArrayList<Object> contenedorDeVertices;
	private JTable table;
	private JButton botonCalcular;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazVisual window = new InterfazVisual();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public InterfazVisual() {
		negocio = new Negocio();
		initialize();
		eventosMenu();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		launchWindows();
		fileMenuBar();
		solucionMenuBar();

	}

	// ~~~~~~~~~~~~~~~~~~~~secciones visuales frame~~~~~~~~~~~~~~~~~~~~
	private void launchWindows() {

		frame = new JFrame("El equipo ideal");
		frame.setBounds(0, 0, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		crearPantallaPrincipal();

	}

	public void crearPantallaPrincipal() {

		pantallaPrincipal = new JPanel();
		pantallaPrincipal.setLayout(null);
		pantallaPrincipal.setBounds(0, 0, 800, 600);
		divNombre();
		divRol();
		divCalificacion();
		crearBotonAgregarPersona();
		insertarGrafo();
		agregarFondoDePantalla();

		pantallaPrincipal.setVisible(true);

		frame.getContentPane().add(pantallaPrincipal);

	}

	/*** Este metodo debe ser llamado d¿luego de agregar todos los componentes. **/
	public void agregarFondoDePantalla() {
		ImageIcon icon = new ImageIcon("src/data/UNGS.jpg");
		ImageIcon iconEscalado = new ImageIcon(icon.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH));
		JLabel thumb = new JLabel();
		thumb.setBounds(0, -60, 800, 600);
		thumb.setIcon(iconEscalado);
		pantallaPrincipal.add(thumb);
		pantallaPrincipal.setComponentZOrder(thumb, pantallaPrincipal.getComponentCount() - 1);

	}

	public void divTablaRequerimientos() {
		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Lider");
		model.addColumn("Arquitecto");
		model.addColumn("Programador");
		model.addColumn("Tester");
		// dadas ciertas complicaciones de parseo mas adelante el el acttion listener,
		// creo que lo mejor es inicializar la lista como strings en lugar de integers.
		String cantidad[] = new String[] { "0", "0", "0", "0" };
		model.addRow(cantidad);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(300, 0, 500, 500);
		pantallaPrincipal.add(scroll);

	}

	public JScrollPane loadTextArea() {
		String filePath = "README.md";
		textArea = new JTextArea();
		textArea.setEditable(false);

		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			StringBuilder content = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line).append("\n");
			}
			reader.close();
			textArea.setText(content.toString());
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error al cargar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
		}

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(400, 50, 300, 400); // Ajusta la ubicación y el tamaño del JScrollPane

		return scrollPane;

	}

	public void fileMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu archivoMenu = new JMenu("Archivo");
		menuBar.add(archivoMenu);
		cargarArchivoItem = new JMenuItem("Cargar Archivo");
		archivoMenu.add(cargarArchivoItem);
		guardarArchivoItem = new JMenuItem("Guardar Archivo");
		archivoMenu.add(guardarArchivoItem);
		frame.setJMenuBar(menuBar);
	}

	public void solucionMenuBar() {
		JMenuBar menuBar = frame.getJMenuBar();
		JMenu solucionMenu = new JMenu("Solucion");
		menuBar.add(solucionMenu);
		compatiblesMenu = new JMenuItem("Crear lista de Compatibles");
		solucionMenu.add(compatiblesMenu);
		incompatiblesMenu = new JMenuItem("Crear lista de incompatibles");
		solucionMenu.add(incompatiblesMenu);
		requerimientoItem = new JMenuItem("Cargar/Modificar requerimientos");
		solucionMenu.add(requerimientoItem);
		empleadoItem = new JMenuItem("Agregar empleado");
		solucionMenu.add(empleadoItem);
	}

	// ~~~~~~~~~~~primer pantalla agregar persona, rol,
	// puntuacion~~~~~~~~~~~~~~~~~~~~~~~~~

	@SuppressWarnings("serial")
	private void divNombre() {

		tfName = new JTextField() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				if (getText().isEmpty() && !isFocusOwner()) {
					g.setColor(Color.GRAY);
					g.setFont(getFont().deriveFont(Font.ITALIC));
					g.drawString("   Ingrese nombre", getInsets().left, getBaseline(getWidth(), getHeight()));
				}
			}
		};
		tfName.setBounds(20, 10, 170, 30); // Establecer las coordenadas y dimensiones del JTextField dentro del JPanel

		pantallaPrincipal.add(tfName);
		eventoNombre();

	}

	private void divRol() {
		JPanel panel = new JPanel();

		String[] roles = { "Tester", "Programador", "Arquitecto", "Lider de proyexto" };
		rolElegido = new JComboBox<>(roles);
		rolElegido.setToolTipText("Seleccione el Rol correspondiente a la persona.");
		panel.add(rolElegido);
		panel.setBounds(10, 70, 170, 31);
		panel.setOpaque(false);
		pantallaPrincipal.add(panel);
	}

	private void divCalificacion() {
		JPanel panel = new JPanel();

		Integer[] roles = { 1, 2, 3, 4, 5 };
		calificacionElegida = new JComboBox<>(roles);
		calificacionElegida.setToolTipText(
				"Seleccione la calificacion historica de desempeño , siendo 5 muy calificado y 1 poco calificado.");

		panel.add(calificacionElegida);
		panel.setBounds(120, 70, 170, 31);
		panel.setOpaque(false);
		pantallaPrincipal.add(panel);
	}

	public void crearBotonAgregarPersona() {
		botonAgregarPersona = new JButton("Agregar");
		botonAgregarPersona.setBounds(20, 120, 100, 30);
		pantallaPrincipal.add(botonAgregarPersona);
		eventoBotonAgregar();
	}

	public void crearBotonCalcular() {
		botonCalcular = new JButton("Calcular");
		botonCalcular.setBounds(20, 200, 100, 100);
		pantallaPrincipal.add(botonCalcular);
		eventoBotonCalcular();
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~ pantalla agregar in/compatibilidad origen-destino
	// ~~~~~~~~~~~~~~ ~~~~~~~~~~
	public void divPersonasDisponibleOrigen() {

		personasDisponiblesOrigen = new JComboBox<>(nombres);
		personasDisponiblesOrigen
				.setToolTipText("Seleccione entre las personas disponibles para agregar compatibilidad");
		personasDisponiblesOrigen.setBounds(10, 50, 100, 20);
		pantallaPrincipal.add(personasDisponiblesOrigen);
	}

	private void divPersonasDisponibleDestinoEn() {

		JPanel panelCheckboxes = new JPanel();
		panelCheckboxes.setLayout(null);

		personasDisponiblesDestino = new JCheckBox[nombres.length];
		for (int i = 0; i < nombres.length; i++) {
			personasDisponiblesDestino[i] = new JCheckBox(nombres[i]);
			personasDisponiblesDestino[i].setBounds(0, i * 25, 100, 20);
			panelCheckboxes.add(personasDisponiblesDestino[i]);
		}
		JScrollPane scrollPane = new JScrollPane(panelCheckboxes);
		scrollPane.setBounds(120, 0, 100, 100);
		pantallaPrincipal.add(scrollPane);

		panelCheckboxes.setPreferredSize(new Dimension(100, nombres.length * 25));
	}

	public void crearBotonAgregarCompatibilidad() {
		botonAgregarCompatibilidad = new JButton("Agregar compatibilidad");
		botonAgregarCompatibilidad.setLayout(null);
		botonAgregarCompatibilidad.setBounds(12, 150, 200, 20);
		pantallaPrincipal.add(botonAgregarCompatibilidad);
		eventoBotonAgregarCompatibilidad();
	}

	public void crearBotonAgregarIncompatibilidad() {
		botonAgregarIncompatibilidad = new JButton("Agregar incompatibilidad");
		botonAgregarIncompatibilidad.setLayout(null);
		botonAgregarIncompatibilidad.setBounds(12, 150, 200, 20);
		pantallaPrincipal.add(botonAgregarIncompatibilidad);

	}

	// ---------------------------Pantalla solicitar
	// requerimienttos--------------------------
	public JSpinner crearSpinnerCantidad() {
		SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, tamanio, 1);
		JSpinner spinner = new JSpinner(spinnerModel);
		spinner.setBounds(180, 75, 40, 25);

		return spinner;
	}

	public void crearBotonAgregarRequerimiento() {
		botonAgregarRequerimiento = new JButton("Agregar Requerimiento");
		botonAgregarRequerimiento.setLayout(null);
		botonAgregarRequerimiento.setBounds(12, 150, 200, 20);
		pantallaPrincipal.add(botonAgregarRequerimiento);
		eventoBotonAgregarRequerimiento();

	}

	// ~~~~~~~~~~~Funciones y eventos~~~~~~~~~~~~~~~

	public void insertarGrafo() {
		// creo un arraylist para los futuros vertices.
		contenedorDeVertices = new ArrayList<>();
		// Crear un objeto mxGraph
		graph = new mxGraph();
		// Obtener el objeto raíz del grafo
		Object parent = graph.getDefaultParent();

		if (this.negocio != null) {
			int x = 0;
			int y = 0;
			boolean turno = true;
			for (String personaNombre : negocio.getNombres()) {
				Object vertex = graph.insertVertex(parent, null, personaNombre, x, y, 40, 40, randomPicture());
				if (turno) {
					x += 40;

					if (x > 490) {
						turno = false;
						x = 0;

					}
				}

				if (!turno) {
					y += 40;
					if (y > 490) {
						turno = true;
						y = 0;
					}
				}

				contenedorDeVertices.add(vertex);
			}
			for (String personaNombre : negocio.getNombres()) {
				// insertar aristas.
				for (String compatible : negocio.getCompatiblesDe(personaNombre)) {
					graph.insertEdge(parent, null, "", buscarEnContenedor(personaNombre),
							buscarEnContenedor(compatible));
				}
			}

		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		graphComponent.setBounds(300, 50, 490, 490);

		pantallaPrincipal.add(graphComponent);
		// Agregar el componente de visualización al JFrame

	}

	/***
	 * Busca el objeto por nombre en el contenedorDeVertices y devuelve el objeto.
	 */
	private Object buscarEnContenedor(String nombreBuscado) {
		for (Object buscado : this.contenedorDeVertices) {
			if (nombreBuscado.equals((String) graph.getModel().getValue(buscado))) {
				return buscado;
			}
		}
		return null;
	}

	private String randomPicture() {
		Random random = new Random();
		String[] pathsDeImagenes = new String[] { "shape=image;image=/data/lelouch.png",
				"shape=image;image=/data/icono4.jpg", "shape=image;image=/data/icono1.jpg" };

		return pathsDeImagenes[random.nextInt(3)];
	}

	/* Intenta agregar a la persona al grafo de negocio **/
	public void eventoBotonAgregar() {
		botonAgregarPersona.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				negocio.agregarPersona(tfName.getText(), (int) calificacionElegida.getSelectedItem(),
						(String) rolElegido.getSelectedItem());
				actualizarNombres();
				pantallaPrincipal.removeAll();
				pantallaPrincipal.setVisible(false);
				crearPantallaPrincipal();

				System.out.println("Se ha presionado el boton agregar");
			}
		});

	}

	public void eventoBotonCalcular() {
		botonCalcular.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String resultado = negocio.calcularEquipoIdeal();
				JOptionPane.showMessageDialog(null, resultado, "EL equipo ideal es: ", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Se ha presionado el boton CALCULAR");
			}
		});

	}

	public void actualizarNombres() {
		this.nombres = negocio.getNombres();
	}

	public void actualizarTamanio() {
		this.tamanio = negocio.getPersonas().getTamanio();
	}

	public void eventoBotonAgregarCompatibilidad() {
		botonAgregarCompatibilidad.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < personasDisponiblesDestino.length; i++) {
					if (personasDisponiblesDestino[i].isSelected()) {
						try {
							if (negocio.agregarCompatibilidad((String) personasDisponiblesOrigen.getSelectedItem(),
									(String) personasDisponiblesDestino[i].getText())) {
								System.out.println("Se ha presionado el boton agregar compatibilidad");
							} else {
								JOptionPane.showMessageDialog(null,
										"No se pudo agregar la compatibilidad, revise los datos ingresados");
							}

						} catch (IllegalArgumentException except) {
							JOptionPane.showMessageDialog(null, except);
						}
					}

				}
				pantallaPrincipal.removeAll();

				divPersonasDisponibleOrigen();
				divPersonasDisponibleDestinoEn();
				crearBotonAgregarCompatibilidad();
				insertarGrafo();
				agregarFondoDePantalla();
				pantallaPrincipal.revalidate();
				pantallaPrincipal.repaint();

			}

		});
	}

	public void eventoBotonAgregarIncompatibilidad() {
		botonAgregarIncompatibilidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < personasDisponiblesDestino.length; i++) {
					if (personasDisponiblesDestino[i].isSelected()) {
						negocio.agregarinCompatibilidad((String) personasDisponiblesOrigen.getSelectedItem(),
								(String) personasDisponiblesDestino[i].getText());
						System.out.println("Se ha presionado el boton agregar incompatibilidad");

					}
				}

			}
		});
	}

	public void eventoBotonAgregarRequerimiento() {
		botonAgregarRequerimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/// OKAY, cuando no toco ningun valor el conenido de table.getValue es un Iteger
				/// por defecto.
				// si modifico algo, el contenido de table.getvalue es un object String.
				// okay, cambie la lista inicial para que comience siendo strings.

				// System.out.println(table.getValueAt(0,0).getClass());
				try {
					negocio.cargarRequerimientos(Integer.parseInt(table.getValueAt(0, 0).toString()),
							Integer.parseInt(table.getValueAt(0, 1).toString()),
							Integer.parseInt(table.getValueAt(0, 2).toString()),
							Integer.parseInt(table.getValueAt(0, 3).toString()));

					System.out.println("Se ha presionado el boton agregar requerimientos");
				} catch (NumberFormatException excepcion) {
					JOptionPane.showMessageDialog(null, "Solo se permiten numeros, no se permiten letras");

				} catch (IllegalArgumentException exc) {
					JOptionPane.showMessageDialog(null, exc);

				}

			}
		});
	}

	public void eventoNombre() {
		tfName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (tfName.getText().equals("   Ingrese nombre")) {
					tfName.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (tfName.getText().isEmpty()) {
					tfName.setText("   Ingrese nombre");
				}
			}
		});
	}

	private void eventosMenu() {
		JFileChooser fc = new JFileChooser();
		File initialDir = new File("src/data/");
		fc.setCurrentDirectory(initialDir); // Establecer directorio inicial
		cargarArchivoItem.addActionListener(ev -> {
			int returnVal = fc.showOpenDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				try {

					negocio.cambiarSesion(file.getName());
					System.out.println("Se ha seleccionado el archivo " + file.getName());

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Operation is CANCELLED :(");
			}

			pantallaPrincipal.removeAll();
			pantallaPrincipal.setVisible(false);
			crearPantallaPrincipal();

		});

		guardarArchivoItem.addActionListener(ev -> {
			fc.setDialogTitle("Especifique el archivo a guardar");
			int returnVal = fc.showSaveDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fc.getSelectedFile();
				try {
					negocio.guardarSesion(fileToSave.getName());
					// Desde acá hay que seguir para dibujar todos los puntos
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				// El usuario canceló el diálogo de selección de archivo
				System.out.println("Selección de archivo cancelada");
			}
		});

		compatiblesMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantallaPrincipal.removeAll();

				divPersonasDisponibleOrigen();
				divPersonasDisponibleDestinoEn();
				crearBotonAgregarCompatibilidad();
				// pantallaPrincipal.add(loadTextArea());
				insertarGrafo();
				agregarFondoDePantalla();
				pantallaPrincipal.revalidate();
				pantallaPrincipal.repaint();
				System.out.println("se ha cambiado de panel..");
			}
		});
		empleadoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pantallaPrincipal.removeAll();
				pantallaPrincipal.setVisible(false);
				crearPantallaPrincipal();

				System.out.println("se ha clickeado en agregar empleado");
			}
		});

		incompatiblesMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantallaPrincipal.removeAll();
				pantallaPrincipal.setVisible(false);
				JOptionPane.showMessageDialog(null,
						"Lo sentimos, debido a un recorte de personal \n no su pudo completar esta funcionalidad");

				divPersonasDisponibleOrigen();
				divPersonasDisponibleDestinoEn();

				// pantallaPrincipal.add(loadTextArea());
				insertarGrafo();
				crearBotonAgregarIncompatibilidad();
				agregarFondoDePantalla();
				pantallaPrincipal.setVisible(true);
				System.out.println("se ha cambiado de panel.. a incompatibilidad");
			}
		});
		requerimientoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (negocio.getPersonas().getTamanio() == 0) {
					JOptionPane.showMessageDialog(null,
							"Antes de ir a los requerimientos deberia cargar una lista de personas...");
				} else {
					actualizarTamanio();
					pantallaPrincipal.removeAll();
					pantallaPrincipal.setVisible(false);
					crearBotonCalcular();
					insertarGrafo();

					crearBotonAgregarRequerimiento();
					divTablaRequerimientos();
					agregarFondoDePantalla();

					pantallaPrincipal.setVisible(true);

					System.out.println("se ha cambiado de panel.. a requerimientos");
				}
			}
		});

	}

}
