package com.model;

import oracle.sql.DATE;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    private String itemName;
    private Integer totalPrice;
    private String city;
    private Date dateExpires;
    private Date dateCreated;
    private Date dateEdited;
    private Long isActive;

    private Users owner;
    private List<Users> participants;

    public Ad() {
    }

    public Ad(String itemName, Integer totalPrice, String city, Users owner, List<Users> participants) {
        this.itemName = itemName;
        this.totalPrice = totalPrice;
        this.city = city;
        this.owner = owner;
        this.participants = participants;

        this.dateExpires = new Date();
        this.dateCreated = new Date();
        this.dateEdited = new Date();
        this.isActive = 0l;
    }

    @Id
    @SequenceGenerator(name = "AD_SEQ", sequenceName = "AD_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AD_SEQ")
    public Long getId() {
        return id;
    }

    @Column(name = "ITEM_NAME")
    public String getItemName() {
        return itemName;
    }

    @Column(name = "TOTAL_PRICE")
    public Integer getTotalPrice() {
        return totalPrice;
    }

    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    @Column(name = "DATE_EXPIRES")
    public Date getDateExpires() {
        return dateExpires;
    }

    @Column(name = "DATE_CREATED")
    public Date getDateCreated() {
        return dateCreated;
    }

    @Column(name = "DATE_EDITED")
    public Date getDateEdited() {
        return dateEdited;
    }

    @Column(name = "IS_ACTIVE")
    public Long getIsActive() {
        return isActive;
    }

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    public Users getOwner() {
        return owner;
    }

    @OneToMany(targetEntity = Users.class, mappedBy = "id", cascade = CascadeType.ALL)
    public List<Users> getParticipants() {
        return participants;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDateExpires(Date dateExpires) {
        this.dateExpires = dateExpires;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateEdited(Date dateEdited) {
        this.dateEdited = dateEdited;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }

    public void setParticipants(List<Users> participants) {
        this.participants = participants;
    }

    //create new project
    //use the same spring config as in one
    //use the same maven - pom, just project name
    //build with maven
}
