package com.pfe.municipalite.terrain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pfe.municipalite.globalException.ProductNotFoundException;
import com.pfe.municipalite.terrain.entity.Terrain;
import com.pfe.municipalite.terrain.repository.TerrainRepository;

@Service
public class TerrainServiceImpl implements TerrainService {

	@Autowired
	TerrainRepository repository;

	@Override
	public ResponseEntity<?> getAllTerrains() {
		// TODO Auto-generated method stub
		try {
			return ResponseEntity.ok(repository.findAll());

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getTerrainById(Long id) {
		// TODO Auto-generated method stub
		try {
			repository.findById(id).orElseThrow(() -> new ProductNotFoundException("terrain n'esxiste pas"));
			return ResponseEntity.ok(repository.findById(id));

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> addTerrain(Terrain terrain) {
		// TODO Auto-generated method stub
		try {
			return ResponseEntity.ok(repository.save(terrain));

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> updateTerrain(Long id, Terrain terrain) {
		// TODO Auto-generated method stub
		try {
			repository.findById(id).orElseThrow(() -> new ProductNotFoundException("terrain n'esxiste pas"));
			Terrain terr = repository.findById(id).get();
			terr.setNumero_terrain(terrain.getNumero_terrain());
			return ResponseEntity.ok(repository.save(terr));

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public void deleteTerrainById(Long id) {
		// TODO Auto-generated method stub
		try {
			repository.findById(id).orElseThrow(() -> new ProductNotFoundException("terrain n'esxiste pas"));
			repository.deleteById(id);

		} catch (Exception e) {
			// TODO: handle exception
			new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

}
