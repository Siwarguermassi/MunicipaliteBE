package com.pfe.municipalite.commission.service;

import org.springframework.http.ResponseEntity;

import com.pfe.municipalite.commission.entity.Commission;

public interface CommissionService {

	public ResponseEntity<?> getAllCommissions();

	public ResponseEntity<?> getCommissionById(Long id);
	
	public ResponseEntity<?> getCommissionDossiers(Long id);

	public ResponseEntity<?> addCommission(Commission commission);

	public ResponseEntity<?> updateCommission(Long id, Commission commission);

	public void deleteCommissionById(Long id);

}
