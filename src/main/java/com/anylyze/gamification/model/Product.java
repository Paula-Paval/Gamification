package com.anylyze.gamification.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "reward")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "value")
    private Long value;
    @Column(name = "imageurl")
    private String imageurl;

    public Product() {
    }

    public Product(String name, String description, Long credits, String imageurl) {
        this.name = name;
        this.description = description;
        this.value = credits;
        this.imageurl = imageurl;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCredits() {
        return value;
    }

    public void setCredits(Long credits) {
        this.value = credits;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                Objects.equals(value, product.value) &&
                Objects.equals(imageurl, product.imageurl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, value, imageurl);
    }


}
