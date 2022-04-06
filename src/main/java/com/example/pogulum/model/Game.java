package com.example.pogulum.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "twitch_Game")
public class Game {

    @Id
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="box_art_url")
    private String box_art_url;


}
