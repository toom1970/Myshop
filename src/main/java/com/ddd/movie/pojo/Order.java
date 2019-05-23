package com.ddd.movie.pojo;


import java.io.Serializable;

public class Order implements Serializable {
    private long id;
    private ReleaseInfo releaseInfo;
    private User user;
    private int number;
    private double price;
    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ReleaseInfo getReleaseInfo() {
        return releaseInfo;
    }

    public void setReleaseInfo(ReleaseInfo releaseInfo) {
        this.releaseInfo = releaseInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Order(long id, ReleaseInfo releaseInfo, User user, int number, int status) {
        this.id = id;
        this.releaseInfo = releaseInfo;
        this.user = user;
        this.number = number;
        this.price = releaseInfo.getPrice() * number;
        this.status = status;
    }

    public Order() {
    }
}
