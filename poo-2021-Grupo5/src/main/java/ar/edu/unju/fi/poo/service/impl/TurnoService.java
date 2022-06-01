package ar.edu.unju.fi.poo.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.dto.MedicoDTO;
import ar.edu.unju.fi.poo.dto.PacienteDTO;
import ar.edu.unju.fi.poo.dto.TurnoDTO;
import ar.edu.unju.fi.poo.entity.Turno;
import ar.edu.unju.fi.poo.repository.IPacienteRepository;
import ar.edu.unju.fi.poo.repository.ITurnoRepository;
import ar.edu.unju.fi.poo.service.ITurnoService;
import ar.edu.unju.fi.poo.util.MensajeTurno;

@Service
public class TurnoService implements ITurnoService {

	@Autowired
	private ITurnoRepository turnoRepository;
	
	@Autowired
	private IPacienteRepository pacienteRepository;
	
	final static Logger logger = Logger.getLogger(ITurnoService.class);
	private static final LocalTime TINI = LocalTime.of(9, 0, 0);
	private static final LocalTime TFIN = LocalTime.of(16, 0, 0);
	
	private ModelMapper mapper = new ModelMapper();
	
	@Override
	public Boolean guardar(TurnoDTO tur) {
		if(extraerFecha(tur.getfHsInicio()).equals(extraerFecha(tur.getfHsFinal()))&&tur.getfHsInicio().isAfter(LocalDateTime.now().minusNanos(1)))
			if(extraerHora(tur.getfHsInicio()).isAfter(TINI.minusNanos(1))&&extraerHora(tur.getfHsFinal()).isBefore(TFIN.plusNanos(1))) {
				Turno turno = new Turno();
				mapper.map(tur,turno);
				if(verificarTurno(turno)) {
					turnoRepository.save(turno);
					logger.info("Turno Asignado");
					return true;
				}
			}
		logger.info("Imposible asignar turno...");
		return false;
	}
	
	private LocalTime extraerHora(LocalDateTime hora) {
		return LocalTime.of(hora.getHour(),hora.getMinute(),hora.getSecond(),hora.getNano());
	}
	
	private LocalDate extraerFecha(LocalDateTime fecha) {
		return LocalDate.of(fecha.getYear(),fecha.getMonthValue(),fecha.getDayOfMonth());
	}
	
