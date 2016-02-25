/**
 * 
 */
package com.comeon.assignment.representations;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents a Player
 */
@Entity
@Table(name = "GameTracking")
public class GameTracking implements Serializable {
    private static final long serialVersionUID = 1057902020595934835L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;
    @Column(name = "gameId")
    private Long gameId;

    @Column(name = "playerId")
    private Long playerId;

    public GameTracking() {
    }

    public GameTracking(Long gameId, Long playerId) {
        this.gameId = gameId;
        this.playerId = playerId;
    }

    @JsonProperty
    public Long getId() {
        return id;
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
