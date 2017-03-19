package com.service;

import com.dao.OrderDAO;
import com.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Edvard Piri on 19.03.2017.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDAO orderDAO;

    @Override
    public Order save(Order order) {
        return orderDAO.save(order);
    }
}
