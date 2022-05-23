package michelin.model;

public class Restaurante {
	private int id;
	private String nombre;
	private String region;
	private String ciudad;
	private int distincion;
	private String direccion;
	private double precio_min;
	private double precio_max;
	private String cocina;
	private String telefono;
	private String web;
	
	
	
	public Restaurante(int id, String nombre, String region, String ciudad, int distincion, String direccion,
			double precio_min, double precio_max, String cocina, String telefono, String web) {
		this.id = id;
		this.nombre = nombre;
		this.region = region;
		this.ciudad = ciudad;
		this.distincion = distincion;
		this.direccion = direccion;
		this.precio_min = precio_min;
		this.precio_max = precio_max;
		this.cocina = cocina;
		this.telefono = telefono;
		this.web = web;
	}

	

	public int getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
	}


	public String getRegion() {
		return region;
	}


	public String getCiudad() {
		return ciudad;
	}


	public int getDistincion() {
		return distincion;
	}


	public String getDireccion() {
		return direccion;
	}


	public double getPrecio_min() {
		return precio_min;
	}


	public double getPrecio_max() {
		return precio_max;
	}


	public String getCocina() {
		return cocina;
	}


	public String getTelefono() {
		return telefono;
	}


	public String getWeb() {
		return web;
	}

	

	
	
	
	
	
}
