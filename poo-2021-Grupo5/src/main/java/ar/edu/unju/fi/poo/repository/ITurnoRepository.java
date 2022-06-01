package ar.edu.unju.fi.poo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.poo.entity.Turno;

public interface ITurnoRepository extends CrudRepository<Turno, Long>{
	
	/**
	 * 
	 * @param matricula
	 * @return
	 */
	public List<Turno> findByMedicoMatricula(Integer matricula);
	public List<Turno> findByPacienteDni(Long dni);
	/**
	 * 
	 * @param matricula
	 * @param dni
	 * @return
	 */
	public Turno findByMedicoMatriculaAndPacienteDni(Integer matricula, Long dni);
}
