package ar.edu.unju.fi.poo.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

public class MedicoDTO extends PersonaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer matricula;
	
	private String turnoLaboral;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<ObraSocialDTO> obrasSociales;
	
	/**
	 * Constructor sin parametros 
	 */
	public MedicoDTO() {
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
	public MedicoDTO(Long id, String nombre, String email, Integer matricula, String turnoLaboral, List<ObraSocialDTO> obrasSociales) {
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
	public MedicoDTO(String nombre, String email, Integer matricula, String turnoLaboral, List<ObraSocialDTO> obrasSociales) {
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

	public List<ObraSocialDTO> getObrasSociales() {
		return obrasSociales;
	}

	public void setObrasSociales(List<ObraSocialDTO> obrasSociales) {
		this.obrasSociales = obrasSociales;
	}

}
