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

import ar.edu.unju.fi.poo.dto.MedicoDTO;
import ar.edu.unju.fi.poo.service.IMedicoService;

@RestController
@RequestMapping("/medico")
public class MedicoController {

	@Autowired
	private IMedicoService medicoService;
	
	@GetMapping("/listar")
	public List<MedicoDTO> obtener() {
		return medicoService.obtenerMedicos();
	}
	
	@GetMapping("/listar2")
	public ResponseEntity<List<MedicoDTO>> obtenerTodos() {
		return new ResponseEntity<>(medicoService.obtenerMedicos(),HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/guardar")
	public MedicoDTO agregar(@RequestBody MedicoDTO med) {
		medicoService.guardar(med);
		return med;
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id") Long id) {
		medicoService.eliminarPorId(id);
	}
	
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody MedicoDTO med) {
		medicoService.guardar(med);
	}
	
	@GetMapping("/buscar/{matricula}&{nombre}")
	public List<MedicoDTO> buscarNombreOMatricula(@PathVariable("matricula") Integer matricula,@PathVariable("nombre") String nombre) {
		return medicoService.buscarPorMatriculaONombre(matricula, nombre);
	}
	
}
