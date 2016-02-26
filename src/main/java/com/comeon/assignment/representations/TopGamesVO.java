/**
 * 
 */
package com.comeon.assignment.representations;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents topGames
 * @author Prashant Pathania
 *
 */
public class TopGamesVO {
    @JsonProperty
    private Long likes;
    @JsonProperty
    private String gameName;

    public TopGamesVO() {
        super();
    }

    /**
     * Constructor with parameters
     * @param long
     * @param string
     */
    public TopGamesVO(Long likes, String gameName) {
        super();
        this.likes = likes;
        this.gameName = gameName;
    }
    /**
     * @return the likes
     */
    public Long getLikes() {
        return likes;
    }
    /**
     * @param likes the likes to set
     */
    public void setLikes(Long likes) {
        this.likes = likes;
    }
    /**
     * @return the gameName
     */
    public String getGameName() {
        return gameName;
    }
    /**
     * @param gameName the gameName to set
     */
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
