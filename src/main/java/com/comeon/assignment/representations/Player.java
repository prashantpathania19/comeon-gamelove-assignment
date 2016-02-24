/**
 * 
 */
package com.comeon.assignment.representations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * This class represents a Player
 */
@Entity
@Table(name = "Player")
@SequenceGenerator(name = "SEQ_SOME_TABLE", sequenceName = "SEQ_SOME_TABLE")
public class Player {
    @Id
    @GeneratedValue(generator = "SEQ_SOME_TABLE")
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}