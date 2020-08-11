package com.example.film.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/*
movie表和movie_detail表关联 查询结果
 */

@Data
public class MovieAndDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="content")
    private String content;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "name")
    private String name;

    @Column(name = "publish_date")
    private Date publishDate;

    @Column(name = "info")
    private String info;

    @Column(name = "url")
    private String url;

    @Column(name = "website_id")
    private Integer websiteId;

    @Column(name = "movie_id")
    private Integer movieId;
}
