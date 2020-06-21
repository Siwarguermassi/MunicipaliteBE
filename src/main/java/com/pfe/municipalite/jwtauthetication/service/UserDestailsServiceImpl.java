package com.pfe.municipalite.jwtauthetication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pfe.municipalite.jwtauthetication.entity.User;
import com.pfe.municipalite.jwtauthetication.repository.UserRepository;

public class UserDestailsServiceImpl implements UserDetailsService {


	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

		//return UserPrinciple.build(user);
		return null;
	}

}
