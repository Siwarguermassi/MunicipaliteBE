package com.pfe.municipalite.dossier.service;

import com.pfe.municipalite.dossier.entity.Dossier;
import org.springframework.http.ResponseEntity;

public interface DossierService {
    public ResponseEntity<?> ajouterDossier(Dossier dossier);
    public ResponseEntity<?> ListeDossier();
    public ResponseEntity<?> getDossierById(Long id);

}
