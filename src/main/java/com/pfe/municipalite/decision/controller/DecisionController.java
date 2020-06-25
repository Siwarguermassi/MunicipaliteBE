package com.pfe.municipalite.decision.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.municipalite.decision.entity.Decision;
import com.pfe.municipalite.decision.service.DecisionService;

@RestController
@RequestMapping("decision")
@CrossOrigin(origins = "http://localhost:4200")
public class DecisionController {

	@Autowired
	DecisionService service;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<?> getAllDecisions() {
		return service.getAllDecisions();
	}

	@RequestMapping(value = "/getDecisionById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDecisionById(@PathVariable Long id) {
		return service.getDecisionById(id);
	}

	@RequestMapping(value = "/getDossierDecision/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDossierDecision(@PathVariable Long id) {
		return service.getDossierDecision(id);
	}

	@RequestMapping(value = "/addDecision", method = RequestMethod.POST)
	public ResponseEntity<?> addDecision(@RequestBody Decision decision) {
		return service.addDecision(decision);
	}

	@RequestMapping(value = "/updateDecision/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> updateDecision(@PathVariable Long id, @RequestBody Decision decision) {
		return service.updateDecision(id, decision);
	}

	@RequestMapping(value = "/deleteDecision/{id}", method = RequestMethod.GET)
	public void deleteDecision(@PathVariable Long id) {
		service.deleteDecisionById(id);
	}

}
