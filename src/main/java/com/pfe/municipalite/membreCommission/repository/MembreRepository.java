package com.pfe.municipalite.membreCommission.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pfe.municipalite.membreCommission.entity.Membre;

public interface MembreRepository extends JpaRepository<Membre, Long> {

	@Query(value = "SELECT *  FROM membre_commission m WHERE m.commission_id = :idCom", nativeQuery = true)
	List<Membre> getCommissionMembers(@Param("idCom") Long idCom);
}
