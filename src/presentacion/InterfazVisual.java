package presentacion;

import negocio.Negocio;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
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
import javax.swing.JComboBox;
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

public class InterfazVisual {

	private JFrame frame;
	private JTextField tfName;
	private JMenuItem cargarArchivoItem;
	private JMenuItem guardarArchivoItem;
	private JTextArea textArea;
	private JMenuItem requerimientoItem;
	private AbstractButton botonAgregar;
	private JMenuItem compatiblesMenu;
	private JMenuItem incompatiblesMenu;
	private Negocio negocio;
	private JComboBox<String> rolElegido;
	private JComboBox<Integer> calificacionElegida;

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
		individuoMenuBar();
		crearBotonAgregar();
		loadFile();
	}

	// ~~~~~~~~~~~~~~~~~~~~secciones visuales frame~~~~~~~~~~~~~~~~~~~~

	public void loadFile() {
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

		frame.add(scrollPane); // Agrega el JScrollPane al frame

		frame.repaint(); // Actualiza la visualización del frame
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

	public void individuoMenuBar() {
		JMenuBar menuBar = frame.getJMenuBar();
		JMenu individuoMenu = new JMenu("Solucion");
		menuBar.add(individuoMenu);
		compatiblesMenu = new JMenuItem("Crear lista de Compatibles");
		individuoMenu.add(compatiblesMenu);
		incompatiblesMenu = new JMenuItem("Crear lista de incompatibles");
		individuoMenu.add(incompatiblesMenu);
		requerimientoItem = new JMenuItem("Cargar/Modificar requerimientos");
		individuoMenu.add(requerimientoItem);
	}

	private void launchWindows() {
		frame = new JFrame("El equipo ideal");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
	}

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
		tfName.setBounds(10, 40, 200, 30);
		frame.getContentPane().add(tfName);
		tfName.setColumns(2);
	}

	private void seccionRol() {
		JPanel panel = new JPanel();

		String[] roles = { "Rol", "Tester", "Programador", "Arquitecto", "Lider de proyexto" };
		rolElegido = new JComboBox<>(roles);
		rolElegido.setToolTipText("Seleccione el Rol correspondiente a la persona.");
		panel.add(rolElegido);
		panel.setBounds(10, 70, 170, 31);
		frame.getContentPane().add(panel);
	}

	private void seccionCalificacion() {
		JPanel panel = new JPanel();

		Integer[] roles = { 1, 2, 3, 4, 5 };
		calificacionElegida = new JComboBox<>(roles);
		calificacionElegida.setToolTipText(
				"Seleccione la calificacion historica de desempeño , siendo 5 muy calificado y 1 poco calificado.");

		panel.add(calificacionElegida);
		panel.setBounds(120, 70, 170, 31);
		frame.getContentPane().add(panel);
	}

	public void crearBotonAgregar() {
		botonAgregar = new JButton("Agregar");
		botonAgregar.setBounds(90, 120, 100, 30);
		frame.add(botonAgregar);
	}

	// ~~~~~~~~~~~Funciones y eventos~~~~~~~~~~~~~~~

	/* Intenta agregar a la persona al grafo de negocio **/
	public void eventoBotonAgregar() {
		botonAgregar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				negocio.agregarPersona(tfName.getText(), (int) calificacionElegida.getSelectedItem(),
						(String) rolElegido.getSelectedItem());

				System.out.println("Se ha presionado el boton agregar");
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
				// Acción al seleccionar "Cargar Archivo"
				System.out.println("Se ha seleccionado 'Cargar Archivo'");
			}
		});

		guardarArchivoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Acción al seleccionar "Guardar Archivo"
				System.out.println("Se ha seleccionado 'Guardar Archivo'");
			}
		});

	}

}
