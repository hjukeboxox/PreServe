package com.example.PreServe.domain.repository;

import com.example.PreServe.domain.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByUserId(String userId);

    Optional<Store> findByStoreName(String storeName);
}
