package ar.edu.unju.fi.poo.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

public class TurnoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private MedicoDTO medico;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private PacienteDTO paciente;
	
	private LocalDateTime fHsInicio;
	
	private LocalDateTime fHsFinal;
	
	public TurnoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	/** Este constructor recibe todos los atributos del objeto
	 * 
	 * @param id
	 * @param medico
	 * @param paciente
	 * @param fHsInicio
	 * @param fHsFinal
	 */
	public TurnoDTO(Long id, MedicoDTO medico, PacienteDTO paciente, LocalDateTime fHsInicio, LocalDateTime fHsFinal) {
		super();
		this.id = id;
		this.medico = medico;
		this.paciente = paciente;
		this.fHsInicio = fHsInicio;
		this.fHsFinal = fHsFinal;
	}

 	/** Este constructor recibe los atributos del objeto y el ID lo genera automaticamente
	 * 
	 * @param medico
	 * @param paciente
	 * @param fecha
	 * @param inicio
	 */
	public TurnoDTO(MedicoDTO medico, PacienteDTO paciente, LocalDate fecha, LocalTime inicio) {
		this.medico = medico;
		this.paciente = paciente;
		this.fHsInicio = LocalDateTime.of(fecha, inicio);
		this.fHsFinal = fHsInicio.plusMinutes(30);
	}

	//Geters y Setters. Métodos de instancia

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MedicoDTO getMedico() {
		return medico;
	}

	public void setMedico(MedicoDTO medico) {
		this.medico = medico;
	}

	public PacienteDTO getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteDTO paciente) {
		this.paciente = paciente;
	}

	public LocalDateTime getfHsInicio() {
		return fHsInicio;
	}

	public void setfHsInicio(LocalDateTime fHsInicio) {
		this.fHsInicio = fHsInicio;
	}

	public LocalDateTime getfHsFinal() {
		return fHsFinal;
	}

	public void setfHsFinal(LocalDateTime fHsFinal) {
		this.fHsFinal = fHsFinal;
	}

}
