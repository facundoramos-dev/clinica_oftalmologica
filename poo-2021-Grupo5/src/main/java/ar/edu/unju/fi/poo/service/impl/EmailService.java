package ar.edu.unju.fi.poo.service.impl;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.dto.TurnoDTO;
import ar.edu.unju.fi.poo.service.IEmailService;
import ar.edu.unju.fi.poo.service.ITurnoService;
import ar.edu.unju.fi.poo.util.EmailEnvio;

@Service
public class EmailService implements IEmailService{
	
	@Autowired
	private ITurnoService turnoService;
	
	@Autowired
	private EmailEnvio emailUtil;

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public boolean enviarMailPaciente(TurnoDTO tur)  {
		return emailUtil.emailEnviado(generarMailPaciente(tur),tur.getPaciente().getEmail(),"Turno Confirmado - Clinica Poo");
	}
	
	@Override
	public boolean enviarMailMedico(TurnoDTO tur)  {
		return emailUtil.emailEnviado(generarMailMedico(tur),tur.getMedico().getEmail(),"Turno Asignado - Clinica Poo");
	}
	
	//Obtener mail para Paciente
	private String generarMailPaciente(TurnoDTO tur) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<h3>Saludos cordiales Sr/a: "+tur.getPaciente().getNombre()+"</h3>");
		stringBuffer.append("<p>Se le recuerda que tiene un turno programado para el dia <b>"+turnoService.formateoDeFecha(tur.getfHsInicio())
				+"</b> a <b>"+turnoService.formateoDeHora(tur.getfHsInicio())+"</b></p>");
		stringBuffer.append("<p>Se encontrara bajo el cuidado del Doctor: <b>"+tur.getMedico().getNombre()+"</b>.</p>");
		stringBuffer.append("<p>Gracias por su atencion !! Lo/a esperamos para el dia de consulta !! :D</p><br>");
		stringBuffer.append("<img src='/images/consultorio.jpg' style='float:left;width:40px;height:50px;' class=\'card-img-top\' alt=\'...\' />");
		stringBuffer.append("<p>									Att. Clinica Oftanmologica Poo-q</p>");
		return stringBuffer.toString();
	}
	
	//Obtener mail para Medico
	private String generarMailMedico(TurnoDTO tur) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<h3>Saludos Doctor: "+tur.getMedico().getNombre()+"</h3>");
		stringBuffer.append("<p>El/La paciente "+ tur.getPaciente().getNombre()+" tiene una cita programada con usted.</p>");
		stringBuffer.append("<h3><u>Datos para la consulta:</u></h3>");
		stringBuffer.append("<p>Fecha: "+turnoService.formateoDeFecha(tur.getfHsInicio())
			+"</p><p>Hora de Inicio: "+turnoService.formateoDeHora(tur.getfHsInicio())
			+"</p><p>Hora de Finalizacion: "+turnoService.formateoDeHora(tur.getfHsFinal())+"</p>");
		stringBuffer.append("<img src='/images/consultorio.jpg' style='float:left;width:40px;height:50px;' class=\'card-img-top\' alt=\'...\' />");
		stringBuffer.append("<p>									Att. Clinica Oftanmologica Poo-q</p>");
		return stringBuffer.toString();
	}
	
	//Obtener formato para cada mail
	public String formatoMail(String asunto, String redactarEmail, File file) throws Exception{
		StringBuffer stringBuffer= new StringBuffer();
		MimeMessage mimeMessage= javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage,true);

		stringBuffer.append("<div style='padding: 8px'");
		stringBuffer.append("<div><h2>"+asunto+"</h2></div>");
		stringBuffer.append("<div style='border-top: 1px solid black; border-bottom: 1px solid black; padding:8px; '><p>"+redactarEmail+"</p></div>");
		stringBuffer.append("<div style='display:flex; padding:12px;  '>");
		stringBuffer.append("<img src='cid:leftSideImage' style='float:left;width:40px;height:50px;'/>");
	    mimeMessageHelper.addAttachment(file.getName(), file);
		return stringBuffer.toString();
	}
}
