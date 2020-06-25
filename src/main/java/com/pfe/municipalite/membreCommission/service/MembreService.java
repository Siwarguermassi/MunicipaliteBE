package com.pfe.municipalite.membreCommission.service;

import org.springframework.http.ResponseEntity;

import com.pfe.municipalite.membreCommission.entity.Membre;

public interface MembreService {

	public ResponseEntity<?> getAllMembers();
	
	public ResponseEntity<?> addMember(Membre member);
	
	public ResponseEntity<?> getMemberById(Long id);
	
	public void deleteMemberById(Long id);
	
	public ResponseEntity<?> updateMemberById(Long id, Membre membre);

	public ResponseEntity<?> inviteMember(Long id, Long commissionId);

}
