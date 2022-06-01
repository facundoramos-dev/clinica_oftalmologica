package ar.edu.unju.fi.poo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Turno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "medico_id", nullable = false)
	private Medico medico;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "paciente_id", nullable = false)
	private Paciente paciente;
	
	@Column
	private LocalDateTime fHsInicio;
	
	@Column
	private LocalDateTime fHsFinal;
	
	/**
	 * Constructor sin parametros 
	 */
	public Turno() {
		// TODO Auto-generated constructor stub
	}

	/** Este constructor recibe todos los atributos del objeto 
	 * 
	 * @param id
	 * @param medico
	 * @param paciente
	 * @param fHsInicio
	 * @param fHsFinal
	 * @param proximoTurno
	 */
	public Turno(Long id, Medico medico, Paciente paciente, LocalDateTime fHsInicio, LocalDateTime fHsFinal,
			Turno proximoTurno) {
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
	 * @param fin
	 */
	public Turno(Medico medico, Paciente paciente, LocalDate fecha, LocalTime inicio, LocalTime fin) {
		this.medico = medico;
		this.paciente = paciente;
		this.fHsInicio = LocalDateTime.of(fecha, inicio);
		this.fHsFinal = LocalDateTime.of(fecha, fin);
	}

	//Geters y Setters. Métodos de instancia

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
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
