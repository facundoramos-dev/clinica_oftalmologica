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

import ar.edu.unju.fi.poo.dto.PacienteDTO;
import ar.edu.unju.fi.poo.service.IPacienteService;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

	@Autowired
	private IPacienteService pacienteService;
	
	@GetMapping("/listar")
	public List<PacienteDTO> obtener() {
		return pacienteService.obtenerPacientes();
	}
	
	@GetMapping("/listar2")
	public ResponseEntity<List<PacienteDTO>> obtenerTodos() {
		return new ResponseEntity<>(pacienteService.obtenerPacientes(),HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/guardar")
	public PacienteDTO agregar(@RequestBody PacienteDTO pac) {
		pacienteService.guardar(pac);
		return pac;
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id") Long id) {
		pacienteService.eliminarPorId(id);
	}
	
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody PacienteDTO pac) {
		pacienteService.guardar(pac);
	}
	
	@GetMapping("/buscar/{nombre}")
	public List<PacienteDTO> buscarPorNombre(@PathVariable("nombre") String nombre) {
		return pacienteService.buscarPorParteDelNombre(nombre);
	}
	
}
