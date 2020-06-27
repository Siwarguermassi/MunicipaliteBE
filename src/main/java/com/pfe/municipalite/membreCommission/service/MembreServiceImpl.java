package com.pfe.municipalite.membreCommission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pfe.municipalite.globalException.ProductNotFoundException;
import com.pfe.municipalite.membreCommission.entity.Membre;
import com.pfe.municipalite.membreCommission.repository.MembreRepository;

@Service
public class MembreServiceImpl implements MembreService {

	@Autowired
	MembreRepository repository;

	@Override
	public ResponseEntity<?> getAllMembers() {
		// TODO Auto-generated method stub
		try {
			return ResponseEntity.ok(repository.findAll());

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Server Error!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> addMember(Membre member) {
		// TODO Auto-generated method stub
		try {
			return ResponseEntity.ok(repository.save(member));
		} catch (Exception e) {
			return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getMemberById(Long id) {
		// TODO Auto-generated method stub
		try {
			repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Membre n'esxiste pas"));
			return ResponseEntity.ok(repository.findById(id));
		} catch (Exception e) {
			return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public void deleteMemberById(Long id) {
		// TODO Auto-generated method stub
		try {
			repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Membre n'esxiste pas"));
			repository.deleteById(id);
		} catch (Exception e) {
			new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> updateMemberById(Long id, Membre membre) {
		// TODO Auto-generated method stub
		try {
			Membre mb = repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Membre n'esxiste pas"));
			mb.setEmail(membre.getEmail());
			mb.setCommission_id(membre.getCommission_id());
			mb.setName(membre.getName());
			mb.setType(membre.getType());
			mb.setPhone(membre.getPhone());
			mb.setAddress(mb.getAddress());
			return ResponseEntity.ok(repository.save(mb));
		} catch (Exception e) {
			// TODO: handle exception
			new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
		return null;
	}

	@Override
	public ResponseEntity<?> inviteMember(Long id, Long commissionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
