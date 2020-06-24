package com.pfe.municipalite.decision.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pfe.municipalite.decision.entity.Decision;
import com.pfe.municipalite.decision.repository.DecisionRepository;
import com.pfe.municipalite.globalException.ProductNotFoundException;

@Service
public class DecisionServiceImpl implements DecisionService {

	@Autowired
	DecisionRepository repository;

	@Override
	public ResponseEntity<?> getAllDecisions() {
		// TODO Auto-generated method stub
		try {
			return ResponseEntity.ok(repository.findAll());

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Server Error!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getDecisionById(Long id) {
		// TODO Auto-generated method stub
		try {
			repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Decision n'esxiste pas"));
			return ResponseEntity.ok(repository.findById(id));
		} catch (Exception e) {
			return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public ResponseEntity<?> getDossierDecision(Long dossierId) {
		// TODO Auto-generated method stub
		try {
			repository.findById(dossierId).orElseThrow(() -> new ProductNotFoundException("Decision n'esxiste pas"));
			return ResponseEntity.ok(repository.findById(dossierId));

		} catch (Exception e) {
			return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> addDecision(Decision decision) {
		// TODO Auto-generated method stub
		try {
			return ResponseEntity.ok(repository.save(decision));
		} catch (Exception e) {
			return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> updateDecision(Long id, Decision decicion) {
		// TODO Auto-generated method stub
		try {
			repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Decision n'esxiste pas"));
			Decision dec = repository.findById(id).get();
			dec.setDecision(decicion.getDecision());
			return ResponseEntity.ok(repository.save(dec));

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public void deleteDecisionById(Long id) {
		// TODO Auto-generated method stub
		try {
			repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Decision n'esxiste pas"));
			repository.deleteById(id);
		} catch (Exception e) {
			 new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

}
