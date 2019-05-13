package com.ddd.movie.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role")
public class Role implements Serializable {
    private int id;
    private String roleValue;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "value")
    public String getRoleValue() {
        return roleValue;
    }

    public void setRoleValue(String roleValue) {
        this.roleValue = roleValue;
    }
}
