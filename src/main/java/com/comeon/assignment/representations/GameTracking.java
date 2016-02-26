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

/**
 * This class represents a Player
 */
@Entity
@Table(name = "GameTracking")
@NamedQueries({
    @NamedQuery(name = "tracking.listTopGames", query = "select count(1) as likes, gt.gameTrackingComposite.game.id, gt.gameTrackingComposite.game.name " + 
                                                        "from GameTracking as gt group by gt.gameTrackingComposite.game.id order by likes DESC"),
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
