package ar.edu.unju.fi.poo.dto;

import java.io.Serializable;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

public class PacienteDTO extends PersonaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long dni;
	
	private String domicilio;
	
	private Long numCelular;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private ObraSocialDTO obraSocial;
	
	/**
	 * Constructor sin parametros 
	 */
	public PacienteDTO() {
		// TODO Auto-generated constructor stub
	}

	/** Este constructor recibe todos los atributos del objeto
	 * 
	 * @param id
	 * @param nombre
	 * @param email
	 * @param dni
	 * @param domicilio
	 * @param numCelular
	 * @param obraSocial
	 */
	public PacienteDTO(Long id, String nombre, String email, Long dni, String domicilio, Long numCelular, ObraSocialDTO obraSocial) {
		super(id,nombre,email);
		this.dni = dni;
		this.domicilio = domicilio;
		this.numCelular = numCelular;
		this.obraSocial = obraSocial;
	}

	/** Este constructor recibe los atributos del objeto y el ID lo genera automaticamente
	 * 
	 * @param nombre
	 * @param email
	 * @param dni
	 * @param domicilio
	 * @param numCelular
	 * @param obraSocial
	 */
	public PacienteDTO(String nombre, String email, Long dni, String domicilio, Long numCelular, ObraSocialDTO obraSocial) {
		super(nombre,email);
		this.dni = dni;
		this.domicilio = domicilio;
		this.numCelular = numCelular;
		this.obraSocial = obraSocial;
	}

	//Geters y Setters. Métodos de instancia
	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Long getNumCelular() {
		return numCelular;
	}

	public void setNumCelular(Long numCelular) {
		this.numCelular = numCelular;
	}

	public ObraSocialDTO getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(ObraSocialDTO obraSocial) {
		this.obraSocial = obraSocial;
	}

}
