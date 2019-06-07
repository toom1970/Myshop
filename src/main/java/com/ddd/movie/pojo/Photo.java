package com.ddd.movie.pojo;

import javax.persistence.*;

@Entity
@Table(name = "photo")
public class Photo {
    private int id;
    private String url;
    private int mid;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "photo")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "mid")
    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public Photo(String url,int mid) {
        this.url = url;
        this.mid = mid;
    }

    public Photo() {
    }
}
