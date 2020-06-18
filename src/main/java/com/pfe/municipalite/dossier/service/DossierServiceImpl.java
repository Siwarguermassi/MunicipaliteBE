package com.pfe.municipalite.dossier.service;

import com.pfe.municipalite.dossier.entity.Dossier;
import com.pfe.municipalite.dossier.repository.DossierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DossierServiceImpl implements DossierService{
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
       return null;
    }
}
