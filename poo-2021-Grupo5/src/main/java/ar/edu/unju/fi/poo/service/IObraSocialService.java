package ar.edu.unju.fi.poo.service;

import java.util.List;

import ar.edu.unju.fi.poo.dto.ObraSocialDTO;

public interface IObraSocialService {
	/**
	 * 
	 * @param id
	 * @return
	 */
	public ObraSocialDTO encontrarPorId(long id);
	//Obtener todas las Obras sociales
	/**
	 * 
	 * @return
	 */
	public List<ObraSocialDTO> getObrasSociales();
	
}
