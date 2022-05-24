package michelin.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;

import michelin.control.ControladorRestauranteMichelin;
import michelin.model.Restaurante;
import michelin.persistencia.GuiaMichelinPersistencia;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class PanelModificarRestaurantes extends JPanel {
	private static final int ANCHO = 800;
	private static final int ALTO = 600;
	private static final String NOMBRE = "Nombre:";
	private static final String COCINA = "Cocina:";
	private static final String REGION = "Región:";
	private static final String CIUDAD = "Ciudad:";
	private static final String DIRECCION = "Dirección::";
	private static final String DISTINCION = "Distinción:";
	private static final String PRECIO_MINIMO = "Precio Mínimo:";
	private static final String PRECIO_MAXIMO = "máximo:";
	private static final String TELEFONO = "Teléfono:";
	private static final String WEB = "Web:";
	public static final String BUSCAR = "Buscar:";
	public static final String GUARDAR_RESTAURANTE = "Guardar Restaurante";
	public static final String CANCELAR = "Cancelar";
	public static final String[] COCINA_TIPO = {"Creativa", "Moderna", "Tradicional", "Regional", "Fusión"};
	
	public JTextField txtNombre;
	public JTextField txtCiudad;
	public JTextField txtDireccion;
	public JTextField txtPrecioMinimo;
	public JTextField txtPrecioMaximo;
	public JTextField txtTelefono;
	public JTextField txtWeb;
	public JComboBox <String>cmbCocina;
	public JComboBox <String>cmbRegion;
	public DefaultComboBoxModel<String> dcbReg;
	public JButton btnGuardarDatos;
	public JButton btnLimpiarDatos;
	public JSpinner spnDistincion;
	private JButton btnBuscar;
	
	
	public PanelModificarRestaurantes() {
		setSize(ANCHO, ALTO);
		setLayout(null);
		
		JLabel lblTitulo = new JLabel("Modificar Restaurante");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitulo.setBounds(69, 83, 189, 27);
		add(lblTitulo);
		
		JLabel lblNombre = new JLabel(NOMBRE);
		lblNombre.setBounds(36, 138, 66, 13);
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(112, 135, 96, 19);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblCocina = new JLabel(COCINA);
		lblCocina.setBounds(218, 138, 45, 13);
		add(lblCocina);
		
		cmbCocina = new JComboBox<String>();
		cmbCocina.setModel(new DefaultComboBoxModel<String>(COCINA_TIPO));
		cmbCocina.setBounds(279, 134, 81, 21);
		add(cmbCocina);
		
		JLabel lblRegion = new JLabel(REGION);
		lblRegion.setBounds(36, 193, 45, 13);
		add(lblRegion);
		
		cmbRegion = new JComboBox<String>();
		dcbReg = new DefaultComboBoxModel<String>();
		cmbRegion.setModel(dcbReg);
		cmbRegion.setBounds(112, 189, 81, 21);
		add(cmbRegion);
		
		JLabel lblCiudad = new JLabel(CIUDAD);
		lblCiudad.setBounds(302, 193, 45, 13);
		add(lblCiudad);
		
		txtCiudad = new JTextField();
		txtCiudad.setBounds(357, 190, 123, 19);
		add(txtCiudad);
		txtCiudad.setColumns(10);
		
		JLabel lblDireccion = new JLabel(DIRECCION);
		lblDireccion.setBounds(36, 254, 60, 13);
		add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(112, 251, 368, 19);
		add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblDistincion = new JLabel(DISTINCION);
		lblDistincion.setBounds(36, 319, 60, 13);
		add(lblDistincion);
		
		JLabel lblPrecioMinimo = new JLabel(PRECIO_MINIMO);
		lblPrecioMinimo.setBounds(166, 319, 117, 13);
		add(lblPrecioMinimo);
		
		txtPrecioMinimo = new JTextField();
		txtPrecioMinimo.setBounds(256, 316, 81, 19);
		add(txtPrecioMinimo);
		txtPrecioMinimo.setColumns(10);
		
		JLabel lblPrecioMaximo = new JLabel(PRECIO_MAXIMO);
		lblPrecioMaximo.setBounds(357, 319, 67, 13);
		add(lblPrecioMaximo);
		
		txtPrecioMaximo = new JTextField();
		txtPrecioMaximo.setBounds(434, 316, 75, 19);
		add(txtPrecioMaximo);
		txtPrecioMaximo.setColumns(10);
		
		JLabel lblTelefono = new JLabel(TELEFONO);
		lblTelefono.setBounds(36, 375, 75, 13);
		add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(132, 372, 126, 19);
		add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblNewLabel = new JLabel(WEB);
		lblNewLabel.setBounds(302, 375, 45, 13);
		add(lblNewLabel);
		
		txtWeb = new JTextField();
		txtWeb.setBounds(341, 372, 189, 19);
		add(txtWeb);
		txtWeb.setColumns(10);
		
		btnGuardarDatos = new JButton(GUARDAR_RESTAURANTE);
		btnGuardarDatos.setBounds(81, 464, 189, 21);
		add(btnGuardarDatos);
		
		btnLimpiarDatos = new JButton(CANCELAR);
		btnLimpiarDatos.setBounds(302, 464, 113, 21);
		add(btnLimpiarDatos);
		
		spnDistincion = new JSpinner();
		spnDistincion.setModel(new SpinnerNumberModel(1, 1, 3, 1));
		spnDistincion.setBounds(112, 316, 30, 20);
		add(spnDistincion);
		
		btnBuscar = new JButton(BUSCAR);
		btnBuscar.setBounds(395, 134, 85, 21);
		add(btnBuscar);
	}
	
	public void setControlador(ControladorRestauranteMichelin controlador) {
		cmbRegion.addActionListener(controlador);
		cmbCocina.addActionListener(controlador);
		btnGuardarDatos.addActionListener(controlador);
		btnLimpiarDatos.addActionListener(controlador);
		btnBuscar.addActionListener(controlador);
	}
	
	public void mostrarError(String error, String titulo) {
		JOptionPane.showMessageDialog(this, //ventana
				error, //mensaje
				titulo, //titulo ventana
				JOptionPane.ERROR_MESSAGE); //tipo icono de la ventana 
	}
	
	public void mostrarMensaje(String error, String titulo) {
		JOptionPane.showMessageDialog(this, //ventana
				error, //mensaje
				titulo, //titulo ventana
				JOptionPane.INFORMATION_MESSAGE); //tipo icono de la ventana 
	}
	
	public void cargarCombo(ArrayList<String> listaReg) {
		dcbReg.removeAllElements();
		dcbReg.addAll(listaReg);
	}
	
	
	
	public void limpiarPanel() { 
		txtNombre.setText("");
		txtCiudad.setText("");
		txtDireccion.setText("");
		txtPrecioMinimo.setText("");
		txtPrecioMaximo.setText("");
		txtTelefono.setText("");
		txtWeb.setText("");
		cmbCocina.setSelectedIndex(0);
		cmbRegion.setSelectedIndex(0);
		spnDistincion.setValue(1);
	}
	
	public void habilitarElementos(boolean b) { 
		txtNombre.setEnabled(b);
		txtCiudad.setEnabled(!b);
		txtDireccion.setEnabled(!b);
		txtPrecioMinimo.setEnabled(!b);
		txtPrecioMaximo.setEnabled(!b);
		txtTelefono.setEnabled(!b);
		txtWeb.setEnabled(!b);
		cmbCocina.setEnabled(!b);
		cmbRegion.setEnabled(!b);
		spnDistincion.setEnabled(!b);
		btnBuscar.setEnabled(b);
		btnGuardarDatos.setEnabled(!b);
		btnLimpiarDatos.setEnabled(!b);
	}
	
	public void habilitarElementos2(boolean b) { 
		txtNombre.setEnabled(!b);
		txtCiudad.setEnabled(b);
		txtDireccion.setEnabled(b);
		txtPrecioMinimo.setEnabled(b);
		txtPrecioMaximo.setEnabled(b);
		txtTelefono.setEnabled(b);
		txtWeb.setEnabled(b);
		cmbCocina.setEnabled(b);
		cmbRegion.setEnabled(b);
		spnDistincion.setEnabled(b);
		btnBuscar.setEnabled(!b);
		btnGuardarDatos.setEnabled(b);
		btnLimpiarDatos.setEnabled(b);
	}
	
	
	
	public String nombre(){
		String nombre= txtNombre.getText();
		
		return nombre;
	}
	
	public String region(){
		
		String region= (String) cmbRegion.getSelectedItem();
		
		return region;
	}
	
	public void rellenarPanel(Restaurante restaurante) {
		txtNombre.setText(restaurante.getNombre());
		txtCiudad.setText(restaurante.getCiudad());
		txtDireccion.setText(restaurante.getDireccion());
		txtPrecioMinimo.setText(String.valueOf(restaurante.getPrecio_min()));
		txtPrecioMaximo.setText(String.valueOf(restaurante.getPrecio_max()));
		txtTelefono.setText(restaurante.getTelefono());
		txtWeb.setText(restaurante.getWeb());
		cmbCocina.setSelectedItem(restaurante.getCocina());
		cmbRegion.setSelectedItem(restaurante.getRegion());
		spnDistincion.setValue(restaurante.getDistincion());
	}
	
	public Restaurante filtrarFallos(){
		String ciudad = txtCiudad.getText();
		Restaurante restaurante= null;
		if (ciudad.isBlank()) {
			mostrarError("Campo ciudad no puede quedarse en blanco", "Error");
		} else {
			try {
				double precioMinimo = Double.valueOf(txtPrecioMinimo.getText());
				double precioMaximo = Double.valueOf(txtPrecioMaximo.getText());
				
				if (precioMinimo>precioMaximo) {
					mostrarError("El precio mínimo no puede ser superior al precio máximo", "Error");
				} else {
					int id=0;
					String nombre = txtNombre.getText();
					String region= region();
					int distincion = (int) spnDistincion.getValue();
					String direccion = txtDireccion.getText();
					String cocina = (String) cmbCocina.getSelectedItem();
					String telefono = txtTelefono.getText();
					String web = txtWeb.getText();
					
					restaurante = new Restaurante(id, nombre, region, ciudad, distincion, direccion, precioMinimo, precioMaximo, cocina, telefono, web);
					
					
				}
				
			} catch (NumberFormatException e) {
				mostrarError("El precio mínimo y el precio máximo deben ser números, no letras.", "Error");
			}
		}
		return restaurante;
	}
	
	/*public Restaurante restaurante() {
		
		Restaurante restaurante;
		int id=0;
		String nombre= nombre();
		String region= region();
		String ciudad= txtCiudad.getText();
		int distincion= (int) spnDistincion.getValue();
		String direccion= txtDireccion.getText();
		double precio_min= Double.parseDouble(txtPrecioMinimo.getText());
		double precio_max= Double.parseDouble(txtPrecioMaximo.getText());
		String cocina= (String) cmbCocina.getSelectedItem();
		String telefono= txtTelefono.getText();
		String web= txtWeb.getText();
		restaurante = new Restaurante(id, nombre, region, ciudad, distincion, direccion, precio_min, precio_max, cocina, telefono, web);
		
		if (nombre.equals("")) {
			mostrarError("Debe introducir un nombre", "Error");
		}else if(ciudad.equals("")) {
			mostrarError("No se ha seleccionado ninguna ciudad", "Error");
		}else if(precio_min>precio_max) {
			mostrarError("Error precio minimo mayor que el maximo", "Error");
		}
		return restaurante;
	}*/
}