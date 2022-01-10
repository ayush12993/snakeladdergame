package com.ayushm.gametry.repository.gamerepo;

import com.ayushm.gametry.model.game.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {

    @Query(value = "SELECT COUNT(*) FROM Player u", nativeQuery = true)
    long countAllUsers();

    Boolean existsByUsername(String username);


}
