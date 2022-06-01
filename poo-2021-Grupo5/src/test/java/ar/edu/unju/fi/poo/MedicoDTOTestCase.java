package ar.edu.unju.fi.poo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.edu.unju.fi.poo.dto.MedicoDTO;
import ar.edu.unju.fi.poo.dto.ObraSocialDTO;
import ar.edu.unju.fi.poo.service.IMedicoService;
import ar.edu.unju.fi.poo.service.IObraSocialService;

@SpringBootTest
public class MedicoDTOTestCase {

	final static Logger logger = Logger.getLogger(MedicoDTOTestCase.class);
	
	@Autowired
	private IMedicoService medicoService;
	
	@Autowired
	private IObraSocialService obraSocialService;

	@Test
	@DisplayName("Agregar Medico")
	void agregarMedicoTest(){
		Integer matricula = 1514;
		List<ObraSocialDTO> listaOS = new ArrayList<ObraSocialDTO>();
		listaOS.add(obraSocialService.encontrarPorId(1l));
		listaOS.add(obraSocialService.encontrarPorId(2l));
		if(listaOS.isEmpty())
			logger.error("Obra Social no puede estar vacia");
		else {
			MedicoDTO medico = new MedicoDTO("Nahir Cruz","nc@gmail.com",matricula,"Tarde",listaOS);
			medicoService.guardar(medico);
			assertTrue(medicoService.exitePorMatricula(matricula));
			logger.debug("Se agrego un medico con exito");
		}
	}

	@Test
	@DisplayName("Eliminar Medico")
	void eliminarMedicoTest(){
		Integer matricula = 3224;
		MedicoDTO medico = new MedicoDTO("Ayelen Calatallo","aye@gmail.com",matricula,"Mañana",obraSocialService.getObrasSociales());
		medicoService.guardar(medico);
		assertTrue(medicoService.exitePorMatricula(matricula));
		medicoService.eliminarPorMatricula(matricula);
		if(medicoService.buscarPorMatricula(matricula).isEmpty())
			logger.debug("Se elimino el medico con exito");
		else
			logger.debug("No se pudo eliminar el medico");
	}
	
	@Test
	@DisplayName("Buscar Medico por matricula o nombre")
	void buscarMedicoTest(){
		List<MedicoDTO> listaMedicosBuscados = new ArrayList<MedicoDTO>();
		Integer matricula = 1541;
		String nombre = "";
		MedicoDTO medico = new MedicoDTO("Milena Vera","mv@gmail.com",matricula,"Mañana",obraSocialService.getObrasSociales());
		medicoService.guardar(medico);
		if(nombre.isEmpty()&&matricula==0) {
			logger.error("Se debe especificar nombre o matricula");
		}else {
			listaMedicosBuscados = medicoService.buscarPorMatriculaONombre(matricula, nombre);
			logger.info("Se encontraron: "+listaMedicosBuscados.size()+" Medicos");
			if(listaMedicosBuscados.size()!=0)
				assertNotNull(listaMedicosBuscados);
		}
	}
	
}
