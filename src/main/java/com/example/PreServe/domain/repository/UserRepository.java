package com.example.PreServe.domain.repository;

import com.example.PreServe.domain.entity.User;
import com.example.PreServe.domain.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User>  findById(Long id);
    Optional<User>  findByUserId(String userId);
}
