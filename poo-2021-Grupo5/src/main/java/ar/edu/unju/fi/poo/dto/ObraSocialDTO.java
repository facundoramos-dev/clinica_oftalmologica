package ar.edu.unju.fi.poo.dto;

import java.io.Serializable;

public class ObraSocialDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String nombre;
	
	private String telefono;
	
	private String direccion;
	
	/**
	 * Constructor sin parametros 
	 */
	public ObraSocialDTO() {
		// TODO Auto-generated constructor stub
	}

	/** Este constructor recibe todos los atributos del objeto
	 * 
	 * @param id
	 * @param nombre
	 * @param telefono
	 * @param direccion
	 */
	public ObraSocialDTO(Long id, String nombre, String telefono, String direccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
	}

	/** Este constructor recibe los atributos del objeto y el ID lo genera automaticamente

	 * 
	 * @param nombre
	 * @param telefono
	 * @param direccion
	 */
	public ObraSocialDTO(String nombre, String telefono, String direccion) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
	}

	//Geters y Setters. Métodos de instancia

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
}
