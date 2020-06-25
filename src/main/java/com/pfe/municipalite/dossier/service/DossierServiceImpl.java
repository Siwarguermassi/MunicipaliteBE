package com.pfe.municipalite.dossier.service;

import com.pfe.municipalite.dossier.entity.Dossier;
import com.pfe.municipalite.dossier.repository.DossierRepository;
import com.pfe.municipalite.globalException.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DossierServiceImpl implements DossierService {
	@Autowired
	public DossierRepository repository;

	@Override
	public ResponseEntity<?> ajouterDossier(Dossier dossier) {
		return ResponseEntity.ok(repository.save(dossier));
	}

	@Override
	public ResponseEntity<?> ListeDossier() {

		return ResponseEntity.ok(repository.findAll());
	}

	@Override
	public ResponseEntity<?> getDossierById(Long id) {
		repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Dossier n'esxiste pas"));
		return ResponseEntity.ok(repository.findById(id));
	}

	@Override
	public void supprimerDossier(Long id) {
		repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Dossier n'esxiste pas"));
		repository.deleteById(id);
	}

	@Override
	public ResponseEntity<?> modifierDossier(Long id, Dossier dossier) {
		repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Dossier n'esxiste pas"));
		Dossier doss = repository.findById(id).get();
		doss.setId_architecte(dossier.getId_architecte());
		doss.setId_commission(dossier.getId_commission());
		doss.setId_decision(dossier.getId_decision());
		doss.setId_nature(dossier.getId_nature());
		doss.setId_proprietaire(dossier.getId_proprietaire());
		doss.setId_terrain(dossier.getId_terrain());
		doss.setId_type_construction(dossier.getId_type_construction());

		return ResponseEntity.ok(repository.save(doss));
	}

	@Override
	public ResponseEntity<?> affectDossierToCommission(Long id, Long comId) {
		// TODO Auto-generated method stub
		try {
			repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Dossier n'esxiste pas"));
			Dossier doss = repository.findById(id).get();
			doss.setId_commission(comId);
			return ResponseEntity.ok(repository.save(doss));
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

}
