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
public class Ladder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ladderId;
    private int ladderTop;
    private int ladderBottom;
    private String gameId;
    private long playerId;
    public Ladder(int ladderTop, int ladderBottom){
        this.ladderTop=ladderTop;
        this.ladderBottom=ladderBottom;
    }
}
