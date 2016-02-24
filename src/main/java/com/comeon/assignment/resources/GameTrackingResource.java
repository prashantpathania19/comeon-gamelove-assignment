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
import com.comeon.assignment.persistence.PlayerDao;

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

    /**
     * Constructor.
     *
     * @param game tracking DAO object to manipulate data.
     */
    public GameTrackingResource(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @POST
    @Timed
    @Path("/saveGameTracking")
    @UnitOfWork
    public String saveGameTracking(@FormParam("playerName") String playerName,
            @FormParam("gameName") String gameName) {
        try {
            playerDao.saveGameTracking(playerName, gameName);
        } catch (Exception exception) {
            return new String("Exception while executing saveGameTracking: " + exception.getMessage());
        }
        
        return "success";
    }
}
