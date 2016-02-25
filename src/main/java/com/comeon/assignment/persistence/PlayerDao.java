/**
 * 
 */
package com.comeon.assignment.persistence;

import org.hibernate.SessionFactory;
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
    public Player savePlayer(String playerName) throws Exception {
        Player player = persist(new Player(playerName));
        return player;
    }
}
