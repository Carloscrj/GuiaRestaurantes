package michelin.view;

import javax.swing.JPanel;

import michelin.control.ControladorRestauranteMichelin;
import michelin.model.Restaurante;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelConsultaRestaurantes extends JPanel {
	public static final String[] OPCIONES = {"TODAS", "1 estrella", "2 estrellas", "3 estrellas"};
	public static final String CONSULTAR= "Consultar";
	private static final int ANCHO = 800;
	private static final int ALTO = 600;
	private static final String NOMBRE = "NOMBRE";
	private static final String CIUDAD = "CIUDAD";
	private static final String DISTINCION = "DISTINCION";
	private static final String COCINA = "COCINA";
	public static final String PRECIO = "PRECIO";
	public static final String ELIMINAR = "ELIMINAR";
	
	public JComboBox <String>cmbRegion;
	private DefaultComboBoxModel<String> dcbReg;
	private JComboBox <String>cmbDistincion;
	private JButton btnConsultar;
	private JScrollPane scrpConsulta;
	public JTable tblRestaurantes;
	private DefaultTableModel tModel; //nos sirve para configurar la tabla
	private JButton btnEliminar;
	
	public PanelConsultaRestaurantes() {
		setLayout(null);
		setSize(ANCHO, ALTO);
		
		JLabel lblFiltro = new JLabel("Filtro:");
		lblFiltro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFiltro.setBounds(38, 100, 52, 13);
		add(lblFiltro);
		
		JLabel lblRegion = new JLabel("Region:");
		lblRegion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegion.setBounds(38, 153, 67, 13);
		add(lblRegion);
		
		JLabel lblConsultaTitulo = new JLabel("CONSULTA DE RESTAURANTES");
		lblConsultaTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConsultaTitulo.setBounds(38, 39, 239, 13);
		add(lblConsultaTitulo);
		
		JLabel lblDistincion = new JLabel("Distincion:");
		lblDistincion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDistincion.setBounds(262, 153, 67, 13);
		add(lblDistincion);
		
		cmbRegion = new JComboBox<String>();
		dcbReg = new DefaultComboBoxModel<String>();
		cmbRegion.setModel(dcbReg);
		cmbRegion.setBounds(102, 151, 150, 21);
		add(cmbRegion);
		
		cmbDistincion = new JComboBox<String>();
		cmbDistincion.setModel(new DefaultComboBoxModel<String>(OPCIONES));
		cmbDistincion.setBounds(339, 151, 67, 21);
		add(cmbDistincion);
		
		btnConsultar = new JButton(CONSULTAR);
		btnConsultar.setBounds(298, 195, 108, 21);
		add(btnConsultar);
		
		scrpConsulta = new JScrollPane();
		scrpConsulta.setBounds(38, 226, 723, 294);
		add(scrpConsulta);
		
		tblRestaurantes = new JTable();
		scrpConsulta.setViewportView(tblRestaurantes);
		configurarTabla();
		
		btnEliminar = new JButton(ELIMINAR);
		btnEliminar.setBounds(281, 569, 125, 21);
		add(btnEliminar);
		
		
		
	}
	
	private void configurarTabla() {
		tModel = new DefaultTableModel() { //inicializamos table model
			public boolean isCellEditable(int row, int column) {
				return false; //porque no queremos celdas editables
			}
		};
		
				tblRestaurantes.setModel(tModel);
		//Establecemos ahora el nombre de las columnas con constantes:
				tModel.addColumn(NOMBRE); //Será la posición 0 en la columna
				tModel.addColumn(CIUDAD); //Será la posición 1 en la columna
				tModel.addColumn(DISTINCION); //Será la posición 2 en la columna
				tModel.addColumn(COCINA); //Será la posición 3 en la columna
				tModel.addColumn(PRECIO); //Será la posición 3 en la columna
				//Ponemos el ancho al campo de la columna:
				tblRestaurantes.getColumn(NOMBRE).setPreferredWidth(75);
				tblRestaurantes.getColumn(CIUDAD).setPreferredWidth(75);
				tblRestaurantes.getColumn(DISTINCION).setPreferredWidth(75);
				tblRestaurantes.getColumn(COCINA).setPreferredWidth(75);
				tblRestaurantes.getColumn(PRECIO).setPreferredWidth(75);
	}
	

	public void  rellenarTabla(ArrayList<Restaurante> listaRestaurantes) {
		
		tModel.getDataVector().clear();// sirve para limpiar el contenido de la tabla antes de cargar otra
		//tModel.setRowCount(0); spara lo mismo que el de arriba, en este le decimos que si contenido sea cero
		
		Object[] fila = new Object[5]; //creamos un array con el tamaño del número de columnas que tenemos que será para llenar las filas
		
		//vamos a rellenar las este array que hemos creado con un foreach; por ello hemos sacado los getters de cada atributo:
		for (Restaurante restaurante : listaRestaurantes) { 
			fila[0] = restaurante.getNombre();
			fila[1] = restaurante.getCiudad();
			if (restaurante.getDistincion()==1) {
				fila[2] = "*";
			}else if(restaurante.getDistincion()==2) {
				fila[2] = "**";
			}else {
				fila[2] = "***";
			}
			
			fila[3] = restaurante.getCocina();
			fila[4] = restaurante.getPrecio_min()+"-"+restaurante.getPrecio_max();
			
			tModel.addRow(fila); //se va añadiendo en cada iteración los datos de cada persona
		}
	}
	
	
	public void setControlador(ControladorRestauranteMichelin controlador) {
		cmbRegion.addActionListener(controlador);
		cmbDistincion.addActionListener(controlador);
		btnConsultar.addActionListener(controlador);
		btnEliminar.addActionListener(controlador);
	}
	
	public void mostrarMensaje(String error, String titulo) {
		JOptionPane.showMessageDialog(this, //ventana
				error, //mensaje
				titulo, //titulo ventana
				JOptionPane.INFORMATION_MESSAGE); //tipo icono de la ventana 
	}
	
	public void mostrarError(String error, String titulo) {
		JOptionPane.showMessageDialog(this, //ventana
				error, //mensaje
				titulo, //titulo ventana
				JOptionPane.ERROR_MESSAGE); //tipo icono de la ventana 
	}
	
	
	public void hacerVisibleScrp(boolean b) {
		scrpConsulta.setVisible(b);
		
	}
	
	public String obtenerRegion() {
		
		String region= (String) cmbRegion.getSelectedItem();
		
		
		return region;
	}
	
	public int obtenerDistincion() {
		
	    int distincion= cmbDistincion.getSelectedIndex();
		
		
		return distincion;
	}
	
	public void cargarCombo(ArrayList<String> listaReg) {
		dcbReg.removeAllElements();
		dcbReg.addElement("TODAS");
		dcbReg.addAll(listaReg);
	}
	
	public int obtenerRestauranteSeleccionado() {
		return tblRestaurantes.getSelectedRow();//selecciono la fila que clicko
	}
	
	public String devolverNombre() {
		 String nombre= tblRestaurantes.getModel().getValueAt(obtenerRestauranteSeleccionado(), 0).toString(); 
		 
		 return nombre;
	}
	 
}
