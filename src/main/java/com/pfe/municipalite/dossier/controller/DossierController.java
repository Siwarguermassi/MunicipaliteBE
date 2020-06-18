package com.pfe.municipalite.dossier.controller;

import com.pfe.municipalite.dossier.entity.Dossier;
import com.pfe.municipalite.dossier.service.DossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dossiers")
public class DossierController {

    @Autowired
    public DossierService dossierService;

    @RequestMapping (value="/getAll", method = RequestMethod.GET)
    public ResponseEntity<?> getAllDossier(){
        return dossierService.ListeDossier();
    }

    @RequestMapping (value="/save", method = RequestMethod.POST)
    public ResponseEntity<?> addDossier(@RequestBody Dossier dossier){
        return dossierService.ajouterDossier(dossier);
    }
}
