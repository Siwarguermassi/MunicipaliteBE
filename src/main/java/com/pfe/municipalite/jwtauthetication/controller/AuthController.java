package com.pfe.municipalite.jwtauthetication.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

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
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.municipalite.dossier.repository.DossierRepository;
import com.pfe.municipalite.globalException.ProductNotFoundException;
import com.pfe.municipalite.jwtauthetication.entity.JwtResponse;
import com.pfe.municipalite.jwtauthetication.entity.LoginForm;
import com.pfe.municipalite.jwtauthetication.entity.ResetPwdForm;
import com.pfe.municipalite.jwtauthetication.entity.Role;
import com.pfe.municipalite.jwtauthetication.entity.RoleName;
import com.pfe.municipalite.jwtauthetication.entity.User;
import com.pfe.municipalite.jwtauthetication.jwt.JwtProvider;
import com.pfe.municipalite.jwtauthetication.repository.RoleRepository;
import com.pfe.municipalite.jwtauthetication.repository.UserRepository;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	RoleRepository repository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	DossierRepository dossierRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		// checkRules();

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		Optional<User> usr = userRepository.findByUsername(userDetails.getUsername());
		User usr11 = usr.get();

		return ResponseEntity
				.ok(new JwtResponse(usr11.getId(), jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
		// checkRules();
		String strRoles = user.getUserRole();
		Set<Role> roles = new HashSet<>();

		switch (strRoles) {
		case "admin":
			Role adminRole = repository.findByName(RoleName.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
			roles.add(adminRole);
			break;
		case "pm":
			Role pmRole = repository.findByName(RoleName.ROLE_PM)
					.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
			roles.add(pmRole);

			break;
		default:
			dossierRepository.findById(user.getIdDossier())
					.orElseThrow(() -> new RuntimeException("Erreur! -> Numero de dossier invalide."));
			Role userRole = repository.findByName(RoleName.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
			roles.add(userRole);

		}
		user.setRoles(roles);
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		// return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
		return ResponseEntity.ok(user);
	}

	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
	public void deleteUser(@PathVariable long id) {
		userRepository.deleteById(id);
	}

	@RequestMapping(value = "/updateUser/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody User user) {
		User usr1 = userRepository.findById(id).get();
		usr1.setEmail(user.getEmail());
		usr1.setPhone(user.getPhone());
		usr1.setAddress(user.getAddress());
		usr1.setName(user.getName());

		return ResponseEntity.ok(userRepository.save(usr1));
	}

	@RequestMapping(value = "/resetPWDSend/{email}", method = RequestMethod.GET)
	public ResponseEntity<?> resetPasswordSend(@PathVariable String email)
			throws AddressException, MessagingException, IOException {
		userRepository.findByUsername(email).orElseThrow(() -> new ProductNotFoundException("User Not Found"));
		User usr = userRepository.findByUsername(email).get();
		String token = UUID.randomUUID().toString();
		System.out.println(usr.getEmail());
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

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(usr.getEmail()));
		msg.setSubject("Reset Password");
		msg.setContent("Hello my friend", "text/html");
		msg.setSentDate(new Date());
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("http://localhost:4200/resetPwd/" + token + '/' + usr.getEmail(), "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		MimeBodyPart attachPart = new MimeBodyPart();

		// attachPart.attachFile("C:/Users/asus/Desktop/Dev/Municipality.pdf");
		// multipart.addBodyPart(attachPart);
		msg.setContent(multipart);
		Transport.send(msg);

		return ResponseEntity.ok(HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/resetPassword/{email}", method = RequestMethod.POST)
	public ResponseEntity<?> resetPassword(@RequestBody ResetPwdForm request, @PathVariable String email) {
		userRepository.findByUsername(email).orElseThrow(() -> new ProductNotFoundException("User Not Founf"));
		User usr = userRepository.findByUsername(email).get();
		usr.setPassword(encoder.encode(request.getPassword()));
		return ResponseEntity.ok(userRepository.save(usr));
	}

	@RequestMapping(value = "/changePWD/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> changePassword(@RequestBody ResetPwdForm request, @PathVariable Long id) {
		userRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("User Not Founf"));
		User usr = userRepository.findById(id).get();
		System.out.println(request.getLastPassword());
		if (encoder.matches(request.getLastPassword(), usr.getPassword())) {
			usr.setPassword(encoder.encode(request.getPassword()));
			return ResponseEntity.ok(userRepository.save(usr));
		}
		return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/getUsersByRole/{role}", method = RequestMethod.GET)
	public ResponseEntity<?> getUsersByRole(@PathVariable String role) {
		return ResponseEntity.ok(userRepository.findByRole(role));
	}

	@RequestMapping(value = "/getUsersById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUsersById(@PathVariable Long id) {
		return ResponseEntity.ok(userRepository.findById(id));
	}

	/*
	 * private void checkRules() { Optional<Role> userRole =
	 * repository.findByName(RoleName.ROLE_USER); Optional<Role> empRole =
	 * repository.findByName(RoleName.ROLE_PM); Optional<Role> adminRole =
	 * repository.findByName(RoleName.ROLE_ADMIN); if (userRole.isEmpty()) { Role rl
	 * = new Role(); rl.setName(RoleName.ROLE_USER); repository.save(rl); } if
	 * (empRole.isEmpty()) { Role rl2 = new Role(); rl2.setName(RoleName.ROLE_PM);
	 * repository.save(rl2); } if (adminRole.isEmpty()) { Role rl3 = new Role();
	 * rl3.setName(RoleName.ROLE_ADMIN); repository.save(rl3); } }
	 */

}
