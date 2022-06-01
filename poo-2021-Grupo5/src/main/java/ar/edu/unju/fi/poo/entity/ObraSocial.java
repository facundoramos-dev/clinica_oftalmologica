package ar.edu.unju.fi.poo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class ObraSocial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nombre;
	
	@Column
	private String telefono;
	
	@Column
	private String direccion;
	
	/**
	 * Constructor sin parametros 
	 */
	public ObraSocial() {
		// TODO Auto-generated constructor stub
	}

	/** Este constructor recibe todos los atributos del objeto 

	 * 
	 * @param id
	 * @param nombre
	 * @param telefono
	 * @param direccion
	 */
	public ObraSocial(Long id, String nombre, String telefono, String direccion) {
		this.id = id;
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
