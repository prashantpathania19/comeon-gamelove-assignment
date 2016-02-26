/**
 * 
 */
package com.comeon.assignment.persistence;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import com.comeon.assignment.representations.Game;
import io.dropwizard.hibernate.AbstractDAO;

/**
 * Game DAO class for CRUD operations
 */
public class GameDao extends AbstractDAO<Game> {
    /**
     * Constructor.
     *
     * @param sessionFactory Hibernate session factory.
     */
    public GameDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Default method to save data into database
     * @param playerName
     * @param gameName
     * @throws Exception
     */
    public Game saveGame(String gameName) throws Exception {
        Game game = persist(new Game(gameName));
        return game;
    }

    /**
     * This method finds game by its name
     * @param gameName
     * @return
     * @throws Exception
     */
    public Game findGame(String gameName) throws Exception {
        Game game = null;
        Query query = currentSession().getNamedQuery("game.findGameByName");
        query.setParameter("name", gameName);
        List<Game> gameList = (List<Game>)query.list();
        if (gameList != null && !gameList.isEmpty()) {
            game = gameList.get(0);
        }
        return game;
    }
}

