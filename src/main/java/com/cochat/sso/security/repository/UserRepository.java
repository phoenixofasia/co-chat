package com.cochat.sso.security.repository;

import com.cochat.sso.security.entity.ChatUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<ChatUser, String> {

    Optional<ChatUser> findByEmailIgnoreCase(String email);

    Optional<ChatUser> findByUsernameIgnoreCase(String username);

    Optional<ChatUser> findByUsername(String username);
}
