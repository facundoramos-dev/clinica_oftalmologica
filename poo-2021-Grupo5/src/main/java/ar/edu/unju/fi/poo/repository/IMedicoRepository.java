package ar.edu.unju.fi.poo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.poo.entity.Medico;

@Repository
public interface IMedicoRepository extends CrudRepository<Medico, Long>{
	/**
	 * 
	 * @param matricula
	 * @return
	 */
	public Boolean existsByMatricula(Integer matricula);
	public Optional<Medico> findByMatricula(Integer matricula);
	public Medico findByMatricula(int matricula);
	/**
	 * 
	 * @param matricula
	 * @param nombre
	 * @return
	 */
	public List<Medico> findAllByMatriculaOrNombre(Integer matricula,String nombre);
	public Medico findById(long id);
	
}
