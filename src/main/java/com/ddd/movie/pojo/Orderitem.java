package com.ddd.movie.pojo;

import javax.persistence.*;

@Entity
@Table(name = "orderitem", schema = "shop", catalog = "")
public class Orderitem {
    private int id;
    private Integer number;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
