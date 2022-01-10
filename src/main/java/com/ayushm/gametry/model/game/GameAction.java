package com.ayushm.gametry.model.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String gameId;

    private int snakeId;

    private int ladderId;

    private int P1DiceCount;

    private int P2DiceCount;

}
