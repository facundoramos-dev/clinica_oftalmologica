package ar.edu.unju.fi.poo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Paciente extends Persona{

	@Column
	private Long dni;
	@Column
	private String domicilio;
	@Column
	private Long numCelular;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "obraSocial_id", nullable = false)
	private ObraSocial obraSocial;

	/**
	 * Constructor sin parametros 
	 */
	public Paciente() {
		// TODO Auto-generated constructor stub
	}

	/**Este constructor recibe todos los atributos del objeto
	 * 
	 * @param id
	 * @param nombre
	 * @param email
	 * @param dni
	 * @param domicilio
	 * @param numCelular
	 * @param obraSocial
	 */
	public Paciente(Long id,String nombre, String email, Long dni, String domicilio, Long numCelular, ObraSocial obraSocial) { 
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
	public Paciente(String nombre, String email, Long dni, String domicilio, Long numCelular, ObraSocial obraSocial) { 
		super(nombre,email);
		this.dni = dni;
		this.domicilio = domicilio;
		this.numCelular = numCelular;
		this.obraSocial = obraSocial;
	}
	
	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return super.getNombre();
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

	public ObraSocial getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(ObraSocial obraSocial) {
		this.obraSocial = obraSocial;
	}

}
