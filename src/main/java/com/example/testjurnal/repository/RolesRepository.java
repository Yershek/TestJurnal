package com.example.testjurnal.repository;

import com.example.testjurnal.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findUserRoleByName(String name);
}
