package com.cochat.sso.security.repository;

import com.cochat.sso.security.entity.PersistentLoginToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersistentLoginTokenRepository extends JpaRepository<PersistentLoginToken, String> {

    Optional<PersistentLoginToken> findBySeries(String series);
}
