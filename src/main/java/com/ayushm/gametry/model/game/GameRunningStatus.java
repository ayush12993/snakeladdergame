package com.ayushm.gametry.model.game;

import com.ayushm.gametry.model.game.enums.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class GameRunningStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long gameRunId;

    private String gameId;

    private GameStatus gameStatus;

    private long snakeId;

    private long ladderId;

    private  int player1Posi;

    private  int player2Posi;

    private long player1Id;

    private long player2Id;
}
