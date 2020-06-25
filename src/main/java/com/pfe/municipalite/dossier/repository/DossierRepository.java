package com.pfe.municipalite.dossier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pfe.municipalite.dossier.entity.Dossier;

public interface DossierRepository extends JpaRepository<Dossier, Long> {

	@Query(value = "SELECT dossier.id,decision.decision, nature_construction.libelle  FROM dossier LEFT JOIN decision  ON dossier.id_commission=decision.id LEFT JOIN nature_construction  ON dossier.id_nature=nature_construction.id", nativeQuery = true)
	List<?> findJoin();

	@Query(value = "SELECT *  FROM dossier d WHERE a.id_commission = :idCom", nativeQuery = true)
	List<?> getCommissionDossiers(@Param("idCom") Long idCom);
}
