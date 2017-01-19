package com.model;

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
    private Integer isActive;

    private Users owner;
    private List<Users> participants;

    public Ad() {
    }

    @Id
    @SequenceGenerator(name = "AD_SEQ", sequenceName = "AD_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AD_SEQ")
    public Long getId() {
        return id;
    }

    @Column
    public String getItemName() {
        return itemName;
    }

    @Column
    public Integer getTotalPrice() {
        return totalPrice;
    }

    @Column
    public String getCity() {
        return city;
    }

    @Column
    public Date getDateExpires() {
        return dateExpires;
    }

    @Column
    public Date getDateCreated() {
        return dateCreated;
    }

    @Column
    public Date getDateEdited() {
        return dateEdited;
    }

    @Column
    public Integer getIsActive() {
        return isActive;
    }

    @ManyToOne
    @JoinColumn(name = "CREATOR_ID")
    public Users getOwner() {
        return owner;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
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

    public void setIsActive(Integer isActive) {
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
