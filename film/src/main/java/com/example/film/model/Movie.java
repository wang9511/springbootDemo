package com.example.film.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data

public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    private Integer hot;

    @Column(name = "image_name")
    private String imageName;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(length = 20)
    private String name;

    public Integer getHot() {
        return hot;
    }

    public String getImageName() {
        return imageName;
    }

    public String getName() {
        return name;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    //    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "publish_date")
    private Date publishDate; // 发布日期

    private String title;
    private String content;
}
