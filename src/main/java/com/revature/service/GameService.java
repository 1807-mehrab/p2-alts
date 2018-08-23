package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Game;
import com.revature.repository.GameDao;

@Service
public class GameService {
	@Autowired
	GameDao dao;

	public void setDao(GameDao dao) {
		this.dao = dao;
	}

	@Transactional
	public List<Game> getAll() {
		return dao.getGames();
	}

	@Transactional
	public void add(Game game) {
		dao.addGame(game);
	}

	@Transactional
	public void delete(Game game) {
		dao.deleteGame(game);
	}

	@Transactional
	public Game getById(long id) {
		return dao.getGameById(id);
	}

	@Transactional
	public Game update(Game game) {
		return dao.updateGame(game);
	}
}