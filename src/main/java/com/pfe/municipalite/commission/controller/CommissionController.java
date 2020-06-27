package com.pfe.municipalite.commission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.municipalite.commission.entity.Commission;
import com.pfe.municipalite.commission.service.CommissionService;
import com.pfe.municipalite.membreCommission.repository.MembreRepository;

@RestController
@RequestMapping("commission")
@CrossOrigin(origins = "http://localhost:4200")
public class CommissionController {

	@Autowired
	CommissionService service;

	@Autowired
	MembreRepository repository;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<?> getAllCommissions() {
		return service.getAllCommissions();
	}

	@RequestMapping(value = "/getCommissionById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCommissionById(@PathVariable Long id) {
		return service.getCommissionById(id);
	}

	@RequestMapping(value = "/getCommissionMembersById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCommissionMembersById(@PathVariable Long id) {
		return ResponseEntity.ok(repository.getCommissionMembers(id));
	}

	@RequestMapping(value = "/getCommissionDossiers/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCommissionDossiers(@PathVariable Long id) {
		return service.getCommissionDossiers(id);
	}

	@RequestMapping(value = "/saveCommission", method = RequestMethod.POST)
	public ResponseEntity<?> saveCommission(@RequestBody Commission commission) {
		return service.addCommission(commission);
	}

	@RequestMapping(value = "/updateCommission/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> updateCommission(@PathVariable Long id, @RequestBody Commission commission) {
		return service.updateCommission(id, commission);
	}

	@RequestMapping(value = "/deleteCommission/{id}", method = RequestMethod.GET)
	public void deleteCommission(@PathVariable Long id) {
		service.deleteCommissionById(id);
	}

}
