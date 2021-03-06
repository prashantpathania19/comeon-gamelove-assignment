/**
 * 
 */
package com.comeon.assignment.persistence;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import io.dropwizard.hibernate.AbstractDAO;
import com.comeon.assignment.representations.Game;
import com.comeon.assignment.representations.GameTracking;
import com.comeon.assignment.representations.GameTrackingComposite;
import com.comeon.assignment.representations.Player;

/**
 * DAO class for CRUD operations
 * author Prashant Pathania
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
    public GameTracking saveGameTracking(Game game, Player player) throws Exception {
        GameTrackingComposite gameTrackingComposite = new GameTrackingComposite(game, player);
        GameTracking gameTracking = persist(new GameTracking(gameTrackingComposite));
        return gameTracking;
    }

    /**
     * Method to list all tracking
     * @return List<GameTracking>
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<GameTracking> listTracking() throws Exception {
        List<GameTracking> trackingList = null;
        Query query = currentSession().getNamedQuery("tracking.listTracking");
        trackingList = (List<GameTracking>)query.list();
        return trackingList;
    }

    /**
     * This method return top games from tracking
     * table
     * @return List<Long> representing list of long data
     */
    @SuppressWarnings("unchecked")
    public List<Object> getTopGames() {
        List<Object> topGamesList = null;
        Query query = currentSession().getNamedQuery("tracking.listTopGames");
        topGamesList = (List<Object>)query.list();
        return topGamesList;
    }
}
