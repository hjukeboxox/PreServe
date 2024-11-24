package com.example.PreServe.domain.repository;

import com.example.PreServe.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByReservIdAndUserId(Long reservId, String userId);

    @Query("SELECT AVG(r.reviewStar) FROM Review r WHERE r.storeId = :storeId")
    Double findAverageStarByStoreId(@Param("storeId") Long storeId);
}