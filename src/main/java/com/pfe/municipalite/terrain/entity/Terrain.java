package com.pfe.municipalite.terrain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "terrain")
public class Terrain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String numero_terrain;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero_terrain() {
		return numero_terrain;
	}

	public void setNumero_terrain(String numero_terrain) {
		this.numero_terrain = numero_terrain;
	}
}
