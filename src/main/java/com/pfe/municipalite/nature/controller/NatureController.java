package com.pfe.municipalite.nature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.municipalite.nature.entity.Nature;
import com.pfe.municipalite.nature.service.NatureService;

@RestController
@RequestMapping("natures")
@CrossOrigin(origins = "http://localhost:4200")
public class NatureController {

	@Autowired
	public NatureService natureService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<?> getAllNature() {
		return natureService.ListeNature();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> addNature(@RequestBody Nature nature) {
		return natureService.ajouterNature(nature);
	}

	@RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getNatureById(@PathVariable Long id) {
		return natureService.getNatureById(id);
	}

	@RequestMapping(value = "/deleteById/{id}", method = RequestMethod.GET)
	public void deleteNatureById(@PathVariable Long id) {
		natureService.supprimerById(id);
	}

}
