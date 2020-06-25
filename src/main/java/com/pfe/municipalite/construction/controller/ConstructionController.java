package com.pfe.municipalite.construction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.municipalite.construction.entity.Construction;
import com.pfe.municipalite.construction.service.ConstructionService;

@RestController
@RequestMapping("construction")
@CrossOrigin(origins = "http://localhost:4200")
public class ConstructionController {

	@Autowired
	ConstructionService service;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<?> getAllConstruction() {
		return service.getAllConstruction();
	}

	@RequestMapping(value = "/getConstructionById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getConstructionById(@PathVariable Long id) {
		return service.getConstructionById(id);
	}

	@RequestMapping(value = "/saveConstruction", method = RequestMethod.POST)
	public ResponseEntity<?> saveConstruction(@RequestBody Construction construction) {
		return service.addConstruction(construction);
	}

	@RequestMapping(value = "/updateTerrain/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> updateCommission(@PathVariable Long id, @RequestBody Construction construction) {
		return service.updateConstruction(id, construction);
	}

	@RequestMapping(value = "/deleteTerrain/{id}", method = RequestMethod.GET)
	public void deleteCommission(@PathVariable Long id) {
		service.deleteConstructionById(id);
	}
}
