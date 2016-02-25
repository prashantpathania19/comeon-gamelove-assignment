/**
 * 
 */
package com.comeon.assignment.representations;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is a composite key for GameTracking
 */
@Embeddable
public class GameTrackingComposite implements Serializable {
    private static final long serialVersionUID = -535636652376023945L;
    @Column(name = "gameId")
    private Long gameId;

    @Column(name = "playerId")
    private Long playerId;

    public GameTrackingComposite() {}

    public GameTrackingComposite(Long gameId, Long playerId) {
        this.gameId = gameId;
        this.playerId = playerId;
    }

    /**
     * @return the gameId
     */
    @JsonProperty
    public Long getGameId() {
        return gameId;
    }

    /**
     * @param gameId the gameId to set
     */
    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    /**
     * @return the playerId
     */
    @JsonProperty
    public Long getPlayerId() {
        return playerId;
    }

    /**
     * @param playerId the playerId to set
     */
    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
}
