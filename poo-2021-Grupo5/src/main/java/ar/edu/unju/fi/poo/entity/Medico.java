package ar.edu.unju.fi.poo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Medico extends Persona{

	@Column
	private Integer matricula;
	@Column
	private String turnoLaboral;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "medico_obra_sociales",
	joinColumns = @JoinColumn(name = "medico_id"),
	inverseJoinColumns = @JoinColumn(name = "obra_social_id"))
	private List<ObraSocial> obrasSociales;
	
	/**
	 * Constructor sin parametros 
	 */
	public Medico() {
		// TODO Auto-generated constructor stub
	}

	/** Este constructor recibe todos los atributos del objeto
	 * 
	 * @param id
	 * @param nombre
	 * @param email
	 * @param matricula
	 * @param turnoLaboral
	 * @param obrasSociales
	 */
	public Medico(Long id, String nombre, String email, Integer matricula, String turnoLaboral, List<ObraSocial> obrasSociales) {
		super(id,nombre,email);
		this.matricula = matricula;
		this.turnoLaboral = turnoLaboral;
		this.obrasSociales = obrasSociales;
	}
	
	/** Este constructor recibe los atributos del objeto y el ID lo genera automaticamente
	 * 
	 * @param nombre
	 * @param email
	 * @param matricula
	 * @param turnoLaboral
	 * @param obrasSociales
	 */
	public Medico(String nombre, String email, Integer matricula, String turnoLaboral, List<ObraSocial> obrasSociales) {
		super(nombre,email);
		this.matricula = matricula;
		this.turnoLaboral = turnoLaboral;
		this.obrasSociales = obrasSociales;
	}

	//Geters y Setters. Métodos de instancia

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getTurnoLaboral() {
		return turnoLaboral;
	}

	public void setTurnoLaboral(String turnoLaboral) {
		this.turnoLaboral = turnoLaboral;
	}

	public List<ObraSocial> getObrasSociales() {
		return obrasSociales;
	}

	public void setObrasSociales(List<ObraSocial> obrasSociales) {
		this.obrasSociales = obrasSociales;
	}
	
}
