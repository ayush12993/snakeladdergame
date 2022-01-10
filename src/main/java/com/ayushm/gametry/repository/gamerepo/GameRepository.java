package com.ayushm.gametry.repository.gamerepo;

import com.ayushm.gametry.model.game.Game;
import com.ayushm.gametry.model.game.enums.GameStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game,String> {

    Optional<Game> findTopByGameStatus(GameStatus gameStatus);

    Boolean existsByCreatedBy(long createdBy);
    Boolean existsByUser(long user);
}
