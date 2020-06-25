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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.municipalite.membreCommission.entity.Membre;
import com.pfe.municipalite.membreCommission.repository.MembreRepository;
import com.pfe.municipalite.membreCommission.service.MembreService;

@RestController
@RequestMapping("membrecommission")
@CrossOrigin(origins = "http://localhost:4200")
public class MembreController {

	@Autowired
	MembreService service;

	@Autowired
	MembreRepository repository;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<?> getAllMembers() {
		return service.getAllMembers();
	}

	@RequestMapping(value = "/getMemberById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getMemberById(@PathVariable("id") Long id) {
		return service.getMemberById(id);
	}

	@RequestMapping(value = "/deleteMemberById/{id}", method = RequestMethod.GET)
	public void deleteMemberById(@PathVariable("id") Long id) {
		service.deleteMemberById(id);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> addMembre(@RequestBody Membre membre) {
		return service.addMember(membre);
	}

	@RequestMapping(value = "/invite/{memberId}/{comId}", method = RequestMethod.GET)
	public String sendEmail(@PathVariable("memberId") Long memberId, @PathVariable("comId") Long comId)
			throws AddressException, MessagingException, IOException {
		Membre membre = repository.findById(memberId).get();
		membre.setCommission_id(comId);
		service.updateMemberById(memberId, membre);
		sendmail(membre.getEmail());
		return "Email sent successfully";
	}

	private void sendmail(String email) throws AddressException, MessagingException, IOException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("municipality2020municipality@gmail.com", "municipality.2020");
			}
		});
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("municipality2020municipality@gmail.com", false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		msg.setSubject("Invitation");
		msg.setContent("Hello my friend", "text/html");
		msg.setSentDate(new Date());
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("commission le 24/06/2020", "text/html");

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
