package com.pfe.municipalite.commission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pfe.municipalite.commission.entity.Commission;
import com.pfe.municipalite.commission.repository.CommissionRepository;
import com.pfe.municipalite.dossier.repository.DossierRepository;
import com.pfe.municipalite.globalException.ProductNotFoundException;

@Service
public class CommissionServiceImpl implements CommissionService {

	@Autowired
	CommissionRepository repository;

	@Autowired
	DossierRepository dossierRepository;

	@Override
	public ResponseEntity<?> getAllCommissions() {
		// TODO Auto-generated method stub
		try {
			return ResponseEntity.ok(repository.findAll());

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getCommissionById(Long id) {
		// TODO Auto-generated method stub
		try {
			repository.findById(id).orElseThrow(() -> new ProductNotFoundException("commission n'esxiste pas"));
			return ResponseEntity.ok(repository.findById(id));

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> addCommission(Commission commission) {
		// TODO Auto-generated method stub
		try {
			return ResponseEntity.ok(repository.save(commission));

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> updateCommission(Long id, Commission commission) {
		// TODO Auto-generated method stub
		try {
			repository.findById(id).orElseThrow(() -> new ProductNotFoundException("commission n'esxiste pas"));
			Commission com = repository.findById(id).get();
			com.setDate(commission.getDate());
			return ResponseEntity.ok(repository.save(com));

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public void deleteCommissionById(Long id) {
		// TODO Auto-generated method stub
		try {
			repository.findById(id).orElseThrow(() -> new ProductNotFoundException("commission n'esxiste pas"));
			repository.deleteById(id);

		} catch (Exception e) {
			// TODO: handle exception
			new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getCommissionDossiers(Long id) {
		// TODO Auto-generated method stub
		try {
			repository.findById(id).orElseThrow(() -> new ProductNotFoundException("commission n'esxiste pas"));
			return ResponseEntity.ok(dossierRepository.findDossierCom(id));

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
		}
	}

}
