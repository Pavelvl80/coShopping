package com.model;

import javax.persistence.*;
import java.util.Date;

//TODO create db table with mapping
@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity{
    private Long id;

    private Ad ad;
    private Users user;

    private Integer itemsCount;
    private Date dateCreated;

    public Order() {
    }


    public Order(Ad ad, Users user, Integer itemsCount, Date dateCreated) {
        this.ad = ad;
        this.user = user;
        this.itemsCount = itemsCount;
        this.dateCreated = dateCreated;
    }

    @Id
    @SequenceGenerator(name = "ORDERS_SEQ", sequenceName = "ORDERS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDERS_SEQ")
    public Long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "AD_ID")
    public Ad getAd() {
        return ad;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public Users getUser() {
        return user;
    }

    @Column(name = "ITEMS_COUNT")
    public Integer getItemsCount() {
        return itemsCount;
    }

    @Column(name = "DATE_CREATED")
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setItemsCount(Integer itemsCount) {
        this.itemsCount = itemsCount;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
