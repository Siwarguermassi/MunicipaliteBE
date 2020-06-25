package com.pfe.municipalite.construction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pfe.municipalite.construction.entity.Construction;
import com.pfe.municipalite.construction.repository.ConstructionRepository;
import com.pfe.municipalite.globalException.ProductNotFoundException;

@Service
public class ConstructionServiceImpl implements ConstructionService {

	@Autowired
	ConstructionRepository repository;

	@Override
	public ResponseEntity<?> getAllConstruction() {
		// TODO Auto-generated method stub
		try {
			return ResponseEntity.ok(repository.findAll());

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getConstructionById(Long id) {
		// TODO Auto-generated method stub
		try {
			repository.findById(id).orElseThrow(() -> new ProductNotFoundException("construction n'esxiste pas"));
			return ResponseEntity.ok(repository.findById(id));

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> addConstruction(Construction construction) {
		// TODO Auto-generated method stub
		try {
			return ResponseEntity.ok(repository.save(construction));

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> updateConstruction(Long id, Construction construction) {
		// TODO Auto-generated method stub
		try {
			repository.findById(id).orElseThrow(() -> new ProductNotFoundException("construction n'esxiste pas"));
			Construction constr = repository.findById(id).get();
			constr.setType(construction.getType());
			return ResponseEntity.ok(repository.save(constr));

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public void deleteConstructionById(Long id) {
		// TODO Auto-generated method stub
		try {
			repository.findById(id).orElseThrow(() -> new ProductNotFoundException("construction n'esxiste pas"));
			repository.deleteById(id);

		} catch (Exception e) {
			// TODO: handle exception
			new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

}
