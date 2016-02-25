/**
 * 
 */
package com.comeon.assignment.representations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents a Player
 */
@Entity
@Table(name = "Game")
public class Game {
    @Id
    @GeneratedValue
    @Column(name="gameId")
    private Long id;
    @Column(name = "game_name")
    private String name;

    public Game() {
    }

    public Game(String name) {
        this.name = name;
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