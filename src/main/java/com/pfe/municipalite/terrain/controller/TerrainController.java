package com.pfe.municipalite.terrain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.municipalite.terrain.entity.Terrain;
import com.pfe.municipalite.terrain.service.TerrainService;

@RestController
@RequestMapping("terrain")
@CrossOrigin(origins = "http://localhost:4200")
public class TerrainController {

	@Autowired
	TerrainService service;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<?> getAllTerrains() {
		return service.getAllTerrains();
	}

	@RequestMapping(value = "/getTerrainById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getTerrainById(@PathVariable Long id) {
		return service.getTerrainById(id);
	}

	@RequestMapping(value = "/saveTerrain", method = RequestMethod.POST)
	public ResponseEntity<?> saveCommission(@RequestBody Terrain terrain) {
		return service.addTerrain(terrain);
	}

	@RequestMapping(value = "/updateTerrain/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> updateCommission(@PathVariable Long id, @RequestBody Terrain terrain) {
		return service.updateTerrain(id, terrain);
	}

	@RequestMapping(value = "/deleteTerrain/{id}", method = RequestMethod.GET)
	public void deleteCommission(@PathVariable Long id) {
		service.deleteTerrainById(id);
	}
}
