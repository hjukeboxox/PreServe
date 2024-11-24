package com.example.PreServe.domain.repository;


import com.example.PreServe.domain.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, Long> {

	Optional<AuthEntity> findByUserId(String userid);
	//boolean existsByUsername(String username);
	boolean existsByUserId(String userid);
}
