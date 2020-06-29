package com.pfe.municipalite.nature.entity;

import javax.persistence.*;


@Entity
@Table(name="nature_construction")
public class Nature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String libelle;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
