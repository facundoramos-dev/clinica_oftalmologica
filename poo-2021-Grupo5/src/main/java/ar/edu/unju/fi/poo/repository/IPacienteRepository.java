package ar.edu.unju.fi.poo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.poo.entity.Paciente;

@Repository
public interface IPacienteRepository extends CrudRepository<Paciente, Long> {
	
	/**
	 * 
	 * @param dni
	 * @return
	 */
	public Boolean existsByDni(Long dni);
	public Optional<Paciente> findByDni(Long dni);
	public Paciente findByDni(long dni);
	/**
	 * 
	 * @param nombre
	 * @return
	 */
	@Query("select p from Paciente p where p.nombre like %?1%")
	public List<Paciente> findByNombre(String nombre);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Paciente findById(long id);
	
}
