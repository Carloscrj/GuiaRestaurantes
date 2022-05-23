package michelin.ejecutable;

import java.awt.EventQueue;

import michelin.control.ControladorRestauranteMichelin;
import michelin.view.PanelConsultaRestaurantes;
import michelin.view.PanelModificarRestaurantes;
import michelin.view.PanelRegistrarRestaurantes;
import michelin.view.VPrincipal;



public class InicioRestauranteMichelin {

	public static void main(String[] args) {
EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				VPrincipal ventana = new VPrincipal(); //para crear la ventana necesitamos los datos por parámetro
				PanelConsultaRestaurantes paConsultar= new PanelConsultaRestaurantes();
				PanelRegistrarRestaurantes paRegistrar= new PanelRegistrarRestaurantes();
				PanelModificarRestaurantes paModificarRestaurantes= new PanelModificarRestaurantes();
				
				ControladorRestauranteMichelin controlador= new ControladorRestauranteMichelin(ventana, paConsultar, paRegistrar, paModificarRestaurantes);
				
				ventana.setControlador(controlador);
				paConsultar.setControlador(controlador);
				paRegistrar.setControlador(controlador);
				paModificarRestaurantes.setControlador(controlador);
				
				ventana.hacerVisible();
			}
		});
	}

}
