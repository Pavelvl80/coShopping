package com.model;

import java.util.Date;

//TODO create db table with mapping
public class Order {
    private Long id;

    //TODO many-to-one
    private Ad ad;

    //TODO many-to-one
    private Users user;

    private Integer itemsCount;
    private Date dateCreated;

    public Order() {
    }


    public Order(Integer itemsCount, Date dateCreated) {
        this.itemsCount = itemsCount;
        this.dateCreated = dateCreated;
    }
}
