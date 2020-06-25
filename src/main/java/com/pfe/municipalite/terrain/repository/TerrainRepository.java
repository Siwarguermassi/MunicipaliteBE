package com.pfe.municipalite.terrain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfe.municipalite.terrain.entity.Terrain;

public interface TerrainRepository extends JpaRepository<Terrain, Long> {

}
