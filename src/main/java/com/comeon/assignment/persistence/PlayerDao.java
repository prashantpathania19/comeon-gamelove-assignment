/**
 * 
 */
package com.comeon.assignment.persistence;

import org.hibernate.SessionFactory;
import com.comeon.assignment.representations.Game;
import com.comeon.assignment.representations.Player;

import io.dropwizard.hibernate.AbstractDAO;

/**
 * DAO class for CRUD operations
 */
public class PlayerDao extends AbstractDAO<Player> {
    /**
     * Constructor.
     *
     * @param sessionFactory Hibernate session factory.
     */
    public PlayerDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Default method to save data into database
     * @param playerName
     * @param gameName
     * @throws Exception
     */
    public void saveGameTracking(String playerName, String gameName) throws Exception {
        Player player = new Player(playerName);
        //Game game = new Game(gameName);
        persist(player);
        //persist(game);
    }
}
