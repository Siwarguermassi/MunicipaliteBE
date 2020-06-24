package com.pfe.municipalite.decision.service;

import org.springframework.http.ResponseEntity;

import com.pfe.municipalite.decision.entity.Decision;

public interface DecisionService {

	public ResponseEntity<?> getAllDecisions();

	public ResponseEntity<?> getDecisionById(Long id);

	public ResponseEntity<?> getDossierDecision(Long dossierId);

	public ResponseEntity<?> addDecision(Decision decision);

	public ResponseEntity<?> updateDecision(Long id, Decision decicion);

	public void deleteDecisionById(Long id);

}
