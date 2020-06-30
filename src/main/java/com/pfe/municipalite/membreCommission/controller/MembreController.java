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

import com.pfe.municipalite.commission.entity.Commission;
import com.pfe.municipalite.commission.repository.CommissionRepository;
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
	
	@Autowired
	CommissionRepository commissionRepository;

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
	
	@RequestMapping(value = "/updateMemberById/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> updateMemberById(@PathVariable("id") Long id, @RequestBody Membre membre) {
		return service.updateMemberById(id, membre);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> addMembre(@RequestBody Membre membre) {
		return service.addMember(membre);
	}

	@RequestMapping(value = "/invite/{memberId}/{comId}/{subject}", method = RequestMethod.GET)
	public ResponseEntity<?> sendEmail(@PathVariable("memberId") Long memberId, @PathVariable("comId") Long comId, @PathVariable("subject") String subject)
			throws AddressException, MessagingException, IOException {
		Membre membre = repository.findById(memberId).get();
		if(subject.equals("invite")) {
			membre.setCommission_id(comId);
		}
		else {
			membre.setCommission_id(null);
		}
		service.updateMemberById(memberId, membre);
		Commission com = commissionRepository.findById(comId).get();
		Long date = com.getDate();
		String dt = new Date(com.getDate()).toString();
		System.out.println(dt);
		sendmail(membre.getEmail(), subject, dt);
		return ResponseEntity.ok(membre);
	}

	private void sendmail(String email, String subject, String date) throws AddressException, MessagingException, IOException {
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
		if(subject.equals("invite")) {
			msg.setSubject("Invitation");
		}
		else {
			msg.setSubject("Annulation d'invitation");
		}
		msg.setContent("Hello my friend", "text/html");
		msg.setSentDate(new Date());
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("commission "+ date, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		MimeBodyPart attachPart = new MimeBodyPart();

		//attachPart.attachFile("C:/Users/asus/Desktop/Dev/Municipality.pdf");
		//multipart.addBodyPart(attachPart);
		msg.setContent(multipart);
		Transport.send(msg);
	}
}