	private Boolean verificarTurno(Turno turno) {
		for(Turno t : (List<Turno>) turnoRepository.findAll()) {
			if(turno.getMedico().getId()==t.getMedico().getId()) {
				if(turno.getfHsInicio().isBefore(t.getfHsFinal())&&turno.getfHsFinal().isAfter(t.getfHsInicio().minusNanos(1))) {
					logger.info("Medico Ocupado");
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public String formateoDeFecha(LocalDateTime turno) {
		String mes="";
		switch (turno.getMonthValue()) {
		case 1:mes = "Enero";break;
		case 2:mes = "Febrero";break;
		case 3:mes = "Marzo";break;
		case 4:mes = "Abril";break;
		case 5:mes = "Mayo";break;
		case 6:mes = "Junio";break;
		case 7:mes = "Julio";break;
		case 8:mes = "Agosto";break;
		case 9:mes = "Septiembre";break;
		case 10:mes = "Octubre";break;
		case 11:mes = "Noviembre";break;
		case 12:mes = "Diciembre";break;
		}
		return turno.getDayOfMonth()+" de "+mes;
	}

	@Override
	public String formateoDeHora(LocalDateTime turno) {
		String hora = turno.getHour()+":";
		if(turno.getMinute()<10)
			hora=hora+"0";
		hora=hora+turno.getMinute()+"Hs.";
		return hora;
	}

	@Override
	public List<MensajeTurno> listaTiempoRestanteTurnosPaciente(Long dni) {
		LocalDateTime fHsActual = LocalDateTime.now();
		List<MensajeTurno> mensajeTurnos = new ArrayList<>();
		List<Turno> listaTurnosPaciente = turnoRepository.findByPacienteDni(dni);
		if (listaTurnosPaciente.isEmpty()) {
			return mensajeTurnos;
		} else {
			for (Turno turno : listaTurnosPaciente) {
				LocalDateTime tiempoRestante = LocalDateTime.from(fHsActual);
				if (fHsActual.isBefore(turno.getfHsInicio())) {
					MensajeTurno mensaje = new MensajeTurno();
					long dias = tiempoRestante.until(turno.getfHsInicio(), ChronoUnit.DAYS);
					tiempoRestante = tiempoRestante.plusDays(dias);
					long horas = tiempoRestante.until(turno.getfHsInicio(), ChronoUnit.HOURS);
					tiempoRestante = tiempoRestante.plusHours(horas);
					long minutos = tiempoRestante.until(turno.getfHsInicio(), ChronoUnit.MINUTES);
					tiempoRestante = tiempoRestante.plusMinutes(minutos);
					mensaje.setNombreMedico(turno.getMedico().getNombre());
					mensaje.setNombrePaciente(turno.getPaciente().getNombre());
					mensaje.setfHsInicioTurno(turno.getfHsInicio().toString());
					mensaje.setfHsFinalTurno(turno.getfHsFinal().toString());
					mensaje.setTiempoRestante(dias+" dias, "+horas+" horas, "+minutos+" minutos");
					mensajeTurnos.add(mensaje);
				}
			}
		}
		return mensajeTurnos;
	}
	
	@Override
	public String consultarHorario(Long dni) {
		if(turnoRepository.findByPacienteDni(dni).size()==0) {
			return "Usted no posee un turno registrado";
		}
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<h2>Turnos del paciente: "+pacienteRepository.findByDni((long)dni).getNombre()+"</h2>");
		for(Turno t : turnoRepository.findByPacienteDni(dni)) {
			stringBuffer.append("<h3>-Medico: "+t.getMedico().getNombre()+" | Horario: "+
					extraerHora(t.getfHsInicio())+" - "+extraerHora(t.getfHsFinal())+"</h3>");
		}
		return stringBuffer.toString();
	}

	@Override
	public List<TurnoDTO> obtenerTurnos() {
		List<TurnoDTO> turs = new ArrayList<TurnoDTO>();
		MedicoDTO med;
		PacienteDTO pac;
		TurnoDTO tur;
		for(Turno t : turnoRepository.findAll()) {
			tur= new TurnoDTO();
			pac = new PacienteDTO();
			med = new MedicoDTO();
			mapper.map(t,tur);
			mapper.map(t.getMedico(),med);
			mapper.map(t.getPaciente(),pac);
			tur.setMedico(med);
			tur.setPaciente(pac);
			turs.add(tur);
			tur=null;
			pac=null;
			med=null;
		}
		return turs;
	}

	@Override
	public void eliminarPorId(Long id) {
		turnoRepository.deleteById(id);
	}

	@Override
	public boolean retrasarTurno(Integer matricula, Long dni) {
		boolean seRetraso = false;
		List<Turno> listaTurnosMedico = turnoRepository.findByMedicoMatricula(matricula);
		Turno turnoPaciente = turnoRepository.findByMedicoMatriculaAndPacienteDni(matricula, dni);
		if (turnoPaciente!=null) {
			for (Turno turno : listaTurnosMedico) {
				if (turno.getfHsInicio().equals(turnoPaciente.getfHsInicio())||turno.getfHsInicio().isBefore(turnoPaciente.getfHsInicio())) {
					turno.setfHsInicio(turno.getfHsInicio().plusMinutes(30));
					turno.setfHsFinal(turno.getfHsFinal().plusMinutes(30));
					turnoRepository.save(turno);
					logger.info("Se retraso un turno: "+ turno.getfHsInicio().toString());
				}
			}
			seRetraso = true;
		} else {
			logger.error("no se encontro el turno del paciente");
		}
		return seRetraso;
	}
}
