package com.pfe.municipalite.nature.controller;


import com.pfe.municipalite.nature.entity.Nature;
import com.pfe.municipalite.nature.repository.NatureRepository;
import com.pfe.municipalite.nature.service.NatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("natures")
public class NatureController {

    @Autowired
    public NatureService natureService;

    @RequestMapping (value="/getAll", method = RequestMethod.GET)
    public ResponseEntity<?> getAllNature(){
        return natureService.ListeNature();
    }
    @RequestMapping (value="/save", method = RequestMethod.POST)
    public ResponseEntity<?> addNature(@RequestBody Nature nature){
        return natureService.ajouterNature(nature);
    }

    @RequestMapping(value="/getById/{id}",method = RequestMethod.GET)
    public  ResponseEntity<?> getNatureById(@PathVariable Long id){
        return natureService.getNatureById(id);
    }

    @RequestMapping(value="/deleteById/{id}",method = RequestMethod.GET)
    public  void deleteNatureById(@PathVariable Long id){
          natureService.supprimerById(id);}


}
