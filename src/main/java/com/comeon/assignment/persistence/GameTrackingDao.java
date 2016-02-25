/**
 * 
 */
package com.comeon.assignment.persistence;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import com.comeon.assignment.representations.GameTracking;

/**
 * DAO class for CRUD operations
 */
public class GameTrackingDao extends AbstractDAO<GameTracking> {
    /**
     * Constructor.
     *
     * @param sessionFactory Hibernate session factory.
     */
    public GameTrackingDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Default method to save GameTracking data into database
     * @param playerName
     * @param gameName
     * @throws Exception
     */
    public GameTracking saveGameTracking(Long gameId, Long playerId) throws Exception {
        GameTracking gameTracking = persist(new GameTracking(gameId, playerId));
        return gameTracking;
    }
}
