package ar.edu.unju.fi.poo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	@Column
	private String nombre;
	@Column
	private String email;
	
	/**
	 * Constructor sin parametros 
	 */
	public Persona() {
		// TODO Auto-generated constructor stub
	}

	/** Este constructor recibe todos los atributos del objeto
	 * 
	 * @param id
	 * @param nombre
	 * @param email
	 */
	public Persona(Long id, String nombre, String email) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
	}

	/** Este constructor recibe los atributos del objeto y el ID lo genera automaticamente
	 * 
	 * @param nombre
	 * @param email
	 */
	public Persona(String nombre, String email) {
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
