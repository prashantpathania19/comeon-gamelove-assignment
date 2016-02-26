/**
 * 
 */
package com.comeon.assignment.representations;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * This class is a composite key for GameTracking
 * @author Prashant Pathania
 */
@Embeddable
public class GameTrackingComposite implements Serializable {
    private static final long serialVersionUID = -535636652376023945L;
    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "player")
    private Player player;

    public GameTrackingComposite() {}

    public GameTrackingComposite(Game game, Player player) {
        this.game = game;
        this.player = player;
    }

    /**
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * @param game the game to set
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
}
