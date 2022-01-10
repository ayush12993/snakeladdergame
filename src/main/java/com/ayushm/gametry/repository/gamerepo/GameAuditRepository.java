package com.ayushm.gametry.repository.gamerepo;

import com.ayushm.gametry.model.game.GameAudit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameAuditRepository extends JpaRepository<GameAudit,Long> {

    List<GameAudit> findBygameId(String gameId);
}
