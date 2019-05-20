package com.ddd.movie.pojo;

import javax.persistence.*;

@Entity
@Table(name = "property")
public class Property {
    private int id;
    private String name;
    private PropertyValue value;

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

    @OneToOne
    @JoinColumn(name = "pid", referencedColumnName = "id")
    public PropertyValue getValue() {
        return value;
    }

    public void setValue(PropertyValue value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Property property = (Property) o;

        if (id != property.id) return false;
        if (name != null ? !name.equals(property.name) : property.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
