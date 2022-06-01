package ar.edu.unju.fi.poo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.dto.PacienteDTO;
import ar.edu.unju.fi.poo.entity.Paciente;
import ar.edu.unju.fi.poo.repository.IPacienteRepository;
import ar.edu.unju.fi.poo.service.IPacienteService;

@Service
public class PacienteService implements IPacienteService {

	final static Logger logger = Logger.getLogger(PacienteService.class);
	@Autowired
	private IPacienteRepository pacienteRepository;
	
	private ModelMapper mapper = new ModelMapper();
	
	@Override
	public void guardar(PacienteDTO pac) {
		Paciente paciente = new Paciente();
		mapper.map(pac,paciente);
		pacienteRepository.save(paciente);
	}
	
	@Override
	public Boolean existePorDni(Long dni) {
		return pacienteRepository.existsByDni(dni);
	}

	@Override
	public void eliminarPorDni(Long dni) {
		Paciente paciente = new Paciente();
		mapper.map(encontrarPorDni(dni),paciente);
		pacienteRepository.delete(paciente);
	}

	@Override
	public PacienteDTO encontrarPorDni(long dni) {
		PacienteDTO pac = new PacienteDTO();
		mapper.map(pacienteRepository.findByDni(dni), pac);
		return pac;
	}

	@Override
	public Optional<PacienteDTO> buscarPorDni(Long dni) {
		Optional<PacienteDTO> paci = Optional.empty();
		mapper.map(pacienteRepository.findByDni(dni), paci);
		return paci;
	}

	@Override
	public List<PacienteDTO> buscarPorParteDelNombre(String parte) {
		List<PacienteDTO> paci = new ArrayList<PacienteDTO>();
		PacienteDTO pac;
		for(Paciente p : pacienteRepository.findByNombre(parte)) {
			pac = new PacienteDTO();
			mapper.map(p, pac);
			paci.add(pac);
			pac = null;
		}
		return paci;
	}

	@Override
	public PacienteDTO encontrarPorId(long id) {
		PacienteDTO pac = new PacienteDTO();
		mapper.map(pacienteRepository.findById(id), pac);
		return pac;
	}

	@Override
	public List<PacienteDTO> obtenerPacientes() {
		List<PacienteDTO> pacs = new ArrayList<PacienteDTO>();
		PacienteDTO pac;
		for(Paciente p : pacienteRepository.findAll()) {
			pac = new PacienteDTO();
			mapper.map(p,pac);
			pacs.add(pac);
			pac=null;
		}
		return pacs;
	}
	
	@Override
	public void eliminarPorId(Long id) {
		pacienteRepository.deleteById(id);
	}
	
}
