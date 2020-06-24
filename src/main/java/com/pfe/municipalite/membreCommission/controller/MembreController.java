package com.pfe.municipalite.membreCommission.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("membrecommission")
public class MembreController {

	@RequestMapping(value = "/sendemail")
	public String sendEmail() throws AddressException, MessagingException, IOException {
		sendmail();
		return "Email sent successfully";
	}

	private void sendmail() throws AddressException, MessagingException, IOException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("from@gmail.com", "EmailPassword");
			}
		});
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("from@gmail.com", false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("to@gmail.com"));
		msg.setSubject("email subject");
		msg.setContent("Hello my friend", "text/html");
		msg.setSentDate(new Date());
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("email subject", "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		MimeBodyPart attachPart = new MimeBodyPart();

		// attachPart.attachFile("/var/tmp/image19.png");
		attachPart.attachFile("C:/Users/asus/Desktop/Dev/Municipality.pdf");
		multipart.addBodyPart(attachPart);
		msg.setContent(multipart);
		Transport.send(msg);
	}
}
