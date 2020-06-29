package com.pfe.municipalite.dossier.entity;

public class DossierDetails {

	
	

	public DossierDetails(Object id, Object id_nature, Object id_type_construction, Object id_terrain,
			Object id_proprietaire, Object nom_proprietaire, Object id_commission, Object id_architecte,
			Object id_decision, Object date, Object pieces, Object decision, Object date_commission,
			Object type_construction, Object architecte_name, Object nature, Object numero_terrain) {
		super();
		this.id = id;
		this.id_nature = id_nature;
		this.id_type_construction = id_type_construction;
		this.id_terrain = id_terrain;
		this.id_proprietaire = id_proprietaire;
		this.nom_proprietaire = nom_proprietaire;
		this.id_commission = id_commission;
		this.id_architecte = id_architecte;
		this.id_decision = id_decision;
		this.date = date;
		this.pieces = pieces;
		this.decision = decision;
		this.date_commission = date_commission;
		this.type_construction = type_construction;
		this.architecte_name = architecte_name;
		this.nature = nature;
		this.numero_terrain = numero_terrain;
	}
	public Object id;
	public Object id_nature;
	public Object id_type_construction;
	public Object id_terrain;
	public Object id_proprietaire;
	public Object nom_proprietaire;
	public Object id_commission;
	public Object id_architecte;
	public Object id_decision;
	public Object date;
	public Object pieces;
	public Object decision;
	public Object date_commission;
	public Object type_construction;
	public Object architecte_name;
	public Object nature;
	public Object numero_terrain;
	
	
	
	public DossierDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Object getId() {
		return id;
	}
	public void setId(Object id) {
		this.id = id;
	}
	public Object getId_nature() {
		return id_nature;
	}
	public void setId_nature(Object id_nature) {
		this.id_nature = id_nature;
	}
	public Object getId_type_construction() {
		return id_type_construction;
	}
	public void setId_type_construction(Object id_type_construction) {
		this.id_type_construction = id_type_construction;
	}
	public Object getId_terrain() {
		return id_terrain;
	}
	public void setId_terrain(Object id_terrain) {
		this.id_terrain = id_terrain;
	}
	public Object getId_proprietaire() {
		return id_proprietaire;
	}
	public void setId_proprietaire(Object id_proprietaire) {
		this.id_proprietaire = id_proprietaire;
	}
	public Object getNom_proprietaire() {
		return nom_proprietaire;
	}
	public void setNom_proprietaire(Object nom_proprietaire) {
		this.nom_proprietaire = nom_proprietaire;
	}
	public Object getId_commission() {
		return id_commission;
	}
	public void setId_commission(Object id_commission) {
		this.id_commission = id_commission;
	}
	public Object getId_architecte() {
		return id_architecte;
	}
	public void setId_architecte(Object id_architecte) {
		this.id_architecte = id_architecte;
	}
	public Object getId_decision() {
		return id_decision;
	}
	public void setId_decision(Object id_decision) {
		this.id_decision = id_decision;
	}
	public Object getDate() {
		return date;
	}
	public void setDate(Object date) {
		this.date = date;
	}
	public Object getPieces() {
		return pieces;
	}
	public void setPieces(Object pieces) {
		this.pieces = pieces;
	}
	public Object getDecision() {
		return decision;
	}
	public void setDecision(Object decision) {
		this.decision = decision;
	}
	public Object getDate_commission() {
		return date_commission;
	}
	public void setDate_commission(Object date_commission) {
		this.date_commission = date_commission;
	}
	public Object getType_construction() {
		return type_construction;
	}
	public void setType_construction(Object type_construction) {
		this.type_construction = type_construction;
	}
	public Object getArchitecte_name() {
		return architecte_name;
	}
	public void setArchitecte_name(Object architecte_name) {
		this.architecte_name = architecte_name;
	}
	public Object getNature() {
		return nature;
	}
	public void setNature(Object nature) {
		this.nature = nature;
	}
	public Object getNumero_terrain() {
		return numero_terrain;
	}
	public void setNumero_terrain(Object numero_terrain) {
		this.numero_terrain = numero_terrain;
	}
	@Override
	public String toString() {
		return "DossierDetails [id=" + id + ", id_nature=" + id_nature + ", id_type_construction="
				+ id_type_construction + ", id_terrain=" + id_terrain + ", id_proprietaire=" + id_proprietaire
				+ ", nom_proprietaire=" + nom_proprietaire + ", id_commission=" + id_commission + ", id_architecte="
				+ id_architecte + ", id_decision=" + id_decision + ", date=" + date + ", pieces=" + pieces
				+ ", decision=" + decision + ", date_commission=" + date_commission + ", type_construction="
				+ type_construction + ", architecte_name=" + architecte_name + ", nature=" + nature
				+ ", numero_terrain=" + numero_terrain + "]";
	}
	
	
	
	

}
