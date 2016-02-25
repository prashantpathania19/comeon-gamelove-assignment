/**
 * 
 */
package com.comeon.assignment.persistence;

import java.util.List;
import org.hibernate.Query;
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
    
    /**
     * This method finds player by its name
     * @param playerName
     * @return
     * @throws Exception
     */
    public Player findPlayer(String playerName) throws Exception {
        Player player = null;
        Query query = currentSession().getNamedQuery("player.findPlayer");
        query.setParameter("name", playerName);
        List<Player> playerList = (List<Player>)query.list();
        if (playerList != null && !playerList.isEmpty()) {
            player = playerList.get(0);
        }
        return player;
    }
}
