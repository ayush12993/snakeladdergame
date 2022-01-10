package com.ayushm.gametry.model.game;

import com.ayushm.gametry.model.game.enums.GameMode;
import com.ayushm.gametry.model.game.enums.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Game  {
    @Id
    private String gameId;

    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    private int gameBoardSize;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "snake_snake_id")
    private Snake snake;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "ladder_ladder_id")
    private Ladder ladder;

    private long createdBy;

    private long user;

    private GameMode gameMode;

    private int noOfPlayers;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "game_audit_audituuid")
    private GameAudit gameAudit;

}
