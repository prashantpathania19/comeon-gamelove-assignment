/**
 * 
 */
package com.comeon.assignment.resources;

import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import io.dropwizard.hibernate.UnitOfWork;
import com.codahale.metrics.annotation.Timed;
import com.comeon.assignment.persistence.GameDao;
import com.comeon.assignment.persistence.GameTrackingDao;
import com.comeon.assignment.persistence.PlayerDao;
import com.comeon.assignment.representations.Game;
import com.comeon.assignment.representations.GameTracking;
import com.comeon.assignment.representations.Player;
import com.comeon.assignment.representations.ResponseString;

/**
 * This class is an end point for saving GameTracking
 * related data
 * @author Prashant Pathania
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
    private GameTrackingDao gameTrackingDao;

    /**
     * Constructor.
     *
     * @param game tracking DAO object to manipulate data.
     */
    public GameTrackingResource(PlayerDao playerDao, GameDao gameDao, GameTrackingDao gameTrackingDao) {
        this.playerDao = playerDao;
        this.gameDao = gameDao;
        this.gameTrackingDao = gameTrackingDao;
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

    /**
     * This method saves game tracking by saving
     * game and player
     * @param playerName - refers to player name
     * @param gameName - refers to game name
     * @return ResponseString - name of response string
     */
    @POST
    @Timed
    @Path("/saveGameTracking")
    @UnitOfWork
    public ResponseString saveGameTracking(@FormParam("playerName") String playerName,
            @FormParam("gameName") String gameName) {
        ResponseString responseString = null;
        try {
            Player player = playerDao.findPlayer(playerName);
            Game game = gameDao.findGame(gameName);
            if (player == null || game == null) {
                responseString = new ResponseString("Invalid Game Name or Player Name");
            } else {
                gameTrackingDao.saveGameTracking(game.getId(), player.getId());
                responseString = new ResponseString("Data Saved: Game: " + game.getName() + ", Player: " + player.getName());
            }
        } catch (Exception exception) {
            return new ResponseString("Exception while executing saveGameTracking: " + exception.getMessage());
        }
        return responseString;
    }

    @GET
    @Timed
    @Path("/listTracking")
    @UnitOfWork
    public List<GameTracking> getTracking() {
        try {
            return gameTrackingDao.listTracking();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
