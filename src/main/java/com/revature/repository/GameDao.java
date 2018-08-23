package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.revature.beans.Game;

@Repository
public class GameDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Game> getGames() {
		try {
			return sessionFactory.getCurrentSession().createCriteria(Game.class).list();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	public Game getGameById(long id) {
		Session s = sessionFactory.getCurrentSession();
		return (Game) s.get(Game.class, id);
	}

	public void addGame(Game game) {
		sessionFactory.getCurrentSession().persist(game);
	}

	public void deleteGame(Game game) {
		sessionFactory.getCurrentSession().delete(game);
	}

	public Game updateGame(Game game) {
		return (Game) sessionFactory.getCurrentSession().merge(game);
	}
}
