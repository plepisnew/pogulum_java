package com.example.pogulum.model;

import lombok.Data;
import org.apache.jasper.tagplugins.jstl.core.Url;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "Clips")
public class Clip {

    @Column(name = "broadcaster_id")
    private long broadcaster_id;

    @Column(name = "broadcaster_name")
    private String broadcaster_name;

    @Column(name = "created_at")
    private Date created_at;

@Column(name = "creator_id")
    private long creator_id;

    @Column(name = "creator_name")
    private String creator_name;

    @Column(name = "duration")
    private double duration;

    @Column(name = "embed_url")
    private String embed_url;

    @Column(name = "game_id")
    private long game_id;

    @Id
    private String id;

    @Column(name = "language")
    private String language;

    @Column(name = "thumbnail_url")
    private String thumbnail_url;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;


    @Column(name = "video_id")
    private String video_id;

    @Column(name = "view_count")
    private Long view_count;

}
