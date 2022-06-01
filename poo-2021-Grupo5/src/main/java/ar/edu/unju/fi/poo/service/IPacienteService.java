package ar.edu.unju.fi.poo.service;

import java.util.List;
import java.util.Optional;

import ar.edu.unju.fi.poo.dto.PacienteDTO;

public interface IPacienteService {

	/**
	 * 
	 * @param pac
	 */
	public void guardar(PacienteDTO pac);
	/**
	 * 
	 * @param dni
	 * @return
	 */
	public Boolean existePorDni(Long dni);
	public void eliminarPorDni(Long dni);
	public Optional<PacienteDTO> buscarPorDni(Long dni);
	public PacienteDTO encontrarPorDni(long dni);
	/**
	 * 
	 * @param parte
	 * @return
	 */
	public List<PacienteDTO> buscarPorParteDelNombre(String parte);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public PacienteDTO encontrarPorId(long id);
	public List<PacienteDTO> obtenerPacientes();
	public void eliminarPorId(Long id);
	
}
