package com.example.film.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "manager")
//@Document(indexName = "spring_boot_movie")
public class Manager implements Serializable {

    private  static final long serialVersionUID = -1L;

    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    //使用分词器, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word"
    //@Field(type = FieldType.Text)
    @Column(name = "username")
    public String username;

    //@Field(type = FieldType.Text)
    @Column(name = "password")
    public String password;

    public Manager(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString(){
        return this.username + this.password;
    }
}
