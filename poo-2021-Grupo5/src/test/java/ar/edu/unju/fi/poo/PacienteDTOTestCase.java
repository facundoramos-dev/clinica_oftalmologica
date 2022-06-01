package ar.edu.unju.fi.poo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.edu.unju.fi.poo.dto.ObraSocialDTO;
import ar.edu.unju.fi.poo.dto.PacienteDTO;
import ar.edu.unju.fi.poo.service.IObraSocialService;
import ar.edu.unju.fi.poo.service.IPacienteService;

@SpringBootTest
class PacienteDTOTestCase {

	final static Logger logger = Logger.getLogger(PacienteDTOTestCase.class);

	@Autowired
	private IPacienteService pacienteService;

	@Autowired
	private IObraSocialService obraSocialService;

	@Test
	@DisplayName("Agregar Paciente")
	void agregarPacienteTest() {
		Long dni = 42823167l;
		ObraSocialDTO obraSocial = obraSocialService.encontrarPorId(1l);
		if (obraSocial != null) {
			PacienteDTO paciente = new PacienteDTO("Camila Reyes", "cami90@gmail.com", dni, 	
					"Av. Bolivia 3455, Jujuy", 388207866l, obraSocial);
			pacienteService.guardar(paciente);
			assertTrue(pacienteService.existePorDni(dni),"si existe DNI");
			logger.debug("Se agrego un paciente con exito");
		}else
			logger.error("La ObraSocial es obligatoria");
	}
	
	@Test
	@DisplayName("Eliminar Paciente")
	void eliminarPacienteTest() {
		Long dni = 42035525l;
		PacienteDTO paciente = new PacienteDTO("Noelia del Pino", "noe@outlook.com", dni,
				"Av Vispero 4112, Jujuy", 3881455110l, obraSocialService.encontrarPorId(2l));
		pacienteService.guardar(paciente);
		assertTrue(pacienteService.existePorDni(dni));
		pacienteService.eliminarPorDni(dni);
		if (pacienteService.buscarPorDni(dni).isEmpty()) {
			logger.debug("Se elimino de la base de datos al paciente");
		} else {
			logger.error("No se pudo eliminar el paciente");
		}
	}
	
	@Test
	@DisplayName("Modificar Paciente")
	void actualizarPacienteTest() {
		Long dni = 43841120l;
		PacienteDTO paciente = new PacienteDTO("Geobana Gonsales","gg@hotmail.com",dni,
				"Av Viraro 1500, Jujuy", 3884200000l, obraSocialService.encontrarPorId(1l));
		pacienteService.guardar(paciente);
		assertTrue(pacienteService.existePorDni(dni));
		PacienteDTO datosViejos = pacienteService.encontrarPorDni(dni);
		PacienteDTO modificar = datosViejos;
		modificar.setNombre("Geovana Guadalupe Gonzalez");
		modificar.setObraSocial(obraSocialService.encontrarPorId(3l));
		pacienteService.guardar(modificar);
		assertNotEquals(datosViejos, pacienteService.encontrarPorDni(dni));
		logger.debug("Se actualizó un paciente con exito");
	}

	@Test
	@DisplayName("Buscar Paciente")
	void buscarPacienteTest() {
		PacienteDTO paciente = new PacienteDTO("Flor Garcia","florcita@gmail.com",41021158l,
				"Av Flor del Pago 1100, Jujuy", 3884246210l, obraSocialService.encontrarPorId(2l));
		pacienteService.guardar(paciente);
		String cadena = "Fl";
		if (cadena.isEmpty())
			logger.error("La cadena a buscar se encuentra vacía");
		else {
			List<PacienteDTO> listaPacientesBuscados = pacienteService.buscarPorParteDelNombre(cadena);
			logger.info("Se realizó la busqueda con un resultado de " + listaPacientesBuscados.size() + " de objetos");
			assertFalse(listaPacientesBuscados.size() == 0);
		}
	}

}
