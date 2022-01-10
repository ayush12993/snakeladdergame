package com.ayushm.gametry.repository.gamerepo;

import com.ayushm.gametry.model.game.Ladder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LadderRepository extends JpaRepository<Ladder,Long> {
}
