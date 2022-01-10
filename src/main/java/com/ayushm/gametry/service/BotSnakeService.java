package com.ayushm.gametry.service;

import com.ayushm.gametry.controllers.BotGameController;
import com.ayushm.gametry.controllers.GameController;
import com.ayushm.gametry.exception.InvalidGameException;
import com.ayushm.gametry.exception.NotFoundException;
import com.ayushm.gametry.model.game.*;
import com.ayushm.gametry.repository.gamerepo.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

import static com.ayushm.gametry.model.game.enums.GameStatus.FINISHED;


@Service
@NoArgsConstructor
@AllArgsConstructor
public class BotSnakeService {
    final static int WINPOINT = 100;

    @Autowired
    PlayerRepository playerRepository;

    private static final Logger log = LoggerFactory.getLogger(BotGameController.class);

    @Autowired
    GameAuditRepository gameAuditRepository;
    @Autowired
    GameRunningStatusRepository gameRunningStatusRepository;
    private Board board = new Board();
    GameRunningStatus gameRunningAction = new GameRunningStatus();
    @Autowired
    GameRepository gameRepository;
    @Autowired
    GameActionRepository gameActionRepository;
    @Autowired
    SnakeRepository snakeRepository;

    @Autowired
    LevelService levelService = new LevelService();

    @Autowired
    LadderRepository ladderRepository;
    // roll the dice
    Game game = new Game();
    Ladder ladder = new Ladder();
    Snake snake = new Snake();
    GameAudit gameAudit = new GameAudit();
    GameRunningStatus gameStatus = new GameRunningStatus();
    GameAction gameAction = new GameAction();
    String temp = UUID.randomUUID().toString();

