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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents a Player
 */
@Entity
@Table(name = "Game")
@NamedQueries({
    @NamedQuery(name = "game.findGameByIds", query = "from Game game where game.id IN (:ids)"),
    @NamedQuery(name = "game.findGameByName", query = "from Game game where game.name = :name")
})
public class Game implements Serializable {
    private static final long serialVersionUID = 5916771721192214458L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gameId")
    private Long id;
    @Column(name = "game_name")
    private String name;

    public Game() {
    }

    public Game(String name) {
        this.name = name;
    }

    public Game(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}