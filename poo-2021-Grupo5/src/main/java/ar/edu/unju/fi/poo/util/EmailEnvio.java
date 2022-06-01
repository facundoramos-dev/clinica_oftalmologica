package ar.edu.unju.fi.poo.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import ar.edu.unju.fi.poo.service.impl.EmailService;

@Component
public class EmailEnvio {

	private final static Logger logger = Logger.getLogger(EmailService.class);
	
	@Autowired
	private JavaMailSender remi;
	
	public boolean emailEnviado(String mensaje, String email,String asunto) {
		boolean send = false;
		MimeMessage mailArmado = remi.createMimeMessage();
		MimeMessageHelper asistente = new MimeMessageHelper(mailArmado);		
		try {
			asistente.setTo(email);
			asistente.setText(mensaje, true);
			asistente.setSubject(asunto);
			remi.send(mailArmado);
			send = true;
		} catch (MessagingException e) {
			logger.error("Hubo un error al enviar el mail", e);
		}
		return send;
	}
	
}
