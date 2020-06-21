package com.pfe.municipalite.nature.service;


import com.pfe.municipalite.globalException.ProductNotFoundException;
import com.pfe.municipalite.nature.entity.Nature;
import com.pfe.municipalite.nature.repository.NatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NatureServiceImpl implements NatureService{

    @Autowired
    public NatureRepository naturerepository;

    @Override
    public ResponseEntity<?> ajouterNature(Nature nature) {

        return ResponseEntity.ok(naturerepository.save(nature));
    }

    @Override
    public ResponseEntity<?> ListeNature() {

        return ResponseEntity.ok(naturerepository.findAll());
    }

    @Override
    public ResponseEntity<?> getNatureById(Long id)
    {
        naturerepository.findById(id).orElseThrow(() -> new ProductNotFoundException("nature inexistante"));
        return ResponseEntity.ok((naturerepository.findById(id)));
    }

    @Override
    public void supprimerById(Long id) {
        naturerepository.findById(id).orElseThrow(() -> new ProductNotFoundException("nature inexistante"));
       naturerepository.deleteById(id);
    }
}
