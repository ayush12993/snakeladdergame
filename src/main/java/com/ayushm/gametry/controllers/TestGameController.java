package com.ayushm.gametry.controllers;

import com.ayushm.gametry.exception.InvalidGameException;
import com.ayushm.gametry.exception.InvalidParamException;
import com.ayushm.gametry.exception.NotFoundException;
import com.ayushm.gametry.model.game.Board;
import com.ayushm.gametry.model.game.Game;
import com.ayushm.gametry.model.game.GameAudit;
import com.ayushm.gametry.model.game.Player;
import com.ayushm.gametry.model.login.User;
import com.ayushm.gametry.repository.gamerepo.GameAuditRepository;
import com.ayushm.gametry.repository.gamerepo.GameRepository;
import com.ayushm.gametry.repository.gamerepo.PlayerRepository;
import com.ayushm.gametry.service.*;
import com.ayushm.gametry.service.impl.PlayerServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Vector;

import static com.ayushm.gametry.model.game.enums.GameStatus.NEW;


@RestController
@RequestMapping("/api/auth/testsnakeGame")
public class TestGameController {

	@Autowired
	GameRepository gameRepository;

	@Autowired
	GameAuditRepository gameAuditRepository;

	@Autowired
	private EasySnakeService snakeService;

	@Autowired
	private RandomSnakeService randomSnakeService;

	@Autowired
	private BotSnakeService botSnakeService;

	@Autowired
	GameService gameService;

	@Autowired
	PlayerRepository playerRepository;


	@Autowired
	LevelService levelService;

	@Autowired
	PlayerService playerService;

	private static final Logger log = LoggerFactory.getLogger(TestGameController.class);

	@Autowired
	private PlayerServiceImpl playerServiceImpl;

	private Board board = new Board();
	Vector<Player> players = new Vector<Player>();
      private Player player1 = new Player();


	@Operation(
			summary = "Get List of All NEW Games",
			responses = {
					@ApiResponse(responseCode = "200", description = "So many Players :) "),
					@ApiResponse(responseCode = "404", description = "A request with the specified id did not exist or  specified id was not found"),
					@ApiResponse(responseCode = "400", description = "The request is not of a type Get")
			}
	)
	// get all players
	@GetMapping(path = "/player/newgames")
	public Optional<Game> getgamesAll() {
		return (gameRepository.findTopByGameStatus(NEW));
	}

	@PostMapping(path = "/connectToGame/{gameId}")
	public Game connectToGame(Authentication authentication,@PathVariable String gameId) throws InvalidParamException, InvalidGameException, JsonProcessingException {return
			gameService.connectToGame(gameId,authentication);}


	@Operation(
			summary = "Yo! Start Playing to see how the Api works!",
			responses = {
					@ApiResponse(responseCode = "200", description = "Wish me good luck if it is working!"),
					@ApiResponse(responseCode = "404", description = "A request with the specified id did not exist or  specified id was not found"),
					@ApiResponse(responseCode = "400", description = "The request is not of a type Post")
			}
	)
	// roll the dice
	@PostMapping(path = "/gameplay/{gameId}")
	public Game play(@PathVariable String gameId) throws InvalidGameException, NotFoundException {
       return snakeService.playWithPlayer(gameId);
	}

}
