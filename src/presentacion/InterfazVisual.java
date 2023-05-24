package presentacion;

import negocio.Negocio;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.color.*;
import java.awt.Graphics;
import java.awt.GridLayout;

public class InterfazVisual {

	private Negocio negocio;
	private JFrame frame;
	private JTextField tfName;	
	private JTextArea textArea;
	private JMenuItem cargarArchivoItem,guardarArchivoItem,requerimientoItem,compatiblesMenu,incompatiblesMenu,empleadoItem;
	private JButton botonAgregarPersona, botonAgregarCompatibilidad;
	private JComboBox<String> rolElegido;
	private JComboBox<Integer> calificacionElegida;
	private JPanel pantallaAgregarPersona, pantallaDisponibles;
	private JComboBox<String> personasDisponiblesOrigen;
	private JCheckBox[] personasDisponiblesDestino;
	

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
		eventoNombre();
		eventosMenu();
		eventoBotonAgregar();
		eventoBotonAgregarCompatibilidad();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		launchWindows();
		seccionNombre();
		seccionRol();
		fileMenuBar();
		seccionCalificacion();
		solucionMenuBar();
		crearBotonAgregarPersona();
		crearBotonAgregarCompatibilidad();
		loadTextArea();

	}

	// ~~~~~~~~~~~~~~~~~~~~secciones visuales frame~~~~~~~~~~~~~~~~~~~~
	private void launchWindows() {

		frame = new JFrame("El equipo ideal");
		frame.setBounds(0, 0, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		pantallaAgregarPersona = new JPanel();
		pantallaAgregarPersona.setLayout(null);
		pantallaAgregarPersona.setBounds(0, 0, 800, 600);
		pantallaAgregarPersona.setVisible(true);
		pantallaAgregarPersona.add(loadTextArea());
		frame.getContentPane().add(pantallaAgregarPersona);

		pantallaDisponibles = new JPanel();
		pantallaDisponibles.setLayout(null);
		pantallaDisponibles.setBounds(0, 0, 800, 600);
		pantallaDisponibles.setVisible(false);
		frame.getContentPane().add(pantallaDisponibles);

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

//~~~~~~~~~~~primer pantalla agregar persona, rol, puntuacion~~~~~~~~~~~~~~~~~~~~~~~~~

	private void seccionNombre() {

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
		pantallaAgregarPersona.add(tfName);

	}

	private void seccionRol() {
		JPanel panel = new JPanel();

		String[] roles = { "Rol", "Tester", "Programador", "Arquitecto", "Lider de proyexto" };
		rolElegido = new JComboBox<>(roles);
		rolElegido.setToolTipText("Seleccione el Rol correspondiente a la persona.");
		panel.add(rolElegido);
		panel.setBounds(10, 70, 170, 31);
		pantallaAgregarPersona.add(panel);
	}

	private void seccionCalificacion() {
		JPanel panel = new JPanel();

		Integer[] roles = { 1, 2, 3, 4, 5 };
		calificacionElegida = new JComboBox<>(roles);
		calificacionElegida.setToolTipText(
				"Seleccione la calificacion historica de desempeño , siendo 5 muy calificado y 1 poco calificado.");

		panel.add(calificacionElegida);
		panel.setBounds(120, 70, 170, 31);
		pantallaAgregarPersona.add(panel);
	}

	public void crearBotonAgregarPersona() {
		botonAgregarPersona = new JButton("Agregar");
		botonAgregarPersona.setBounds(20, 120, 100, 30);
		pantallaAgregarPersona.add(botonAgregarPersona);
	}

//~~~~~~~~~~~~~~~~~~~~~~~~	pantalla agregar compatibilidad origen-destino ~~~~~~~~~~~~~~	~~~~~~~~~~
	public void crearSeccionPersonasDisponibleOrigen() {
		String[] nombres = negocio.getNombres();

		personasDisponiblesOrigen = new JComboBox<>(nombres);
		personasDisponiblesOrigen
				.setToolTipText("Seleccione entre las personas disponibles para agregar compatibilidad");
		personasDisponiblesOrigen.setBounds(10, 50, 100, 20);
		pantallaDisponibles.add(personasDisponiblesOrigen);
	}

	private void crearSeccionPersonasDisponibleDestino() {
		String[] nombres = negocio.getNombres();

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
		pantallaDisponibles.add(scrollPane);

		panelCheckboxes.setPreferredSize(new Dimension(100, nombres.length * 25));
	}

	public void crearBotonAgregarCompatibilidad() {
		botonAgregarCompatibilidad = new JButton("Agregar compatibilidad");
		botonAgregarCompatibilidad.setLayout(null);
		botonAgregarCompatibilidad.setBounds(12, 150, 200, 20);
		pantallaDisponibles.add(botonAgregarCompatibilidad);
	
	}

	// ~~~~~~~~~~~Funciones y eventos~~~~~~~~~~~~~~~

	/* Intenta agregar a la persona al grafo de negocio **/
	public void eventoBotonAgregar() {
		botonAgregarPersona.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				negocio.agregarPersona(tfName.getText(), (int) calificacionElegida.getSelectedItem(),
						(String) rolElegido.getSelectedItem());

				System.out.println("Se ha presionado el boton agregar");
			}
		});

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
		cargarArchivoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Se ha seleccionado 'Cargar Archivo'");
			}
		});

		guardarArchivoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Se ha seleccionado 'Guardar Archivo'");
			}
		});

		compatiblesMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantallaAgregarPersona.setVisible(false);
				crearSeccionPersonasDisponibleOrigen();
				crearSeccionPersonasDisponibleDestino();
				
				pantallaDisponibles.add(loadTextArea());
				pantallaDisponibles.setVisible(true);
				System.out.println("se ha cambiado de panel..");
			}
		});
		empleadoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantallaAgregarPersona.setVisible(true);
				pantallaDisponibles.setVisible(false);
				System.out.println("se ha clickeado en agregar empleado");
			}
		});

	}

}
