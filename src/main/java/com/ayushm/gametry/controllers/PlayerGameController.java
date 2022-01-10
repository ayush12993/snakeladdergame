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
@RequestMapping("/api/auth/playersnakeGame")
public class PlayerGameController {

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

	private static final Logger log = LoggerFactory.getLogger(PlayerGameController.class);

	@Autowired
	private PlayerServiceImpl playerServiceImpl;

	private Board board = new Board();
	Vector<Player> players = new Vector<Player>();
      private Player player1 = new Player();

	// get position by ID
	@Operation(
			summary = "Get List of Player by an Id! ",
			responses = {
					@ApiResponse(responseCode = "200", description = "Woo hoo your id is here!"),
					@ApiResponse(responseCode = "404", description = "A request with the specified id did not exist or  specified id was not found"),
					@ApiResponse(responseCode = "400", description = "The request is not of a type Get")
			}
	)
	@GetMapping(path = "/player/{id}")
	public Optional<Player> getPosition(@PathVariable Long id) {
	return playerServiceImpl.findById(id);
	}

	@Operation(
			summary = "Get List of All Player by an Id",
			responses = {
					@ApiResponse(responseCode = "200", description = "So many Players :) "),
					@ApiResponse(responseCode = "404", description = "A request with the specified id did not exist or  specified id was not found"),
					@ApiResponse(responseCode = "400", description = "The request is not of a type Get")
			}
	)
	// get all players
	@GetMapping(path = "/player/all")
	public List<Player> getPositionOfAll() {
		return playerRepository.findAll();
	}


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

//	@Operation(
//			summary = "Create player to see how our api works!",
//			responses = {
//					@ApiResponse(responseCode = "200", description = "Woo hoo you are created! \nYou are been created"),
//					@ApiResponse(responseCode = "404", description = "A request with the specified id did not exist or  specified id was not found"),
//					@ApiResponse(responseCode = "400", description = "The request is not of a type Post")
//			}
//	)


	// add new player Now there is no need to add player because all the player are
	// being added automatically
//	@PostMapping(path = "/player/new")
//	public ResponseEntity<?> addPlayer(@RequestBody Player player) {
//		playerRepository.save(player);
//	    //gameService.createEasyGame(player);
//		return new ResponseEntity("New Player with id "+player.getId()+" has been created!",HttpStatus.OK);
//	}


	@Operation(
			summary = "Start seeing player and position! User related Try checking this also",
			responses = {
					@ApiResponse(responseCode = "200", description = "Wish me good luck if it is working!"),
					@ApiResponse(responseCode = "404", description = "A request with the specified id did not exist or  specified id was not found"),
					@ApiResponse(responseCode = "400", description = "The request is not of a type Get")
			}
	)
	@GetMapping("/player/allPlayer")
	public  List<String> allPlayers() {
		return playerService.getList();
	}

	@Operation(
			summary = "Yo! Start Checking to see how the Api works! \nThis one only for testing Purposes!",
			responses = {
					@ApiResponse(responseCode = "200", description = "Wish me good luck if it is working!"),
					@ApiResponse(responseCode = "404", description = "A request with the specified id did not exist or  specified id was not found"),
					@ApiResponse(responseCode = "400", description = "The request is not of a type Post")
			}
	)
	@GetMapping("/totalUsers")
	public long totalUsers(){
		return playerRepository.countAllUsers();
	}

	@Operation(
			summary = "Yo! Start creating Bot Check to see how the Api works! \nThis one only for testing Purposes!",
			responses = {
					@ApiResponse(responseCode = "200", description = "Yo Many Bots are waiting for you"),
					@ApiResponse(responseCode = "404", description = "A request with the specified id did not exist or  specified id was not found"),
					@ApiResponse(responseCode = "400", description = "The request is not of a type Post")
			}
	)

	@GetMapping("getPlayerData/{gameId}")
	public List<GameAudit> gameAuditList(@PathVariable String gameId){
		return gameAuditRepository.findBygameId(gameId);
	}
	public void Authorization(Authentication authentication,User user) throws JsonProcessingException {

	}
}
