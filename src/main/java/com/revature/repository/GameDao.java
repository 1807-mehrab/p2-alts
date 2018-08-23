package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

	public Game getGameById(int id) {
		Session s = sessionFactory.getCurrentSession();
		return (Game) s.get(Game.class, id);
	}

	public void addGame(Game game) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();

		s.persist(game);

		tx.commit();
	}

	public void deleteGame(Game game) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();

		s.delete(game);

		tx.commit();
	}

	public Game updateGame(Game game) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();

		Game g = (Game) s.merge(game);

		tx.commit();

		return g;
	}

	public Game getGameById(long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
