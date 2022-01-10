package com.ayushm.gametry.model.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Snake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long snakeId;
    private int snakeHead;
    private int snakeTail;
    private String gameId;
    private long playerId;


    public Snake(int snakeHead,int snakeTail) {
        this.snakeHead=snakeHead;
        this.snakeTail=snakeTail;
    }

}

