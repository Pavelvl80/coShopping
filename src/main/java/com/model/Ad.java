package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Edvard Piri on 25.12.2016.
 */
//TODO refactor db table
@Entity
@Table(name = "AD")
public class Ad extends BaseEntity {
    private Long id;
    private String itemName;
    private Integer itemsCount;
    private Double pricePerItem;
    private Integer itemsReserved;

    private Date dateExpires;
    private Date dateCreated;
    private Date dateEdited;
    private Long isActive;

    private Supplier supplier;

    @JsonIgnore
    private Users owner;

    @JsonIgnore
    private Set<Order> orders;

    public Ad() {
        this.dateExpires = new Date();
        this.dateCreated = new Date();

        this.isActive = 1l;
    }

    //TODO last home work
    public Ad(String itemName, Integer itemsCount, Double pricePerItem, Integer itemsReserved) {
        this.itemName = itemName;
        this.itemsCount = itemsCount;
        this.pricePerItem = pricePerItem;
        this.itemsReserved = itemsReserved;
    }

    @JsonIgnore
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

    @Column(name = "ITEMS_COUNT")
    public Integer getItemsCount() {
        return itemsCount;
    }

    @Column(name = "PRICE_PER_ITEM")
    public Double getPricePerItem() {
        return pricePerItem;
    }

    @Column(name = "ITEMS_RESERVED")
    public Integer getItemsReserved() {
        return itemsReserved;
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

    @OneToMany(targetEntity = Order.class, mappedBy = "ad", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Order> getOrders() {
        return orders;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemsCount(Integer itemsCount) {
        this.itemsCount = itemsCount;
    }

    public void setPricePerItem(Double pricePerItem) {
        this.pricePerItem = pricePerItem;
    }

    public void setItemsReserved(Integer itemsReserved) {
        this.itemsReserved = itemsReserved;
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

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", dateExpires=" + dateExpires +
                ", dateCreated=" + dateCreated +
                ", dateEdited=" + dateEdited +
                ", isActive=" + isActive +
                ", owner=" + owner +
                ", orders=" + orders +
                '}';
    }
}
