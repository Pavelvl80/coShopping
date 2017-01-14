package com.model;

import javax.persistence.*;

/**
 * Created by Edvard Piri on 25.12.2016.
 */
//TODO create new db
//TODO create table in db
//TODO move to hibernate
@Entity
@Table(name = "AD")
public class Ad {
    private Long id;
    private Users creator;
    private Users user;

    public Ad() {
    }


    @Id
    @SequenceGenerator(name = "AD_SEQ", sequenceName = "AD_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AD_SEQ")
    public Long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "CREATOR_ID")
    public Users getCreator() {
        return creator;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public Users getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreator(Users creator) {
        this.creator = creator;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    //create new project
    //use the same spring config as in one
    //use the same maven - pom, just project name
    //build with maven
}
