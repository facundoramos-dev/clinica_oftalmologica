package ar.edu.unju.fi.poo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.dto.ObraSocialDTO;
import ar.edu.unju.fi.poo.entity.ObraSocial;
import ar.edu.unju.fi.poo.repository.IObraSocialRepository;
import ar.edu.unju.fi.poo.service.IObraSocialService;

@Service
public class ObraSocialService implements IObraSocialService {

	@Autowired
	IObraSocialRepository obraSocialRepository;
	
	private ModelMapper mapper = new ModelMapper();
	
	@Override
	public ObraSocialDTO encontrarPorId(long id) {
		ObraSocial ob = obraSocialRepository.findById(id);
		ObraSocialDTO obraSO = new ObraSocialDTO();
		mapper.map(ob, obraSO);
		return obraSO;
	}

	@Override
	public List<ObraSocialDTO> getObrasSociales() {
		//Obtener listado de las Obras Sociales
		List<ObraSocialDTO> obraSO = new ArrayList<ObraSocialDTO>();
		ObraSocialDTO obraDTO;
		for(ObraSocial o: obraSocialRepository.findAll()) {
			obraDTO = new ObraSocialDTO();
			mapper.map(o, obraDTO);
			obraSO.add(obraDTO);
			obraDTO = null;
		}
		return obraSO;
	}

}
