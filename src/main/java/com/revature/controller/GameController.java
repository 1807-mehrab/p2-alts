package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Game;
import com.revature.service.GameService;

@RestController
public class GameController {
	@Autowired
	private GameService service;

	public void setService(GameService service) {
		this.service = service;
	}

	@GetMapping("/game/{id}")
	public Game byId(@PathVariable("id") long id) {
		return service.getById(id);
	}

	@GetMapping("/game")
	public List<Game> games() {
		return service.getAll();
	}

	@PostMapping(path = "/game", consumes = "application/json", produces = "application/json")
	public Game addGame(@RequestBody Game game) {
		service.add(game);
		return service.getByTitle(game.getTitle());
	}

	@DeleteMapping("/game/{id}")
	public @ResponseBody ResponseEntity<Game> deleteGame(@PathVariable("id") long id) {
		Game game = service.getById(id);
		if (game == null) {
			return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
		}

		service.delete(game);
		return new ResponseEntity<Game>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(path = "/game/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Game> updateUser(@PathVariable("id") long id, @RequestBody Game game) {
		Game currentGame = service.getById(id);

		if (currentGame == null) {
			return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
		}

		GameController.populate(currentGame, game);

		service.update(currentGame);
		return new ResponseEntity<Game>(currentGame, HttpStatus.OK);
	}

	protected static void populate(Game currentGame, Game game) {
		currentGame.setCopies(game.getCopies());
		currentGame.setPhoto(game.getPhoto());
		currentGame.setPrice(game.getPrice());
		currentGame.setTitle(game.getTitle());
	}
}