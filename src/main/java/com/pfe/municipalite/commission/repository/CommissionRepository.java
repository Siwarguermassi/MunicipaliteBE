package com.pfe.municipalite.commission.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfe.municipalite.commission.entity.Commission;

public interface CommissionRepository extends JpaRepository<Commission, Long> {

}
