package com.pfe.municipalite.jwtauthetication.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.municipalite.jwtauthetication.entity.JwtResponse;
import com.pfe.municipalite.jwtauthetication.entity.LoginForm;
import com.pfe.municipalite.jwtauthetication.entity.Role;
import com.pfe.municipalite.jwtauthetication.entity.RoleName;
import com.pfe.municipalite.jwtauthetication.entity.User;
import com.pfe.municipalite.jwtauthetication.jwt.JwtProvider;
import com.pfe.municipalite.jwtauthetication.repository.RoleRepository;
import com.pfe.municipalite.jwtauthetication.repository.UserRepository;

@RestController
@RequestMapping("auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	RoleRepository repository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtProvider jwtProvider;

	
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Optional<User> usr = userRepository.findByUsername(userDetails.getUsername());
        System.out.println("hello");
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}
	
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {

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
			Role userRole = repository.findByName(RoleName.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
			roles.add(userRole);

		}
		user.setRoles(roles);
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
	}
	
}
