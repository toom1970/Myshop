package com.ddd.movie.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cinema")
public class Cinema implements Serializable {
    private int id;
    private String name;
    private Area area;
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

    @Transient
    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
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

    public Cinema(String name, String contactNumber, String service) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.service = service;
    }

    public Cinema() {
        this.area = new Area();
        this.service = "3D眼镜";
    }

    public Cinema(int id, String name, Area area) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.service = "3D眼镜";
    }
}
