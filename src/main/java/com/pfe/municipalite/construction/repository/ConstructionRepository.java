package com.pfe.municipalite.construction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfe.municipalite.construction.entity.Construction;

public interface ConstructionRepository extends JpaRepository<Construction, Long> {

}
