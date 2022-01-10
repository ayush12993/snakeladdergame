package com.ayushm.gametry.repository.gamerepo;

import com.ayushm.gametry.model.game.Snake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnakeRepository extends JpaRepository<Snake,Long> {
}
