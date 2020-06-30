package com.pfe.municipalite.dossier.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pfe.municipalite.dossier.entity.Dossier;
import com.pfe.municipalite.dossier.entity.DossierDetails;
import com.sun.el.stream.Optional;

public interface DossierRepository extends JpaRepository<Dossier, Long> {

	@Query(value = "SELECT dossier.id,dossier.id_nature,dossier.id_type_construction,dossier.id_terrain,dossier.id_proprietaire,\r\n"
			+ "dossier.nom_proprietaire,dossier.id_commission,dossier.id_architecte,dossier.id_decision,dossier.date,dossier.pieces,decision.decision as decision, \r\n"
			+ "nature_construction.libelle as nature , commission.date as date_commission,\r\n"
			+ "terrain.numero_terrain as numero_terrain,type_construction.type as type_construction, membre_commission.name as architecte_name FROM dossier \r\n"
			+ "LEFT JOIN decision  ON dossier.id_decision=decision.id \r\n"
			+ "LEFT JOIN nature_construction ON dossier.id_nature=nature_construction.id \r\n"
			+ "LEFT JOIN commission  ON dossier.id_commission=commission.id \r\n"
			+ "LEFT JOIN terrain  ON dossier.id_terrain=terrain.id \r\n"
			+ "LEFT JOIN type_construction  ON dossier.id_type_construction=type_construction.id \r\n"
			+ "LEFT JOIN membre_commission ON dossier.id_architecte=membre_commission.id", nativeQuery = true)
	List<Event> findJoin();

	@Query(value = "SELECT dossier.id,dossier.id_nature,dossier.id_type_construction,dossier.id_terrain,dossier.id_proprietaire,\r\n"
			+ "dossier.nom_proprietaire,dossier.id_commission,dossier.id_architecte,dossier.id_decision,dossier.date,dossier.pieces,decision.decision as decision, \r\n"
			+ "nature_construction.libelle as nature , commission.date as date_commission,\r\n"
			+ "terrain.numero_terrain as numero_terrain,type_construction.type as type_construction, membre_commission.name as architecte_name FROM dossier \r\n"
			+ "LEFT JOIN decision  ON dossier.id_decision=decision.id \r\n"
			+ "LEFT JOIN nature_construction ON dossier.id_nature=nature_construction.id \r\n"
			+ "LEFT JOIN commission  ON dossier.id_commission=commission.id \r\n"
			+ "LEFT JOIN terrain  ON dossier.id_terrain=terrain.id \r\n"
			+ "LEFT JOIN type_construction  ON dossier.id_type_construction=type_construction.id \r\n"
			+ "LEFT JOIN membre_commission ON dossier.id_architecte=membre_commission.id WHERE dossier.id = :id", nativeQuery = true)
	Event findOneDossier(@Param("id") Long id);


	@Query(value = "SELECT dossier.id,dossier.id_nature,dossier.id_type_construction,dossier.id_terrain,dossier.id_proprietaire,\r\n"
	+ "dossier.nom_proprietaire,dossier.id_commission,dossier.id_architecte,dossier.id_decision,dossier.date,dossier.pieces,decision.decision as decision, \r\n"
	+ "nature_construction.libelle as nature , commission.date as date_commission,\r\n"
	+ "terrain.numero_terrain as numero_terrain,type_construction.type as type_construction, membre_commission.name as architecte_name FROM dossier \r\n"
	+ "LEFT JOIN decision  ON dossier.id_decision=decision.id \r\n"
	+ "LEFT JOIN nature_construction ON dossier.id_nature=nature_construction.id \r\n"
	+ "LEFT JOIN commission  ON dossier.id_commission=commission.id \r\n"
	+ "LEFT JOIN terrain  ON dossier.id_terrain=terrain.id \r\n"
	+ "LEFT JOIN type_construction  ON dossier.id_type_construction=type_construction.id \r\n"
	+ "LEFT JOIN membre_commission ON dossier.id_architecte=membre_commission.id WHERE dossier.id_commission = :idCom", nativeQuery = true)
List<Event> findDossierCom(@Param("idCom") Long idCom);

	public interface Event {

		public long getId();

		public Long getId_nature();

		public Long getId_type_construction();

		public Long getId_terrain();

		public Long getId_proprietaire();

		public String getNom_proprietaire();

		public Long getId_commission();

		public Long getId_architecte();

		public Long getId_decision();

		public Long getDate();

		public String getPieces();

		public String getDecision();

		public Long getDate_commission();

		public String getType_construction();

		public String getArchitecte_name();

		public String getNature();

		public String getNumero_terrain();

	}

	@Query(value = "SELECT *  FROM dossier d WHERE a.id_commission = :idCom", nativeQuery = true)
	List<?> getCommissionDossiers(@Param("idCom") Long idCom);
}
