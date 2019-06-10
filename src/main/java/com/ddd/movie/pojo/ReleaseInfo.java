package com.ddd.movie.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.beans.Transient;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReleaseInfo implements Serializable {
    private int id;
    private Movie movie;
    private Cinema cinema;
    private Date time;
    private double price;
    private int remainTicket;
    private String type;
    private String th;
    private String lang;

    public ReleaseInfo(Movie movie, Date time, double price, String type, String th, String lang) {
        this.movie = movie;
        this.cinema = new Cinema();
        this.time = time;
        this.price = price;
        this.type = type;
        this.th = th;
        this.lang = lang;
        this.remainTicket = 0;
    }

    public ReleaseInfo(Movie movie, Date time, String type, String th, String lang) {
        this.movie = movie;
        this.cinema = new Cinema();
        this.time = time;
        this.type = type;
        this.th = th;
        this.price = 0;
        this.lang = lang;
        this.remainTicket = 0;
    }

    public ReleaseInfo() {
    }

    public ReleaseInfo(Cinema cinema, Date time, double price, String type, String th, String lang) {
        this.movie = new Movie();
        this.cinema = cinema;
        this.time = time;
        this.price = price;
        this.type = type;
        this.th = th;
        this.lang = lang;
        this.remainTicket = 0;
    }

    @Transient
    @JsonIgnore
    public String getDt() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(time);
    }

    @Transient
    @JsonIgnore
    public String getTm() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(time);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTh() {
        return th;
    }

    public void setTh(String th) {
        this.th = th;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRemainTicket() {
        return remainTicket;
    }

    public void setRemainTicket(int remainTicket) {
        this.remainTicket = remainTicket;
    }
}
