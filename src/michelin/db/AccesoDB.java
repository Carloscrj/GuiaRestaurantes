package michelin.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoDB {
	 private String driver; //tipo de gestor de base de datos que vamos a usar
	    private String url; //atributo para la ubicación del archivo 

	    public AccesoDB() { //constructor por defecto
	        driver = "org.sqlite.JDBC"; //nombre que hay que darle para referirnos a SQLite
	        url = "jdbc:sqlite:db/GUIA_MICHELIN.db"; //ubicación del archivo 
	    }

	    //sin la libreria añadida, este método no podríamos usarlo:
	    public Connection getConexion() throws ClassNotFoundException, SQLException {
	            Class.forName(driver);//establecemos cuál es el sgbd que vamos a usar
	            Connection conexion = DriverManager.getConnection(url);

	        return conexion;
	    }
}
