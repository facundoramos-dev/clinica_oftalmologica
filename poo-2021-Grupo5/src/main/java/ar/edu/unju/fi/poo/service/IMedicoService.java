package ar.edu.unju.fi.poo.service;

import java.util.List;
import java.util.Optional;

import ar.edu.unju.fi.poo.dto.MedicoDTO;

public interface IMedicoService {

	/**
	 * 
	 * @param med
	 */
	public void guardar(MedicoDTO med);
	/**
	 * 
	 * @param matricula
	 * @return
	 */
	public Boolean exitePorMatricula(Integer matricula);
	public void eliminarPorMatricula(Integer matricula);
	public Optional<MedicoDTO> buscarPorMatricula(Integer matricula);
	public MedicoDTO encontrarPorMatricula(int matricula);
	/**
	 * 
	 * @param matricula
	 * @param nombre
	 * @return
	 */
	public List<MedicoDTO> buscarPorMatriculaONombre(Integer matricula, String nombre);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public MedicoDTO encontrarPorId(long id);
	public List<MedicoDTO> obtenerMedicos();
	public void eliminarPorId(Long id);

}
