package com.ayushm.gametry.repository.gamerepo;

import com.ayushm.gametry.model.game.GameRunningStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRunningStatusRepository extends JpaRepository<GameRunningStatus,Long> {
}
