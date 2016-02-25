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
@Table(name = "Player")
public class Player {
    @Id
    @GeneratedValue
    @Column(name="playerId")
    private Long id;
    @Column(name = "first_name")
    private String name;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
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