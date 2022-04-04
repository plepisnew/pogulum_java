package com.example.pogulum.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Data
@Entity
@Table(name = "twitch_user")
public class User {

    @Column(name="broadcaster_type")
    private String broadcasterType;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="description")
    private String description;

    @Column(name="display_name")
    private String displayName;

    @Id
    private long id;

    @Column(name="login")
    private String login;

    @Column(name="offline_image")
    private String offlineImage;

    @Column(name="profile_image")
    private String profileImage;

    @Column(name="account_type")
    private String type;

    @Column(name="view_count")
    private int view_count;



}
