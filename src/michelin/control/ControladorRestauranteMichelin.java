package michelin.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import michelin.model.Restaurante;
import michelin.persistencia.GuiaMichelinPersistencia;
import michelin.view.PanelConsultaRestaurantes;
import michelin.view.PanelModificarRestaurantes;
import michelin.view.PanelRegistrarRestaurantes;
import michelin.view.VPrincipal;

public class ControladorRestauranteMichelin implements ActionListener {

	VPrincipal ventana;
	PanelConsultaRestaurantes paConsultar;
	PanelRegistrarRestaurantes paRegistrar;
	PanelModificarRestaurantes paModificar;
	private GuiaMichelinPersistencia gp;

	public ControladorRestauranteMichelin(VPrincipal ventana, PanelConsultaRestaurantes paConsultar, PanelRegistrarRestaurantes paRegistrar, PanelModificarRestaurantes paModificar) {
		this.ventana = ventana;
		this.paConsultar = paConsultar;
		this.paRegistrar= paRegistrar;
		this.paModificar= paModificar;
		this.gp = new GuiaMichelinPersistencia();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JMenuItem) {
			if (e.getActionCommand().equals(VPrincipal.MNTN_CON_RESTAURANTES)) {
				ArrayList<String> listaComboBox = new ArrayList<String>();
				listaComboBox = gp.obtenerValoresCombo();
				paConsultar.cargarCombo(listaComboBox);
				ventana.cargarPanel(paConsultar);
				paConsultar.hacerVisibleScrp(false);
			} else if (e.getActionCommand().equals(VPrincipal.MNTN_REG_RESTAURANTES)) {
				ventana.cargarPanel(paRegistrar);
			} else if (e.getActionCommand().equals(VPrincipal.MNTN_MOD_RESTAURANTES)) {
				ArrayList<String> listaComboBox = new ArrayList<String>();
				listaComboBox = gp.obtenerValoresCombo();
				paModificar.cargarCombo(listaComboBox);
				ventana.cargarPanel(paModificar);
				paModificar.habilitarElementos(true);
			} else if (e.getActionCommand().equals(VPrincipal.MNTN_SALIR)) {
				int resp = JOptionPane.showConfirmDialog(ventana, "Se va a cerrrar la aplicacion, Desea cerrarla?",
						"Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (resp == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}

		} else if (e.getSource() instanceof JButton) { // si el componente en el que se ha producido el evento es un
														// botón
			if (e.getActionCommand().equals(PanelConsultaRestaurantes.CONSULTAR)) { // así identifico que se ha
																					// seleccionado el botón limpiar
				ArrayList<Restaurante> listaRestaurantes = new ArrayList<Restaurante>();

				String region = paConsultar.obtenerRegion();
				int distincion = paConsultar.obtenerDistincion();
				if (region.equals("TODAS") && distincion == 0) {
					listaRestaurantes = gp.selecccionarRestaurante();

				} else if (!region.equals("TODAS") && distincion == 0) {
					listaRestaurantes = gp.obtenerRestauranteRegion(region);

				} else if (region.equals("TODAS") && distincion != 0) {
					listaRestaurantes = gp.obtenerRestauranteDistincion(distincion);

				} else if (!region.equals("TODAS") && distincion != 0) {
					listaRestaurantes = gp.obtenerRestauranteRegionYdistincion(region, distincion);

				}

				if (listaRestaurantes.isEmpty()) {
					paConsultar.mostrarError("No hay datos que mostrar", "Error");
				} else {
					paConsultar.rellenarTabla(listaRestaurantes);
					paConsultar.hacerVisibleScrp(true);
				}

			} else if (e.getActionCommand().equals(PanelConsultaRestaurantes.ELIMINAR)) {
				String nombreRestaurante= paConsultar.devolverNombre();
				
				if (nombreRestaurante == null) {
					paConsultar.mostrarError("No se ha seleccionado ningún restaurante", "Error");
				} else {
					int resp=JOptionPane.showConfirmDialog(ventana,
							"Se va a borrar un restaurantes, ¿Desea continuar?",
							"Confirmar borrado",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
							if (resp== JOptionPane.YES_OPTION) {
								ArrayList<Restaurante> listaRestaurantes = new ArrayList<Restaurante>();
								gp.eliminarRestaurante(nombreRestaurante);
								String region = paConsultar.obtenerRegion();
								int distincion = paConsultar.obtenerDistincion();
								listaRestaurantes = gp.obtenerRestauranteRegionYdistincion(region, distincion);
								paConsultar.rellenarTabla(listaRestaurantes);
								paConsultar.hacerVisibleScrp(true);
							}
					
				}
			}else if (e.getActionCommand().equals(PanelRegistrarRestaurantes.GUARDAR_DATOS)) {
				/*String nombreRestaurante= paRegistrar.nombre();
				String ciudadSeleccionada= paRegistrar.txtCiudad.getText();
				double precioMinimo= Double.parseDouble(paRegistrar.txtPrecioMinimo.getText());
				double precioMaximo= Double.parseDouble(paRegistrar.txtPrecioMinimo.getText());*/
				String nombre= paRegistrar.nombre();
				if (paRegistrar.restaurante()==null) {
					paRegistrar.mostrarError("Error el restaurante está vacio", "Error");
				}else if(gp.obtenerNombreRestaurante(nombre)){
					paRegistrar.mostrarError("Error ya existe un restaurante con ese nombre", "Resultado de operación");
				}else {
					gp.registrarRestaurante(paRegistrar.restaurante());
					paRegistrar.mostrarMensaje("Se ha registrado el restaurante con éxito.", "Resultado de la operación");
					paRegistrar.limpiarPanel();
				}
			}else if (e.getActionCommand().equals(PanelRegistrarRestaurantes.LIMPIAR_DATOS)) {
				paRegistrar.limpiarPanel();
			}else if (e.getActionCommand().equals(PanelModificarRestaurantes.GUARDAR_RESTAURANTE)) {
				Restaurante restaurante= paModificar.filtrarFallos();
				if (restaurante==null) {
					paRegistrar.mostrarError("Error vuelva a introducir los campos correctamente", "Error");
				}else {
					gp.modificarRestaurante(restaurante);
					paModificar.mostrarMensaje("Se ha modificado el restaurante con éxito.", "Resultado de la operación");
				}
				
			}else if (e.getActionCommand().equals(PanelModificarRestaurantes.CANCELAR)) {
				paModificar.limpiarPanel();
				paModificar.habilitarElementos(true);
				
			}else if (e.getActionCommand().equals(PanelModificarRestaurantes.BUSCAR)) {
				String nombre= paModificar.nombre();
				if(nombre.isEmpty()) {
					paModificar.mostrarError("No se han encontrado datos", "Resultado de operación");
						
				}else{
					Restaurante restaurante= gp.obtenerRestauranteNombreMedias(nombre);
					paModificar.rellenarPanel(restaurante);
					paModificar.habilitarElementos2(true);
					
				}
				
				
			}

		}

	}

}
