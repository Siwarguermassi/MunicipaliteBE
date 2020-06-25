package com.pfe.municipalite.terrain.service;

import org.springframework.http.ResponseEntity;

import com.pfe.municipalite.terrain.entity.Terrain;

public interface TerrainService {

	public ResponseEntity<?> getAllTerrains();

	public ResponseEntity<?> getTerrainById(Long id);

	public ResponseEntity<?> addTerrain(Terrain terrain);

	public ResponseEntity<?> updateTerrain(Long id, Terrain terrain);

	public void deleteTerrainById(Long id);
}
