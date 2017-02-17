package com.dao;

import com.dao.AbstractDAOImpl;
import com.dao.AdDAO;
import com.model.Ad;
import com.model.Users;
import com.service.UserService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.internal.SessionImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

/**
 * Created by Edvard Piri on 13.02.2017.
 */
@Repository
@Transactional
public class TestDAOImpl extends AbstractDAOImpl<Ad> implements TestDAO {

//    @Autowired
//    private AdDAO adDAO;

    public Long testPutDb(Users user) throws Exception {
        Long beforeTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            Long random = (long) Math.random() * 1000 + 10;
            Ad ad = new Ad("ITEM", random, "CITY_TEST", user);
            //String itemName, Integer totalPrice, String city, Users owner, Users participants
            save(ad);
            if (i % 20 == 0) {
                getSession().flush();
                getSession().clear();
            }
        }
        long afterTime = System.currentTimeMillis();
//        getSession().close();
        return afterTime - beforeTime;
    }

    public Long testGetDb() {
        Long beforeTime = System.currentTimeMillis();
        String hql = "from Ad t where t.owner = 1102";
        Query query = getSession().createQuery(hql);
        List<Ad> list = query.list();
        Long afterTime = System.currentTimeMillis();
        return afterTime - beforeTime;
    }
}
