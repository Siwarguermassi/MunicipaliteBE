package com.pfe.municipalite.jwtauthetication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pfe.municipalite.jwtauthetication.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    
    @Query(value = "SELECT * FROM users a WHERE a.user_role = :role", 
    		  nativeQuery = true)
    			List<User> findByRole(@Param("role") String role);
}
