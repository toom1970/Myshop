package com.ddd.movie.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "category", schema = "shop")
public class Tag implements Serializable {
    private int id;
    private String name;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }
}
