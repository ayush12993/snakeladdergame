package com.ayushm.gametry.repository.loginrepo;

import java.util.Optional;

import com.ayushm.gametry.model.login.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT COUNT(*) FROM Users u", nativeQuery = true)
    long countAllUsers();

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);}