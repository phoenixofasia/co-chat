package com.cochat.sso.security.repository;

import com.cochat.sso.security.entity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, String> {

    Optional<Verification> findByUsername(String username);
}
