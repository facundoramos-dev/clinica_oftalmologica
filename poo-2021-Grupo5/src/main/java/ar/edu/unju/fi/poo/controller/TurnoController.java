package ar.edu.unju.fi.poo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unju.fi.poo.dto.TurnoDTO;
import ar.edu.unju.fi.poo.service.IEmailService;
import ar.edu.unju.fi.poo.service.ITurnoService;
import ar.edu.unju.fi.poo.util.MensajeTurno;

@RestController
@RequestMapping("/turno")
public class TurnoController {

	@Autowired
	private ITurnoService turnoService;
	
	@Autowired
	private IEmailService s;
	
	@GetMapping("/listar")
	public List<TurnoDTO> obtener() {
		return turnoService.obtenerTurnos();
	}
	
	@GetMapping("/listar2")
	public ResponseEntity<List<TurnoDTO>> obtenerTodos() {
		return new ResponseEntity<>(turnoService.obtenerTurnos(),HttpStatus.ACCEPTED);
	}

	@PostMapping("/guardar")
	public TurnoDTO agregar(@RequestBody TurnoDTO tur) {
		turnoService.guardar(tur);
		return tur;
	}
	
	@PostMapping("/guardarMail")
	public TurnoDTO agregarMail(@RequestBody TurnoDTO tur) {
		turnoService.guardar(tur);
		s.enviarMailMedico(tur);
		s.enviarMailPaciente(tur);
		return tur;
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id") Long id) {
		turnoService.eliminarPorId(id);
	}
	
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody TurnoDTO tur) {
		turnoService.guardar(tur);
	}
	
	@GetMapping("/consultar/horario/{dni}")
	public String consultarHorario(@PathVariable("dni") Long dni) {
		return turnoService.consultarHorario(dni);
	}
	
	@GetMapping("/consultar/tiempo/{dni}")
	public List<MensajeTurno> estimarTiempo(@PathVariable("dni") Long dni) {
		return turnoService.listaTiempoRestanteTurnosPaciente(dni);
	}
	
	@GetMapping("/consultar/retrasar/{matricula}/{dni}")
	public boolean retrasarTiempo(@PathVariable("matricula") Integer matricula, @PathVariable("dni") Long dni) {
		return turnoService.retrasarTurno(matricula, dni);
	}
	
}
