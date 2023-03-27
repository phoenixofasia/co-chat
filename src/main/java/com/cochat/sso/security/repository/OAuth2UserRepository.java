package com.cochat.sso.security.repository;

import com.cochat.sso.security.entity.ChatOAuth2User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OAuth2UserRepository extends JpaRepository<ChatOAuth2User, String> {

    Optional<ChatOAuth2User> findByEmail(String email);

}
