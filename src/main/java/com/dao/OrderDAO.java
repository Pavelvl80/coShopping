package com.dao;

import com.model.Order;

/**
 * Created by Edvard Piri on 19.03.2017.
 */
public interface OrderDAO {
    Order save(Order order);
}
