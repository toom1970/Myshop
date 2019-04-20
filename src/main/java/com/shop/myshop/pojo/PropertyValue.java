package com.shop.myshop.pojo;

import javax.persistence.*;

@Entity
@Table(name = "propertyvalue", schema = "shop")
public class PropertyValue {
    private int id;
    private String value;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
