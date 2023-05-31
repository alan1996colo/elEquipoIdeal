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

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.awt.Graphics;

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
		pantallaPrincipal.add(loadTextArea());

		divNombre();
		divRol();
		divCalificacion();
		crearBotonAgregarPersona();
		pantallaPrincipal.setVisible(true);

		frame.getContentPane().add(pantallaPrincipal);

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

		String[] roles = { "Rol", "Tester", "Programador", "Arquitecto", "Lider de proyexto" };
		rolElegido = new JComboBox<>(roles);
		rolElegido.setToolTipText("Seleccione el Rol correspondiente a la persona.");
		panel.add(rolElegido);
		panel.setBounds(10, 70, 170, 31);
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
		pantallaPrincipal.add(panel);
	}

	public void crearBotonAgregarPersona() {
		botonAgregarPersona = new JButton("Agregar");
		botonAgregarPersona.setBounds(20, 120, 100, 30);
		pantallaPrincipal.add(botonAgregarPersona);
		eventoBotonAgregar();
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
	public void crearSpinnerCantidad() {
		SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, tamanio, 1);
		JSpinner spinner = new JSpinner(spinnerModel);
		spinner.setBounds(180, 75, 40, 25);
		pantallaPrincipal.add(spinner);
	}

	public void crearBotonAgregarRequerimiento() {
		botonAgregarRequerimiento = new JButton("Agregar Requerimiento");
		botonAgregarRequerimiento.setLayout(null);
		botonAgregarRequerimiento.setBounds(12, 150, 200, 20);
		pantallaPrincipal.add(botonAgregarRequerimiento);

	}

	// ~~~~~~~~~~~Funciones y eventos~~~~~~~~~~~~~~~

	/* Intenta agregar a la persona al grafo de negocio **/
	public void eventoBotonAgregar() {
		botonAgregarPersona.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				negocio.agregarPersona(tfName.getText(), (int) calificacionElegida.getSelectedItem(),
						(String) rolElegido.getSelectedItem());
				actualizarNombres();
				System.out.println("Se ha presionado el boton agregar");
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
						negocio.agregarCompatibilidad((String) personasDisponiblesOrigen.getSelectedItem(),
								(String) personasDisponiblesDestino[i].getText());
						System.out.println("Se ha presionado el boton agregar compatibilidad");

					}
				}

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
		File initialDir = new File("src/fileManager/");
		fc.setCurrentDirectory(initialDir); // Establecer directorio inicial
		cargarArchivoItem.addActionListener(ev -> {
			int returnVal = fc.showOpenDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				try {
					// System.out.println(file.getPath());

					// **codigo de capa de negocio para cambiar el grafoLista por el el
					// archivoSeleccionado fname, usando gestorArchivos.

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Operation is CANCELLED :(");
			}
		});

		guardarArchivoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Se ha seleccionado 'Guardar Archivo'");
			}
		});

		compatiblesMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantallaPrincipal.removeAll();

				divPersonasDisponibleOrigen();
				divPersonasDisponibleDestinoEn();
				crearBotonAgregarCompatibilidad();
				pantallaPrincipal.add(loadTextArea());
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
				divPersonasDisponibleOrigen();
				divPersonasDisponibleDestinoEn();

				pantallaPrincipal.add(loadTextArea());
				crearBotonAgregarIncompatibilidad();
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
					crearBotonAgregarRequerimiento();
					crearSpinnerCantidad();
					divRol();

					pantallaPrincipal.setVisible(true);

					System.out.println("se ha cambiado de panel.. a requerimientos");
				}
			}
		});

	}

}
