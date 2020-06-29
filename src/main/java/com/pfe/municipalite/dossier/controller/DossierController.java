package com.pfe.municipalite.dossier.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.municipalite.dossier.entity.Dossier;
import com.pfe.municipalite.dossier.entity.DossierDetails;
import com.pfe.municipalite.dossier.repository.DossierRepository;
import com.pfe.municipalite.dossier.service.DossierService;

@RestController
@RequestMapping("dossiers")
@CrossOrigin(origins = "http://localhost:4200")
public class DossierController {

	@Autowired
	public DossierService dossierService;

	@Autowired
	public DossierRepository repo;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<?> getAllDossier() {
		return dossierService.ListeDossier();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> addDossier(@RequestBody Dossier dossier) {
		return dossierService.ajouterDossier(dossier);
	}

	@RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDossierById(@PathVariable Long id) {
		return dossierService.getDossierById(id);
	}

	@RequestMapping(value = "/deleteById/{id}", method = RequestMethod.GET)
	public void deleteById(@PathVariable Long id) {
		dossierService.supprimerDossier(id);
	}

	@RequestMapping(value = "/updateById/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody Dossier dossier) {
		return dossierService.modifierDossier(id, dossier);
	}

	@RequestMapping(value = "/affectToCommission/{id}/{idCom}", method = RequestMethod.GET)
	public ResponseEntity<?> affectDossierToCommission(@PathVariable("id") Long id, @PathVariable("idCom") Long idCom) {
		return dossierService.affectDossierToCommission(id, idCom);
	}

	@RequestMapping(value = "/getAllDossiersWithDetails", method = RequestMethod.GET)
	public ResponseEntity<?> getAllDossiersWithDetails() {
		return ResponseEntity.ok(repo.findJoin());
	}

	@RequestMapping(value = "/getOneDossierDetails/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getOneDossierDetails(@PathVariable("id") Long id) {
		return ResponseEntity.ok(repo.findOneDossier(id));
	}

}
