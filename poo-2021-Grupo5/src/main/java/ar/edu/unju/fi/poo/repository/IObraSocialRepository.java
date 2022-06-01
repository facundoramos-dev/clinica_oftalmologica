package ar.edu.unju.fi.poo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.poo.entity.ObraSocial;

@Repository
public interface IObraSocialRepository extends CrudRepository<ObraSocial, Long>{
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public ObraSocial findById(long id);
	public List<ObraSocial> findAll();
	
}
