package com.pfe.municipalite.dossier.entity;

import javax.persistence.*;

@Entity
@Table(name = "dossier")
public class Dossier {
    public String nature;
    public String proprietaire;
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String getNature() {
        return nature;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }
}
