package ar.edu.unju.fi.poo.dto;

import java.io.Serializable;

public abstract class PersonaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String nombre;
	
	private String email;
	
	/**
	 * 
	 */
	public PersonaDTO() {
		// TODO Auto-generated constructor stub
	}

	/** Este constructor recibe todos atributos del objeto 
	 * 
	 * @param id
	 * @param nombre
	 * @param email
	 */
	public PersonaDTO(Long id, String nombre, String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
	}

	/** Este constructor recibe los atributos del objeto y el ID lo genera automaticamente
	 * 
	 * @param nombre
	 * @param email
	 */
	public PersonaDTO(String nombre, String email) {
		this.nombre = nombre;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
