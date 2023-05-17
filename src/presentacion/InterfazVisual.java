package presentacion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.color.*;
import java.awt.Graphics;

public class InterfazVisual {

	private JFrame frame;
	private JTextField tfName;
	 private JMenuItem cargarArchivoItem;
	  private JMenuItem guardarArchivoItem;

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
		initialize();
		eventoNombre();
		eventosMenu();
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
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		launchWindows();
		seccionNombre();
		seccionRol();
		menuDesplegable();
	}

	// ~~~~~~~~~~~~~~~~~~~~secciones visuales frame~~~~~~~~~~~~~~~~~~~~
	public void menuDesplegable() {
		JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu archivoMenu = new JMenu("Archivo");
        menuBar.add(archivoMenu);
         cargarArchivoItem = new JMenuItem("Cargar Archivo");
        archivoMenu.add(cargarArchivoItem);
         guardarArchivoItem = new JMenuItem("Guardar Archivo");
        archivoMenu.add(guardarArchivoItem);
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

		String[] roles = { "Rol","Tester", "Programador", "Arquitecto", "Lider de proyexto" };
		JComboBox<String> comboBox = new JComboBox<>(roles);

		panel.add(comboBox);
		panel.setBounds(10, 70, 170, 31);
		frame.getContentPane().add(panel);
	}

	// ~~~~~~~~~~~Funciones y eventos~~~~~~~~~~~~~~~

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


}
