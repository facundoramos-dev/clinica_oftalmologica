package ar.edu.unju.fi.poo.service;

import ar.edu.unju.fi.poo.dto.TurnoDTO;

public interface IEmailService {

	/**
	 * 
	 * @param tur
	 * @return
	 */
	//Obtener los mail enviados de Paciente y/o medico
	public boolean enviarMailPaciente(TurnoDTO tur);
	public boolean enviarMailMedico(TurnoDTO tur);
	
}
