package ar.edu.unju.fi.poo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.edu.unju.fi.poo.dto.MedicoDTO;
import ar.edu.unju.fi.poo.dto.ObraSocialDTO;
import ar.edu.unju.fi.poo.dto.PacienteDTO;
import ar.edu.unju.fi.poo.dto.TurnoDTO;
import ar.edu.unju.fi.poo.service.IEmailService;
import ar.edu.unju.fi.poo.service.IMedicoService;
import ar.edu.unju.fi.poo.service.IObraSocialService;
import ar.edu.unju.fi.poo.service.IPacienteService;
import ar.edu.unju.fi.poo.service.ITurnoService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TurnoDTOTestCase {

	final static Logger logger = Logger.getLogger(TurnoDTOTestCase.class);

	@Autowired
	private IMedicoService medicoService;

	@Autowired
	private IPacienteService pacienteService;

	@Autowired
	private IObraSocialService obraSocialService;

	@Autowired
	private ITurnoService turnoService;
	
	@Autowired
	private IEmailService emailService;
	
	private Integer matricula1 = 85799;
	private Integer matricula2 = 85129;
	private Integer matricula3 = 51254;
	private Long dni1 = 42035291l;
	private Long dni2 = 41081221l;
	
	@BeforeAll
	void cargarMedicos() {
		List<ObraSocialDTO> listaOS1 = new ArrayList<ObraSocialDTO>();
		List<ObraSocialDTO> listaOS2 = new ArrayList<ObraSocialDTO>();
		List<ObraSocialDTO> listaOS3 = new ArrayList<ObraSocialDTO>();
		listaOS1.add(obraSocialService.encontrarPorId(1l));
		listaOS2.add(obraSocialService.encontrarPorId(2l));
		listaOS3.add(obraSocialService.encontrarPorId(3l));
		//Medico
		MedicoDTO medico1 = new MedicoDTO("Facundo Sebastian Ramos","faq.ramos.14101999@gmail.com",matricula1,"Tarde",listaOS1);
		MedicoDTO medico2 = new MedicoDTO("Agustina Miranda Torrez","amt@gmail.com",matricula2,"Mañana",listaOS2);
		MedicoDTO medico3 = new MedicoDTO("Sofia Estefania LLanes","sel@hotmail.com",matricula3,"Mañana",listaOS3);
		medicoService.guardar(medico1);
		medicoService.guardar(medico2);
		medicoService.guardar(medico3);
		//Paciente
		ObraSocialDTO obraSocial1 = obraSocialService.encontrarPorId(1l);
		ObraSocialDTO obraSocial2 = obraSocialService.encontrarPorId(2l);
		PacienteDTO paciente1 = new PacienteDTO("Geovana Guadalupe Gonzalez Lopez","geovanagonzalez480@gmail.com",dni1,"Vivaro, al nose",
				3884214073l,obraSocial1);
		PacienteDTO paciente2 = new PacienteDTO("Sonia Esther Fuentes","sef@outlook.com",dni2,"Avenida 19 de Abril 1750",
				3884511234l,obraSocial2);
		pacienteService.guardar(paciente1);
		pacienteService.guardar(paciente2);
	}
	
	@AfterAll
	void eliminar() {
		matricula1 = null;
		matricula2 = null;
		dni1 = null;
		dni2 = null;
	}
	
	@Test
	@DisplayName("Agregar Turno")
	void agregarTurnoTest() {
		LocalDate fecha = LocalDate.now().plusDays(2);
		LocalTime hInicio = LocalTime.of(15,30);
		TurnoDTO turno = new TurnoDTO(medicoService.encontrarPorMatricula(matricula1),pacienteService.encontrarPorDni(dni1),fecha,hInicio);
		assertTrue(turnoService.guardar(turno));
		assertTrue(emailService.enviarMailPaciente(turno));
		assertTrue(emailService.enviarMailMedico(turno));
	}
	
	@Test
	@DisplayName("Agregar Turno Fuera de Hora")
	void agregarTurnoFueraDeHoraTest() {
		LocalDate fecha1 = LocalDate.now().minusDays(1);
		LocalDate fecha2 = LocalDate.now().plusDays(1);
		LocalTime hInicio1 = LocalTime.of(10,0);
		LocalTime hInicio2 = LocalTime.of(8,59);
		LocalTime hInicio3 = LocalTime.of(16,1);
		TurnoDTO turno1 = new TurnoDTO(medicoService.encontrarPorMatricula(matricula1),pacienteService.encontrarPorDni(dni2),fecha1,hInicio1);
		TurnoDTO turno2 = new TurnoDTO(medicoService.encontrarPorMatricula(matricula1),pacienteService.encontrarPorDni(dni2),fecha2,hInicio2);
		TurnoDTO turno3 = new TurnoDTO(medicoService.encontrarPorMatricula(matricula1),pacienteService.encontrarPorDni(dni2),fecha2,hInicio3);
		assertFalse(turnoService.guardar(turno1));
		assertFalse(turnoService.guardar(turno2));
		assertFalse(turnoService.guardar(turno3));
	}
	
	@Test
	@DisplayName("Superponer Turnos")
	void superponerTurnoTest() {
		LocalDate fecha = LocalDate.now().plusDays(2);
		LocalTime hInicio1 = LocalTime.of(10,2);
		LocalTime hInicio2 = hInicio1.plusMinutes(19);
		TurnoDTO turno1 = new TurnoDTO(medicoService.encontrarPorMatricula(matricula3),pacienteService.encontrarPorDni(dni2),fecha,hInicio1);
		TurnoDTO turno2 = new TurnoDTO(medicoService.encontrarPorMatricula(matricula3),pacienteService.encontrarPorDni(dni1),fecha,hInicio2);
		assertTrue(turnoService.guardar(turno1));
		assertFalse(turnoService.guardar(turno2));
	}
	
	@Test
	@DisplayName("Calcular el tiempo restantes para un turno")
	void calcularTiempoRestanteTurnoTest() {
		LocalDate fecha = LocalDate.now().plusDays(1);
		LocalTime hInicio1 = LocalTime.of(15,0);
		LocalTime hInicio2 = LocalTime.of(14,0);
		TurnoDTO turno1 = new TurnoDTO(medicoService.encontrarPorMatricula(matricula3),pacienteService.encontrarPorDni(dni1),fecha,hInicio1);
		TurnoDTO turno2 = new TurnoDTO(medicoService.encontrarPorMatricula(matricula2),pacienteService.encontrarPorDni(dni1),fecha,hInicio2);
		turnoService.guardar(turno1);
		turnoService.guardar(turno2);
		logger.info("Cantidad de Turnos encontrados con el numero de documento "+dni1+" de un Paciente: "+ turnoService.listaTiempoRestanteTurnosPaciente(dni1).size());
		assertFalse(turnoService.listaTiempoRestanteTurnosPaciente(dni1).isEmpty());
	}
	
	@Test
	@DisplayName("Retrasar un turno")
	void retrasarTurnoTest() {
		LocalDate fecha1 = LocalDate.now().plusDays(2);
		LocalDate fecha2 = LocalDate.now().plusDays(3);
		LocalTime hInicio1 = LocalTime.of(12,0);
		LocalTime hInicio2 = LocalTime.of(11,0);
		TurnoDTO turno1 = new TurnoDTO(medicoService.encontrarPorMatricula(matricula2),pacienteService.encontrarPorDni(dni1),fecha1,hInicio1);
		TurnoDTO turno2 = new TurnoDTO(medicoService.encontrarPorMatricula(matricula2),pacienteService.encontrarPorDni(dni2),fecha2,hInicio2);
		turnoService.guardar(turno1);
		turnoService.guardar(turno2);
		assertTrue(turnoService.retrasarTurno(matricula2, dni2));
	}
}
