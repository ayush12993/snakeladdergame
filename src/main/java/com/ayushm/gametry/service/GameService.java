package com.ayushm.gametry.service;

import com.ayushm.gametry.exception.InvalidGameException;
import com.ayushm.gametry.exception.InvalidParamException;
import com.ayushm.gametry.model.game.*;
import com.ayushm.gametry.model.login.User;
import com.ayushm.gametry.repository.gamerepo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.ayushm.gametry.model.game.enums.GameMode.EASY;
import static com.ayushm.gametry.model.game.enums.GameStatus.*;


@Service
public class GameService {
    final static int WINPOINT = 100;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameAuditRepository gameAuditRepository;
    @Autowired
    GameRunningStatusRepository gameRunningStatusRepository;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    GameActionRepository gameActionRepository;
    // roll the dice
    Game game = new Game();
    GameAudit gameAudit = new GameAudit();
    GameRunningStatus gameStatus = new GameRunningStatus();
    GameAction gameAction = new GameAction();
    String temp = UUID.randomUUID().toString();

    public Game createGame(Authentication authentication) throws InvalidGameException, InvalidParamException, JsonProcessingException {

        long id = Authorization(authentication);
        if (!playerRepository.existsById(id)){
            throw new InvalidGameException("Id does not exist");
        }
        if (game.getGameStatus()==NEW||game.getGameStatus()==IN_PROGRESS||game.getGameStatus()==FINISHED){
            throw new InvalidGameException("Game already created");
        }

        game.setGameBoardSize(WINPOINT);
        game.setGameId(temp);
        game.setGameMode(EASY);
        game.setCreatedBy(id);
        game.setGameStatus(NEW);
        gameAudit.setGameId(temp);
        gameAudit.setPlayer1Dice(0);
        gameAudit.setPlayer1Id(id);
        gameAction.setP1DiceCount(0);
        gameAudit.setPlayer1Position(0);
        gameStatus.setGameStatus(NEW);
        gameRepository.save(game);
        gameAuditRepository.save(gameAudit);
        gameRunningStatusRepository.save(gameStatus);
        gameActionRepository.save(gameAction);
        return game;}


    public Game connectToGame(String gameId,Authentication authentication) throws InvalidParamException, InvalidGameException, JsonProcessingException {
Game game =gameRepository.getById(gameId);
long id = Authorization(authentication);
        if (!gameRepository.existsById(gameId)|| !playerRepository.existsById(id)&& id==game.getCreatedBy()){
            throw new InvalidGameException("Something,s not right! Run");
        }
        if (game.getGameStatus()==IN_PROGRESS||game.getGameStatus()==FINISHED){
            throw new InvalidGameException("Game already created");
        }
       game.setUser(id);
        gameAudit.setPlayer2Id(id);
        gameAudit.setPlayer2Position(0);
        gameAction.setP2DiceCount(0);
        game.setGameStatus(IN_PROGRESS);
        gameRepository.save(game);
        gameActionRepository.save(gameAction);
        gameAuditRepository.save(gameAudit);
        return game;
    }

    public Game connectToRandomGame(Authentication authentication) throws InvalidParamException, InvalidGameException, JsonProcessingException {
        long id = Authorization(authentication);
        Game game = gameRepository.findTopByGameStatus(NEW).orElse(new Game());
        if (game.getGameStatus()==IN_PROGRESS){
            throw new InvalidGameException("Game already created");
        }
       game.setUser(id);
        gameAudit.setPlayer2Id(id);
        gameAudit.setPlayer2Position(0);
        gameAction.setP2DiceCount(0);
        game.setGameStatus(IN_PROGRESS);
        gameRepository.save(game);
        gameActionRepository.save(gameAction);
        gameAuditRepository.save(gameAudit);
        return game;
    }

    public Game connectToBotGame(String gameId){
        Game game = gameRepository.getById(gameId);
        game.setUser(1);
        gameAudit.setPlayer2Id(1);
        gameAudit.setPlayer2Position(0);
        gameAction.setP2DiceCount(0);
        game.setGameStatus(IN_PROGRESS);
        gameRepository.save(game);
        gameActionRepository.save(gameAction);
        gameAuditRepository.save(gameAudit);
        return game;
    }

    public Game findAll(){
       Game game =gameRepository.findTopByGameStatus(NEW).orElse(new Game());
       return game;
    }

    public Long Authorization(Authentication authentication) throws JsonProcessingException {
        authentication.getPrincipal();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

        //new ObjectMapper().readValue(new ObjectMapper().writeValueAsString(authentication.getPrincipal()), User.class);
        User user = objectMapper.readValue(new ObjectMapper().writeValueAsString(authentication.getPrincipal()), User.class);
    return user.getId();
    }

}
