package com.ddd.movie.pojo;

import java.io.Serializable;
import java.util.Date;

public class ReleaseInfo implements Serializable {
    private int id;
    private Movie movie;
    private Cinema cinema;
    private Date time;
    private double price;
    private int remainTicket;

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
