package com.pfe.municipalite.decision.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.municipalite.decision.entity.Decision;

@Repository
public interface DecisionRepository extends JpaRepository<Decision, Long> {

}
