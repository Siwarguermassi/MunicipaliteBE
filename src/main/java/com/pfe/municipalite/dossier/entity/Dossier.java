package com.pfe.municipalite.dossier.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dossier")
public class Dossier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	public Long id_nature;
	public Long id_type_construction;
	public Long id_terrain;
	public Long id_proprietaire;
	public String nom_proprietaire;
	public Long id_commission;
	public Long id_architecte;
	public Long id_decision;
	public Long date;
	public String pieces;

	public String getPieces() {
		return pieces;
	}

	public void setPieces(String pieces) {
		this.pieces = pieces;
	}

	public Long getId_proprietaire() {
		return id_proprietaire;
	}

	public void setId_proprietaire(Long id_proprietaire) {
		this.id_proprietaire = id_proprietaire;
	}

	public String getNom_proprietaire() {
		return nom_proprietaire;
	}

	public void setNom_proprietaire(String nom_proprietaire) {
		this.nom_proprietaire = nom_proprietaire;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getId_nature() {
		return id_nature;
	}

	public void setId_nature(Long id_nature) {
		this.id_nature = id_nature;
	}

	public Long getId_type_construction() {
		return id_type_construction;
	}

	public void setId_type_construction(Long id_type_construction) {
		this.id_type_construction = id_type_construction;
	}

	public Long getId_terrain() {
		return id_terrain;
	}

	public void setId_terrain(Long id_terrain) {
		this.id_terrain = id_terrain;
	}

	public Long getId_commission() {
		return id_commission;
	}

	public void setId_commission(Long id_commission) {
		this.id_commission = id_commission;
	}

	public Long getId_architecte() {
		return id_architecte;
	}

	public void setId_architecte(Long id_architecte) {
		this.id_architecte = id_architecte;
	}

	public Long getId_decision() {
		return id_decision;
	}

	public void setId_decision(Long id_decision) {
		this.id_decision = id_decision;
	}
}
