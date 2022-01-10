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
public class GameAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long audituuid;

    private  String gameId;

    private long player1Id;

    private long player2Id;

    private int player1Position;

    private int player2Position;

    private int player1Dice;

    private int player2Dice;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "game_action_id")
    private GameAction gameAction;

}
