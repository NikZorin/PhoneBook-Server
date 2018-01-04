package com.norrissim.phonebook.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by User on 31.12.2017.
 */

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String phone;

    private String image;

    public Contact(String name, String phone, String image) {
        this.name = name;
        this.phone = phone;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
