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
@Table(name = "Player")
@NamedQueries({
    @NamedQuery(name = "player.findPlayer", query = "from Player player where player.name = :name")
})
public class Player implements Serializable {
    private static final long serialVersionUID = 1923678106676908710L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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