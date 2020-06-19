package com.pfe.municipalite.jwtauthetication.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.municipalite.jwtauthetication.entity.Role;
import com.pfe.municipalite.jwtauthetication.entity.RoleName;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}