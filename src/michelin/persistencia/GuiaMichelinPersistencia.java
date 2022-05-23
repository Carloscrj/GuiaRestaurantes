package michelin.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import michelin.db.AccesoDB;
import michelin.model.Restaurante;

public class GuiaMichelinPersistencia {
	private AccesoDB acceso;

	public GuiaMichelinPersistencia() {
		acceso = new AccesoDB(); //inicializamos el objeto pero no le pasamos nada por parámetro porque ya lo tiene
	}
	
	public ArrayList<Restaurante> selecccionarRestaurante() {
		ArrayList<Restaurante> listaRestaurantes = new ArrayList<Restaurante>();
		
		
		String query = "SELECT " + GuiaMichelinContract.ID + ", "
								 + GuiaMichelinContract.NOMBRE + ", "
								 + GuiaMichelinContract.REGION + ", "
								 + GuiaMichelinContract.CIUDAD + ", "
								 + GuiaMichelinContract.DISTINCION + ", "
								 + GuiaMichelinContract.DIRECCION + ", "
								 + GuiaMichelinContract.PRECIO_MIN + ", "
								 + GuiaMichelinContract.PRECIO_MAX + ", "
								 + GuiaMichelinContract.COCINA + ", " 
								 + GuiaMichelinContract.TELEFONO + ", "
								 + GuiaMichelinContract.WEB
								 + " FROM " + GuiaMichelinContract.NOM_TABLA;	
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rsult = null;

		try {
			con = acceso.getConexion(); //establecer conexión
			stmt = con.createStatement(); //el mismo catch SQLException coge sus posibles errores
			rsult = stmt.executeQuery(query); //ejecutar la query
			
			Restaurante restaurante;
			int id;
			String nombre;
			String region;
			String ciudad;
			int distincion;
			String direccion;
			double precio_min;
			double precio_max;
			String cocina;
			String telefono;
			String web;
			
			
	
			while (rsult.next()) { //recuperar la consulta, como en PLSQL, vamos sacando los datos 
				id = rsult.getInt(1);
				nombre = rsult.getString(2);
				region = rsult.getString(3);//resuperamos por posición; posición SQL, entonces empieza en 1
				ciudad = rsult.getString(4);
				distincion = rsult.getInt(5);
				direccion = rsult.getString(6);
				precio_min = rsult.getDouble(7);
				precio_max = rsult.getDouble(8);
				cocina = rsult.getString(9);
				telefono = rsult.getString(10);
				web = rsult.getString(11);
				
				restaurante = new Restaurante(id, nombre, region, ciudad, distincion, direccion, precio_min, precio_max, cocina, telefono, web);
			  //System.out.println(alumno);
				listaRestaurantes.add(restaurante);
				
				
				
				
			}
			
		} catch (ClassNotFoundException e) { //si no encuentra el driver, da la excepción
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace(); //saca toda la info por consola en rojo del error
		} catch (SQLException e) { //si no encuentra la url, da error
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		} finally { //para liberar recursos, cerramos lo creado; en el orden inverso en que se han abierto
			try {
				if (rsult != null) rsult.close(); //solo los cerramos si son distintos de null; es decir, si se han podido abrir
				if (stmt != null) stmt.close(); 
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		
		return listaRestaurantes;
	}
	
	public ArrayList<Restaurante> obtenerRestauranteRegion(String region) {
		ArrayList<Restaurante> listaRestaurantes = new ArrayList<Restaurante>();
		
		
		String query = "SELECT " + GuiaMichelinContract.ID + ", "
				 + GuiaMichelinContract.NOMBRE + ", "
				 + GuiaMichelinContract.REGION + ", "
				 + GuiaMichelinContract.CIUDAD + ", "
				 + GuiaMichelinContract.DISTINCION + ", "
				 + GuiaMichelinContract.DIRECCION + ", "
				 + GuiaMichelinContract.PRECIO_MIN + ", "
				 + GuiaMichelinContract.PRECIO_MAX + ", "
				 + GuiaMichelinContract.COCINA + ", " 
				 + GuiaMichelinContract.TELEFONO + ", "
				 + GuiaMichelinContract.WEB
				 + " FROM " + GuiaMichelinContract.NOM_TABLA+
				  " WHERE " + GuiaMichelinContract.REGION+ "= ?";	
				 
								
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rsult= null;
		

		try {
			con = acceso.getConexion(); //establecer conexión
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, region);
			 //el mismo catch SQLException coge sus posibles errores
			pstmt.executeQuery();
			rsult = pstmt.executeQuery(); //ejecutar la query
			
			Restaurante restaurante;
			int id;
			String nombre;
			String ciudad;
			int distincion;
			String direccion;
			double precio_min;
			double precio_max;
			String cocina;
			String telefono;
			String web;
			
			
			
			while (rsult.next()) { //recuperar la consulta, como en PLSQL, vamos sacando los datos 
				id = rsult.getInt(1);
				nombre = rsult.getString(2);
				region = rsult.getString(3);//resuperamos por posición; posición SQL, entonces empieza en 1
				ciudad = rsult.getString(4);
				distincion = rsult.getInt(5);
				direccion = rsult.getString(6);
				precio_min = rsult.getDouble(7);
				precio_max = rsult.getDouble(8);
				cocina = rsult.getString(9);
				telefono = rsult.getString(10);
				web = rsult.getString(11);
				
				restaurante = new Restaurante(id, nombre, region, ciudad, distincion, direccion, precio_min, precio_max, cocina, telefono, web);
			  //System.out.println(alumno);
				listaRestaurantes.add(restaurante);
				
			}
			
		} catch (ClassNotFoundException e) { //si no encuentra el driver, da la excepción
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace(); //saca toda la info por consola en rojo del error
		} catch (SQLException e) { //si no encuentra la url, da error
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		} finally { //para liberar recursos, cerramos lo creado; en el orden inverso en que se han abierto
			try {
				if (rsult != null) rsult.close(); //solo los cerramos si son distintos de null; es decir, si se han podido abrir
				if (pstmt != null) pstmt.close(); 
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		
		return listaRestaurantes;
	}
	
	public ArrayList<Restaurante> obtenerRestauranteDistincion(int distincion) {
		ArrayList<Restaurante> listaRestaurantes = new ArrayList<Restaurante>();
		
		String query = "SELECT " + GuiaMichelinContract.ID + ", "
								 + GuiaMichelinContract.NOMBRE + ", "
								 + GuiaMichelinContract.REGION + ", "
								 + GuiaMichelinContract.CIUDAD + ", "
								 + GuiaMichelinContract.DISTINCION + ", "
								 + GuiaMichelinContract.DIRECCION + ", "
								 + GuiaMichelinContract.PRECIO_MIN + ", "
								 + GuiaMichelinContract.PRECIO_MAX + ", "
								 + GuiaMichelinContract.COCINA + ", " 
								 + GuiaMichelinContract.TELEFONO + ", "
								 + GuiaMichelinContract.WEB
								 + " FROM " + GuiaMichelinContract.NOM_TABLA+
								 " WHERE " + GuiaMichelinContract.DISTINCION + "= ?";	
		
		Connection con = null;
		PreparedStatement pstmt = null; //estas tres lineas se ponen cuando hay where cuando no lo hay cambian
		ResultSet rsult= null;

		try {
			con = acceso.getConexion(); //establecer conexión
			pstmt= con.prepareStatement(query);
			pstmt.setInt(1, distincion);
			 //el mismo catch SQLException coge sus posibles errores
			pstmt.executeQuery();
			rsult = pstmt.executeQuery(); //ejecutar la query
			
			Restaurante restaurante;
			int id;
			String nombre;
			String region;
			String ciudad;
			String direccion;
			double precio_min;
			double precio_max;
			String cocina;
			String telefono;
			String web;
			
			
			while (rsult.next()) { //recuperar la consulta, como en PLSQL, vamos sacando los datos 
				id = rsult.getInt(1);
				nombre = rsult.getString(2);
				region = rsult.getString(3);//resuperamos por posición; posición SQL, entonces empieza en 1
				ciudad = rsult.getString(4);
				distincion= rsult.getInt(5);
				direccion = rsult.getString(6);
				precio_min = rsult.getDouble(7);
				precio_max = rsult.getDouble(8);
				cocina = rsult.getString(9);
				telefono = rsult.getString(10);
				web = rsult.getString(11);
				
				restaurante = new Restaurante(id, nombre, region, ciudad, distincion, direccion, precio_min, precio_max, cocina, telefono, web);
				listaRestaurantes.add(restaurante);
				
				
				
				
			}
			
		} catch (ClassNotFoundException e) { //si no encuentra el driver, da la excepción
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace(); //saca toda la info por consola en rojo del error
		} catch (SQLException e) { //si no encuentra la url, da error
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		} finally { //para liberar recursos, cerramos lo creado; en el orden inverso en que se han abierto
			try {
				if (rsult != null) rsult.close(); //solo los cerramos si son distintos de null; es decir, si se han podido abrir
				if (pstmt != null) pstmt.close(); 
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		
		return listaRestaurantes;
	}
	
	public ArrayList<Restaurante> obtenerRestauranteRegionYdistincion(String region, int distincion) {
		ArrayList<Restaurante> listaRestaurantes = new ArrayList<Restaurante>();
		
		String query = "SELECT " + GuiaMichelinContract.ID + ", "
								 + GuiaMichelinContract.NOMBRE + ", "
								 + GuiaMichelinContract.REGION + ", "
								 + GuiaMichelinContract.CIUDAD + ", "
								 + GuiaMichelinContract.DISTINCION + ", "
								 + GuiaMichelinContract.DIRECCION + ", "
								 + GuiaMichelinContract.PRECIO_MIN + ", "
								 + GuiaMichelinContract.PRECIO_MAX + ", "
								 + GuiaMichelinContract.COCINA + ", " 
								 + GuiaMichelinContract.TELEFONO + ", "
								 + GuiaMichelinContract.WEB
								 + " FROM " + GuiaMichelinContract.NOM_TABLA+
								 " WHERE " + GuiaMichelinContract.REGION + "= ? AND "
								 + GuiaMichelinContract.DISTINCION + "= ?";
								 	
		
		Connection con = null;
		PreparedStatement pstmt = null; //estas tres lineas se ponen cuando hay where cuando no lo hay cambian
		ResultSet rsult= null;

		try {
			con = acceso.getConexion(); //establecer conexión
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, region);
			pstmt.setInt(2, distincion);
			 //el mismo catch SQLException coge sus posibles errores
			pstmt.executeQuery();
			rsult = pstmt.executeQuery(); //ejecutar la query
			
			Restaurante restaurante;
			int id;
			String nombre;
			String ciudad;
			String direccion;
			double precio_min;
			double precio_max;
			String cocina;
			String telefono;
			String web;
			
			
			while (rsult.next()) { //recuperar la consulta, como en PLSQL, vamos sacando los datos 
				id = rsult.getInt(1);
				nombre = rsult.getString(2);
				region = rsult.getString(3);//resuperamos por posición; posición SQL, entonces empieza en 1
				ciudad = rsult.getString(4);
				distincion= rsult.getInt(5);
				direccion = rsult.getString(6);
				precio_min = rsult.getDouble(7);
				precio_max = rsult.getDouble(8);
				cocina = rsult.getString(9);
				telefono = rsult.getString(10);
				web = rsult.getString(11);
				
				restaurante = new Restaurante(id, nombre, region, ciudad, distincion, direccion, precio_min, precio_max, cocina, telefono, web);
				listaRestaurantes.add(restaurante);
				
				
			}
			
		} catch (ClassNotFoundException e) { //si no encuentra el driver, da la excepción
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace(); //saca toda la info por consola en rojo del error
		} catch (SQLException e) { //si no encuentra la url, da error
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		} finally { //para liberar recursos, cerramos lo creado; en el orden inverso en que se han abierto
			try {
				if (rsult != null) rsult.close(); //solo los cerramos si son distintos de null; es decir, si se han podido abrir
				if (pstmt != null) pstmt.close(); 
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		
		return listaRestaurantes;
	}
	
	public ArrayList<String> obtenerValoresCombo() {
		ArrayList<String> listaRestaurantes = new ArrayList<String>();
		
		
		String query = "SELECT DISTINCT " + GuiaMichelinContract.REGION
								 + " FROM " + GuiaMichelinContract.NOM_TABLA;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rsult = null;

		try {
			con = acceso.getConexion(); //establecer conexión
			stmt = con.createStatement(); //el mismo catch SQLException coge sus posibles errores
			rsult = stmt.executeQuery(query); //ejecutar la query
			
			String region;
			
			
			while (rsult.next()) { //recuperar la consulta, como en PLSQL, vamos sacando los datos 
				
				region = rsult.getString(GuiaMichelinContract.REGION);//resuperamos por posición; posición SQL, entonces empieza en 1
				
				listaRestaurantes.add(region);
			}
			
		} catch (ClassNotFoundException e) { //si no encuentra el driver, da la excepción
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace(); //saca toda la info por consola en rojo del error
		} catch (SQLException e) { //si no encuentra la url, da error
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		} finally { //para liberar recursos, cerramos lo creado; en el orden inverso en que se han abierto
			try {
				if (rsult != null) rsult.close(); //solo los cerramos si son distintos de null; es decir, si se han podido abrir
				if (stmt != null) stmt.close(); 
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		
		return listaRestaurantes;
	}
	
	public int eliminarRestaurante(String nombre) {
		int resultado = 0;
		
		String query = "DELETE FROM "+GuiaMichelinContract.NOM_TABLA+" WHERE NOMBRE= ?";
		
		Connection  conexion = null;
		PreparedStatement pstm = null;
		
		
		try {
			conexion = acceso.getConexion();
			pstm = conexion.prepareStatement(query);
			pstm.setString(1, nombre);
			
			
			resultado =  pstm.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close(); 
				if (conexion != null) conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		return resultado;
	}
	
	public int registrarRestaurante(Restaurante restaurante) {
		int resultado = 0;
		
		String query = "INSERT INTO RESTAURANTES (NOMBRE, REGION, CIUDAD, DISTINCION, DIRECCION, PRECIO_MIN, PRECIO_MAX, COCINA, TELEFONO, WEB) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection  conexion = null;
		PreparedStatement pstm = null;
		
		
		try {
			
		
			conexion = acceso.getConexion();
			pstm = conexion.prepareStatement(query);
			pstm.setString(1, restaurante.getNombre());
			pstm.setString(2, restaurante.getRegion());
			pstm.setString(3, restaurante.getCiudad());
			pstm.setInt(4, restaurante.getDistincion());
			pstm.setString(5, restaurante.getDireccion());
			pstm.setDouble(6, restaurante.getPrecio_min());
			pstm.setDouble(7, restaurante.getPrecio_max());
			pstm.setString(8, restaurante.getCocina());
			pstm.setString(9, restaurante.getTelefono());
			pstm.setString(10, restaurante.getWeb());
			
			
			
			resultado =  pstm.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close(); 
				if (conexion != null) conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		return resultado;
	}
	

	
	public boolean obtenerNombreRestaurante(String nombre) {
		boolean respuesta= false;
		
		
		String query = "SELECT " + GuiaMichelinContract.NOMBRE
								 + " FROM " + GuiaMichelinContract.NOM_TABLA+
								 " WHERE "+GuiaMichelinContract.NOMBRE+"= ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rsult = null;

		try {
			con = acceso.getConexion(); //establecer conexión
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, nombre);
			pstmt.executeQuery();
			rsult = pstmt.executeQuery(); //ejec
			
			
			
			if(rsult.next()) {
				respuesta = true;
			}
                
			
			
		} catch (ClassNotFoundException e) { //si no encuentra el driver, da la excepción
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace(); //saca toda la info por consola en rojo del error
		} catch (SQLException e) { //si no encuentra la url, da error
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		} finally { //para liberar recursos, cerramos lo creado; en el orden inverso en que se han abierto
			try {
				if (rsult != null) rsult.close(); //solo los cerramos si son distintos de null; es decir, si se han podido abrir
				if (pstmt != null) pstmt.close(); 
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		
		return respuesta;
	}
	
	public Restaurante obtenerRestauranteNombreMedias(String nombre) {
		
		Restaurante restaurante= null;
		
		String query = "SELECT * FROM " + GuiaMichelinContract.NOM_TABLA+
				 " WHERE "+GuiaMichelinContract.NOMBRE+" LIKE ?";
				 
								
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rsult= null;
		

		try {
			con = acceso.getConexion(); //establecer conexión
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, nombre + "%");
			 //el mismo catch SQLException coge sus posibles errores
			pstmt.executeQuery();
			rsult = pstmt.executeQuery(); //ejecutar la query
			
		
			int id;
			String region;
			String ciudad;
			int distincion;
			String direccion;
			double precio_min;
			double precio_max;
			String cocina;
			String telefono;
			String web;
			
			
			
			if (rsult.next()) { //recuperar la consulta, como en PLSQL, vamos sacando los datos 
				id = rsult.getInt(1);
				nombre = rsult.getString(2);
				region = rsult.getString(3);//resuperamos por posición; posición SQL, entonces empieza en 1
				ciudad = rsult.getString(4);
				distincion = rsult.getInt(5);
				direccion = rsult.getString(6);
				precio_min = rsult.getDouble(7);
				precio_max = rsult.getDouble(8);
				cocina = rsult.getString(9);
				telefono = rsult.getString(10);
				web = rsult.getString(11);
				
				restaurante = new Restaurante(id, nombre, region, ciudad, distincion, direccion, precio_min, precio_max, cocina, telefono, web);
				
				
			}
			
		} catch (ClassNotFoundException e) { //si no encuentra el driver, da la excepción
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace(); //saca toda la info por consola en rojo del error
		} catch (SQLException e) { //si no encuentra la url, da error
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		} finally { //para liberar recursos, cerramos lo creado; en el orden inverso en que se han abierto
			try {
				if (rsult != null) rsult.close(); //solo los cerramos si son distintos de null; es decir, si se han podido abrir
				if (pstmt != null) pstmt.close(); 
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		
		return restaurante;
	}
	
	public int modificarRestaurante(Restaurante restaurante) {
		int resultado = 0;
		
		String query = "UPDATE "  + GuiaMichelinContract.NOM_TABLA + " SET " + GuiaMichelinContract.REGION + "= ?, " 
				+ GuiaMichelinContract.CIUDAD + "= ?, " + GuiaMichelinContract.DISTINCION + "= ?, " + GuiaMichelinContract.DIRECCION + "= ?, "
				+ GuiaMichelinContract.PRECIO_MIN + "= ?, " + GuiaMichelinContract.PRECIO_MAX + "= ?, " + GuiaMichelinContract.COCINA + "= ?, " + GuiaMichelinContract.TELEFONO
				+ "= ?, " + GuiaMichelinContract.WEB + "= ? WHERE " + GuiaMichelinContract.NOMBRE + "= ?";
		
		
		Connection conexion = null;
		PreparedStatement pstm = null;
		
		try {
			conexion = acceso.getConexion();
			pstm = conexion.prepareStatement(query);
			
			
			pstm.setString(1, restaurante.getRegion());
			pstm.setString(2, restaurante.getCiudad());
			pstm.setInt(3, restaurante.getDistincion());
			pstm.setString(4, restaurante.getDireccion());
			pstm.setDouble(5, restaurante.getPrecio_min());
			pstm.setDouble(6, restaurante.getPrecio_max());
			pstm.setString(7, restaurante.getCocina());
			pstm.setString(8, restaurante.getTelefono());
			pstm.setString(9, restaurante.getWeb());
			pstm.setString(10, restaurante.getNombre());

	
			resultado = pstm.executeUpdate();
						
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close(); 
				if (conexion != null) conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		
		return resultado;
	}
	
	/*public boolean obtenerRestauranteNombreMitad(String nombre) {
		boolean respuesta= false;
		
		
		String query = "SELECT * FROM " + GuiaMichelinContract.NOM_TABLA+
								 " WHERE "+GuiaMichelinContract.NOMBRE+"= LIKE ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rsult = null;

		try {
			con = acceso.getConexion(); //establecer conexión
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, nombre + "%");
			pstmt.executeQuery();
			rsult = pstmt.executeQuery(); //ejec
			
			
			
			if(rsult.next()) {
				respuesta = true;
			}
                
			
			
		} catch (ClassNotFoundException e) { //si no encuentra el driver, da la excepción
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace(); //saca toda la info por consola en rojo del error
		} catch (SQLException e) { //si no encuentra la url, da error
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		} finally { //para liberar recursos, cerramos lo creado; en el orden inverso en que se han abierto
			try {
				if (rsult != null) rsult.close(); //solo los cerramos si son distintos de null; es decir, si se han podido abrir
				if (pstmt != null) pstmt.close(); 
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		
		return respuesta;
	}*/
	
	
	
	
}


	




