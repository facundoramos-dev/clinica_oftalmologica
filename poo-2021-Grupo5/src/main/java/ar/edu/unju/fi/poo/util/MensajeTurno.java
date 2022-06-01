package ar.edu.unju.fi.poo.util;

public class MensajeTurno {
	private String nombreMedico;
	private String nombrePaciente;
	private String fHsInicioTurno;
	private String fHsFinalTurno;
	private String tiempoRestante;
	
	public MensajeTurno() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param nombreMedico
	 * @param nombrePaciente
	 * @param fHsInicioTurno
	 * @param fHsFinalTurno
	 * @param tiempoRestante
	 */
	public MensajeTurno(String nombreMedico, String nombrePaciente, String fHsInicioTurno, String fHsFinalTurno,
			String tiempoRestante) {
		super();
		this.nombreMedico = nombreMedico;
		this.nombrePaciente = nombrePaciente;
		this.fHsInicioTurno = fHsInicioTurno;
		this.fHsFinalTurno = fHsFinalTurno;
		this.tiempoRestante = tiempoRestante;
	}

	public String getNombreMedico() {
		return nombreMedico;
	}

	public void setNombreMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
	}

	public String getNombrePaciente() {
		return nombrePaciente;
	}

	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}

	public String getfHsInicioTurno() {
		return fHsInicioTurno;
	}

	public void setfHsInicioTurno(String fHsInicioTurno) {
		this.fHsInicioTurno = fHsInicioTurno;
	}

	public String getfHsFinalTurno() {
		return fHsFinalTurno;
	}

	public void setfHsFinalTurno(String fHsFinalTurno) {
		this.fHsFinalTurno = fHsFinalTurno;
	}

	public String getTiempoRestante() {
		return tiempoRestante;
	}

	public void setTiempoRestante(String tiempoRestante) {
		this.tiempoRestante = tiempoRestante;
	}

	@Override
	public String toString() {
		return "MensajeTurno [nombreMedico=" + nombreMedico + ", nombrePaciente=" + nombrePaciente + ", fHsInicioTurno="
				+ fHsInicioTurno + ", fHsFinalTurno=" + fHsFinalTurno + ", tiempoRestante=" + tiempoRestante + "]";
	}
}
