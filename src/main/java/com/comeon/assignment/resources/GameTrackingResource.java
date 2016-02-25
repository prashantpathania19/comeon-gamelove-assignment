/**
 * 
 */
package com.comeon.assignment.resources;

import io.dropwizard.hibernate.UnitOfWork;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.codahale.metrics.annotation.Timed;
import com.comeon.assignment.persistence.GameDao;
import com.comeon.assignment.persistence.PlayerDao;
import com.comeon.assignment.representations.Game;
import com.comeon.assignment.representations.Player;

/**
 * @author prashant.pathania
 *
 */
@Path("/player")
@Produces(MediaType.APPLICATION_JSON)
public class GameTrackingResource {
    /**
     * The DAO object to manipulate game tracking.
     */
    private PlayerDao playerDao;
    private GameDao gameDao;

    /**
     * Constructor.
     *
     * @param game tracking DAO object to manipulate data.
     */
    public GameTrackingResource(PlayerDao playerDao, GameDao gameDao) {
        this.playerDao = playerDao;
        this.gameDao = gameDao;
    }

    /**
     * This method save game entity
     * @param gameName - name of the game
     * @return game object
     */
    @POST
    @Timed
    @Path("/saveGame")
    @UnitOfWork
    public Game saveGame(@FormParam("gameName") String gameName) {
        Game game = null;
        try {
            game = gameDao.saveGame(gameName);
            System.out.println("GameId: " + game.getId());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return game;
    }

    /**
     * This method save player entity
     * @param gameName - name of the player
     * @return player object
     */
    @POST
    @Timed
    @Path("/savePlayer")
    @UnitOfWork
    public Player savePlayer(@FormParam("playerName") String playerName) {
        Player player = null;
        try {
            player = playerDao.savePlayer(playerName);
            System.out.println("PlayerId: " + player.getId());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return player;
    }

    /*@POST
    @Timed
    @Path("/saveGameTracking")
    @UnitOfWork
    public String saveGameTracking(@FormParam("playerName") String playerName,
            @FormParam("gameName") String gameName) {
        try {
            Player player = playerDao.saveGameTracking(playerName);
            Game game = gameDao.saveGame(gameName);
            System.out.println("PlayerId: " + player.getId());
            System.out.println("GameId: " + game.getId());
        } catch (Exception exception) {
            return new String("Exception while executing saveGameTracking: " + exception.getMessage());
        }
        
        return "success";
    }*/
}
