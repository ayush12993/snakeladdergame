package com.ayushm.gametry.repository.loginrepo;

import java.util.Optional;

import com.ayushm.gametry.model.login.enums.ERole;
import com.ayushm.gametry.model.login.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(
            ERole name);
}