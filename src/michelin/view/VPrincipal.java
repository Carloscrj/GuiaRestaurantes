package michelin.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import michelin.control.ControladorRestauranteMichelin;
import javax.swing.JLabel;


public class VPrincipal extends JFrame {
	public static final int ANCHO = 1000;
	public static final int ALTO = 600;
	public static final String MNB_MANTENIMIENTO = "Mantenimiento de Restaurantes";
	public static final String MNTN_CON_RESTAURANTES = "Consulta Restaurante";
	public static final String MNTN_REG_RESTAURANTES = "Registro de Restaurante";
	public static final String MNTN_MOD_RESTAURANTES = "Modificación de Restaurante";
	public static final String MNTN_SALIR = "Salir";
	
	private JMenuBar mnbMenu;
	private JMenu mnMantRestaurantes;
	private JMenuItem mntmConRestaurante;
	private JMenuItem mntmRegRestaurante;
	private JMenuItem mntmModRestaurante;
	private JMenuItem mntmSalir;
	private JScrollPane scrpContenedor;
	
	public VPrincipal() {
		init();
	}

	private void init() {
		setTitle("**GUIA MICHELIN**");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		scrpContenedor = new JScrollPane();
		getContentPane().add(scrpContenedor, BorderLayout.CENTER);
		
		mnbMenu = new JMenuBar();
		setJMenuBar(mnbMenu);
		
		mnMantRestaurantes = new JMenu(MNB_MANTENIMIENTO);
		mnbMenu.add(mnMantRestaurantes);
		
		mntmConRestaurante = new JMenuItem(MNTN_CON_RESTAURANTES);
		mnMantRestaurantes.add(mntmConRestaurante);
		
		JSeparator separator = new JSeparator();
		mnMantRestaurantes.add(separator);
		
		mntmRegRestaurante = new JMenuItem(MNTN_REG_RESTAURANTES);
		mnMantRestaurantes.add(mntmRegRestaurante);
		
		JSeparator separator_1 = new JSeparator();
		mnMantRestaurantes.add(separator_1);
		
		mntmModRestaurante = new JMenuItem(MNTN_MOD_RESTAURANTES);
		mnMantRestaurantes.add(mntmModRestaurante);
		
		mntmSalir = new JMenuItem(MNTN_SALIR);
		mnbMenu.add(mntmSalir);
		
		setSize(600, 800);
	}
	
	public void hacerVisible() {
		setVisible(true);
	}
	
	public void cargarPanel(JPanel panel) { //aquí añadimos como parámetro el ScrollPane que nos pasen; Opción 11 u Opción 12
		scrpContenedor.setViewportView(panel);
	}

	private void centrarVentana() {
		// Asignamos tamaño a la ventana:
		setPreferredSize(new Dimension(ANCHO, ALTO));  
		// Se obtienen las dimensiones en pixels de la pantalla:    
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();               
		// Se obtienen las dimensiones en pixels de la ventana:       
		Dimension ventana = this.getPreferredSize();               
		// Un cálculo para situar la ventana en el centro de la pantalla:       
		setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
	
	}
	
	public void setControlador(ControladorRestauranteMichelin controlador) {
		mnMantRestaurantes.addActionListener(controlador);
		mntmConRestaurante.addActionListener(controlador);
		mntmRegRestaurante.addActionListener(controlador);
		mntmModRestaurante.addActionListener(controlador);
		mntmSalir.addActionListener(controlador);
	}

}
