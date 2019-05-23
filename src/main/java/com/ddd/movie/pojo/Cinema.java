package com.ddd.movie.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cinema")
public class Cinema implements Serializable {
    private int id;
    private String name;
    private String area;
    private String contactNumber;
    private String service;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "area")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Column(name = "contactnumber")
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Column(name = "cinemaservice")
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
