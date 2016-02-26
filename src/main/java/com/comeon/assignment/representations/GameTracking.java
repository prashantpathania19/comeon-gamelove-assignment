/**
 * 
 */
package com.comeon.assignment.representations;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents a Player
 */
@Entity
@Table(name = "GameTracking")
@NamedQueries({
    @NamedQuery(name = "tracking.listTopGames", query = "select count(1) as likes, gt.gameTrackingComposite.gameId from GameTracking gt group by gameId order by likes DESC"),
    @NamedQuery(name = "tracking.listTracking", query = "from GameTracking")
})
public class GameTracking implements Serializable {
    private static final long serialVersionUID = 1057902020595934835L;
    @EmbeddedId
    private GameTrackingComposite gameTrackingComposite;

    public GameTracking() {
    }

    public GameTracking(GameTrackingComposite gameTrackingComposite) {
        this.gameTrackingComposite = gameTrackingComposite;
    }

    /**
     * @return the gameTrackingComposite
     */
    @JsonProperty
    public GameTrackingComposite getGameTrackingComposite() {
        return gameTrackingComposite;
    }

    /**
     * @param gameTrackingComposite the gameTrackingComposite to set
     */
    public void setGameTrackingComposite(GameTrackingComposite gameTrackingComposite) {
        this.gameTrackingComposite = gameTrackingComposite;
    }

    
}
