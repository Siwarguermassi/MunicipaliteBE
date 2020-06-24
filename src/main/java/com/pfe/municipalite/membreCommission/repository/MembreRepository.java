package com.pfe.municipalite.membreCommission.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfe.municipalite.membreCommission.entity.Membre;

public interface MembreRepository extends JpaRepository<Membre, Long> {

}
