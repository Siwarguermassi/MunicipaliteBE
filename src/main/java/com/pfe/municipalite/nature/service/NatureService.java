package com.pfe.municipalite.nature.service;

import com.pfe.municipalite.nature.entity.Nature;
import org.springframework.http.ResponseEntity;

public interface NatureService {
    public ResponseEntity<?> ajouterNature(Nature nature);
    public ResponseEntity<?> ListeNature();
    public ResponseEntity<?> getNatureById(Long id);
    public void supprimerById(Long id);
}
