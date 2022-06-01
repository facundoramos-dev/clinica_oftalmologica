package ar.edu.unju.fi.poo.service;

import java.time.LocalDateTime;
import java.util.List;
import ar.edu.unju.fi.poo.dto.TurnoDTO;
import ar.edu.unju.fi.poo.util.MensajeTurno;

public interface ITurnoService {

	/**
	 * 
	 * @param tur
	 * @return
	 */
	public Boolean guardar(TurnoDTO tur);
	/**
	 * 
	 * @param fecha
	 * @return
	 */
	public String formateoDeFecha(LocalDateTime fecha);
	public String formateoDeHora(LocalDateTime fecha);
	/**
	 * 
	 * @param dni
	 * @return
	 */
	public List<MensajeTurno> listaTiempoRestanteTurnosPaciente(Long dni);
	public String consultarHorario(Long dni);
	public List<TurnoDTO> obtenerTurnos();
	/**
	 * 
	 * @param id
	 */
	public void eliminarPorId(Long id);
	/**
	 * 
	 * @param matricula
	 * @param dni
	 * @return
	 */
	public boolean retrasarTurno(Integer matricula, Long dni);
}