    public Game playWithBot(String gameId) throws NotFoundException, InvalidGameException {
        Game game = gameRepository.getById(gameId);
        if (game.getGameStatus()==FINISHED) {
           throw new InvalidGameException("Game is already Finished");
        }

        if (game.getGameId()!=gameId) {
            throw new NotFoundException("Game not Found! Oh ho");
        }
       easy();
        if (gameAction.getP1DiceCount()== gameAction.getP2DiceCount()){
            int dice1 = rollDice();
            gameAction.setGameId(gameId);
            log.info("Dice shows Player 1-" + dice1 +" position "+gameAudit.getPlayer1Position());
            int i=0;
            int initial1Position = gameAudit.getPlayer1Position();
            int final1Position = initial1Position + dice1;
            if(final1Position > 100) {
                final1Position = initial1Position;
            }
            if(final1Position==100){
                log.info("Player 1 won ! Woo Hoo");
                game.setGameStatus(FINISHED);
                game.setGameAudit(gameAudit);
                gameAudit.setPlayer1Id(game.getCreatedBy());
                gameAudit.setGameAction(gameAction);
                gameAudit.setPlayer1Position(100);
                gameRepository.save(game);
                gameAction.setP1DiceCount(1+ gameAction.getP1DiceCount());
                return game;
            }
            if (board.checkSnake(final1Position) != 0){
                log.info("Player 1 Got bitten by snake");
                snake.setGameId(gameId);
                snake.setPlayerId(game.getCreatedBy());
                gameAudit.setPlayer1Id(game.getCreatedBy());
                snake.setSnakeHead(final1Position);
                final1Position = board.checkSnake(final1Position);
                gameAudit.setPlayer1Position(final1Position);
                gameAudit.setPlayer1Dice(dice1);
                gameAudit.setGameId(gameId);
                snake.setSnakeTail(final1Position);
                game.setGameAudit(gameAudit);
                game.setSnake(snake);
                gameAudit.setGameAction(gameAction);
                gameRepository.save(game);
                gameAction.setP1DiceCount(1+ gameAction.getP1DiceCount());
                snakeRepository.save(snake);
                return game;
            }
           else if (board.checkLadder(final1Position)!=0){
                log.info("Player 1 Got bitten by snake");
                ladder.setGameId(gameId);
               ladder.setLadderBottom(final1Position);
               ladder.setPlayerId(game.getCreatedBy());
                final1Position = board.checkLadder(final1Position);
                ladder.setLadderTop(final1Position);
                gameAudit.setGameId(gameId);
                gameAudit.setPlayer1Position(final1Position);
                gameAudit.setPlayer1Dice(dice1);
                gameAudit.setPlayer1Id(game.getCreatedBy());
                game.setGameAudit(gameAudit);
                game.setLadder(ladder);
                gameAudit.setGameAction(gameAction);
                gameRepository.save(game);
                ladderRepository.save(ladder);
                gameAction.setP1DiceCount(1+ gameAction.getP1DiceCount());
                
                
                return game;
            }
            game.setGameAudit(gameAudit);
            gameAudit.setGameId(gameId);
            gameAudit.setPlayer1Id(game.getCreatedBy());
            gameAction.setP1DiceCount(1+ gameAction.getP1DiceCount());
            game.setGameAudit(gameAudit);
            gameAudit.setPlayer1Position(final1Position);
            gameAudit.setPlayer1Dice(dice1);
            gameRepository.save(game);
            return game;
        }
        else if(gameAction.getP1DiceCount()!= gameAction.getP2DiceCount()){
            int dice2 = rollDice();
            log.info("Dice shows Player 2-" + dice2 +" "+" position "+gameAudit.getPlayer2Position());
            int initial2Position = gameAudit.getPlayer2Position();
            int final2Position = initial2Position + dice2;
            if (final2Position > 100) {
                final2Position = initial2Position;
            }
            // check if player won
            if (final2Position == 100) {
                log.info("Player 2 won ! Wo Hoo");
                game.setGameAudit(gameAudit);
                gameAudit.setGameId(gameId);
                gameAudit.setPlayer2Position(100);
                gameAudit.setPlayer1Id(game.getUser());
                game.setGameStatus(FINISHED);
                gameRepository.save(game);
                gameAction.setP2DiceCount(1+ gameAction.getP2DiceCount());
                gameAudit.setGameAction(gameAction);
                return game;
            }
            //return "You WON!";

            // check if bitten by snake or got a ladder
                if (board.checkSnake(final2Position) != 0) {
                    log.info("Player 2 Got bitten by snake");
                    snake.setGameId(gameId);
                    snake.setSnakeHead(final2Position);
                    snake.setPlayerId(game.getUser());
                    gameAudit.setPlayer1Id(game.getUser());
                    game.setGameAudit(gameAudit);
                    gameAudit.setGameId(gameId);
                    game.setSnake(snake);
                    final2Position = board.checkSnake(final2Position);
                    snake.setSnakeTail(final2Position);
                    gameAudit.setPlayer2Position(final2Position);
                    gameAudit.setPlayer2Dice(dice2);
                    gameAudit.setGameAction(gameAction);
                    gameRepository.save(game);
                    snakeRepository.save(snake);
                    gameAction.setP2DiceCount(1+ gameAction.getP2DiceCount());
                    return game;
                }
                else if(board.checkLadder(final2Position) != 0) {
                    log.info("Player 2 used the ladder");
                    game.setGameAudit(gameAudit);
                    gameAudit.setGameId(gameId);
                    ladder.setGameId(gameId);
                    gameAudit.setPlayer1Id(game.getUser());
                    ladder.setPlayerId(game.getUser());
                    ladder.setLadderBottom(final2Position);
                    gameAudit.setGameAction(gameAction);
                final2Position = board.checkLadder(final2Position);
                ladder.setLadderTop(final2Position);
                game.setLadder(ladder);
                gameAudit.setPlayer2Position(final2Position);
                gameAudit.setPlayer2Dice(dice2);
                gameRepository.save(game);
                ladderRepository.save(ladder);
                    gameAction.setP2DiceCount(1+ gameAction.getP2DiceCount());
                    
                
                return game;
           }
            gameAudit.setGameId(gameId);
            game.setGameAudit(gameAudit);
            gameAudit.setGameAction(gameAction);
            gameAudit.setPlayer1Id(game.getUser());
            gameAudit.setPlayer2Position(final2Position);
            gameAudit.setPlayer2Dice(dice2);
            gameRepository.save(game);
            gameAction.setP2DiceCount(1+ gameAction.getP2DiceCount());
            
            
            return game;
            }
        return null;
    }

    public void easy(){
        board.addSnake(25,4);
        board.addSnake(29,10);
        board.addSnake(33,27);
        board.addSnake(42,22);
        board.addSnake(63,59);
        board.addSnake(64,37);
        board.addSnake(70,49);
        board.addSnake(94,73);
        board.addSnake(96,47);
        board.addSnake(99,78);


        board.addLadder(2,23);
        board.addLadder(8,14);
        board.addLadder(26,35);
        board.addLadder(38,44);
        board.addLadder(31,86);
        board.addLadder(41,60);
        board.addLadder(56,77);
        board.addLadder(69,90);
        board.addLadder(79,81);
        board.addLadder(88,92);
    }

    // helper function to roll dice
    public static int rollDice() {
        return new Random().nextInt(6)+1;
    }
    // helper function to check if he won
    public boolean isWin(int position)
    {
        return WINPOINT == position;
    }
}
