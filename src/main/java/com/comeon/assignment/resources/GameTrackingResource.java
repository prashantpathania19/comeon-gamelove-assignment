/**
 * 
 */
package com.comeon.assignment.resources;

import java.util.ArrayList;
import java.util.Iterator;
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
import com.comeon.assignment.representations.TopGamesVO;

/**
 * This class is an end point for saving GameTracking
 * related data
 * @author Prashant Pathania
 */
@Path("/love")
@Produces(MediaType.APPLICATION_JSON)
public class GameTrackingResource {
    /**
     * The DAO object to manipulate game tracking.
     */
    private PlayerDao playerDao;
    private GameDao gameDao;
    private GameTrackingDao gameTrackingDao;

    /**
     * Constructor with parameter setting all DAO objects
     * @param playerDao - refers to player DAO
     * @param gameDao - refers to game DAO
     * @param gameTrackingDao - refers to game tracking DAO
     */
    public GameTrackingResource(PlayerDao playerDao, GameDao gameDao, GameTrackingDao gameTrackingDao) {
        this.playerDao = playerDao;
        this.gameDao = gameDao;
        this.gameTrackingDao = gameTrackingDao;
    }

    /**
     * This method is a POST method to save a game
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
     * This method is a POST method to save a player
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
     * This method is a POST method that 
     * saves game tracking by saving game and
     * player object. It will save tracking of only
     * saved games and players. please save game and player before
     * adding anything to the tracking table.
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
                gameTrackingDao.saveGameTracking(game, player);
                responseString = new ResponseString("Data Saved: Game: " + game.getName() + ", Player: " + player.getName());
            }
        } catch (Exception exception) {
            return new ResponseString("Exception while executing saveGameTracking: " + exception.getMessage());
        }
        return responseString;
    }

    /**
     * This method is a GET method to
     * see entries in GameTracking table
     * @return List<GameTracking> - represents a list of GameTracking detail
     */
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

    /**
     * This method is a post method that return
     * list of top games liked by players. If data
     * does not exist then it will show blank list
     * @return - List<Games> representing list of Games
     */
    @GET
    @Timed
    @Path("/topGames")
    @UnitOfWork
    public List<TopGamesVO> getTopGames() {
        List<TopGamesVO> topGamesList = null;
        List<Object> gamesList = gameTrackingDao.getTopGames();
        if (gamesList != null && !gamesList.isEmpty()) {
            topGamesList = new ArrayList<TopGamesVO>();
            Iterator<Object> iterator = gamesList.iterator();
            while(iterator.hasNext()) {
                Object[] gamesListObj = (Object[]) iterator.next();
                topGamesList.add(new TopGamesVO((Long)gamesListObj[0], (String)gamesListObj[2]));
            }
        }
        return topGamesList;
    }
}
