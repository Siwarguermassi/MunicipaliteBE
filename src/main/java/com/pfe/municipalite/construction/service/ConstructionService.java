package com.pfe.municipalite.construction.service;

import org.springframework.http.ResponseEntity;

import com.pfe.municipalite.construction.entity.Construction;

public interface ConstructionService {

	public ResponseEntity<?> getAllConstruction();

	public ResponseEntity<?> getConstructionById(Long id);

	public ResponseEntity<?> addConstruction(Construction construction);

	public ResponseEntity<?> updateConstruction(Long id, Construction construction);

	public void deleteConstructionById(Long id);
}
