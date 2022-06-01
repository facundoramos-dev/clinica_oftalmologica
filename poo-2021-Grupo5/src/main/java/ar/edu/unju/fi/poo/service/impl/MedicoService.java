package ar.edu.unju.fi.poo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.dto.MedicoDTO;
import ar.edu.unju.fi.poo.entity.Medico;
import ar.edu.unju.fi.poo.repository.IMedicoRepository;
import ar.edu.unju.fi.poo.service.IMedicoService;

@Service
public class MedicoService implements IMedicoService {

	@Autowired
	private IMedicoRepository medicoRepository;
	
	private ModelMapper mapper = new ModelMapper();

	@Override
	public void guardar(MedicoDTO med) {
		Medico medico = new Medico();
		mapper.map(med,medico);
		medicoRepository.save(medico);
	}
	
	@Override
	public Boolean exitePorMatricula(Integer matricula) {
		return medicoRepository.existsByMatricula(matricula);
	}
	
	@Override
	public void eliminarPorMatricula(Integer matricula) {
		Medico medico = new Medico();
		mapper.map(encontrarPorMatricula(matricula), medico);
		medicoRepository.delete(medico);
	}
	
	@Override
	public MedicoDTO encontrarPorMatricula(int matricula) {
		MedicoDTO med = new MedicoDTO();
		mapper.map(medicoRepository.findByMatricula(matricula), med);
		return med;
	}
	
	@Override
	public Optional<MedicoDTO> buscarPorMatricula(Integer matricula) {
		Optional<MedicoDTO> med = Optional.empty();
		mapper.map(medicoRepository.findByMatricula(matricula), med);
		return med;
	}
	
	@Override
	public List<MedicoDTO> buscarPorMatriculaONombre(Integer matricula, String nombre) {
		List<MedicoDTO> lista = new ArrayList<MedicoDTO>();
		MedicoDTO med;
		for (Medico m : medicoRepository.findAllByMatriculaOrNombre(matricula,nombre)) {
			med = new MedicoDTO();
			mapper.map(m,med);
			lista.add(med);
			med=null;
		}
		return lista;
	}

	@Override
	public MedicoDTO encontrarPorId(long id) {
		MedicoDTO med = new MedicoDTO();
		mapper.map(medicoRepository.findById(id), med);
		return med;
	}

	@Override
	public List<MedicoDTO> obtenerMedicos() {
		List<MedicoDTO> meds = new ArrayList<MedicoDTO>();
		MedicoDTO med;
		for(Medico m : medicoRepository.findAll()) {
			med = new MedicoDTO();
			mapper.map(m,med);
			meds.add(med);
			med = null;
		}
		return meds;
	}

	@Override
	public void eliminarPorId(Long id) {
		medicoRepository.deleteById(id);
	}
	
}
