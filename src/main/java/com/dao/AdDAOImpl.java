package com.dao;

import com.model.Ad;
import com.model.Users;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Edvard Piri on 28.01.2017.
 */
@Repository
@Transactional
public class AdDAOImpl<T> extends AbstractDAOImpl<Ad> implements AdDAO {

    @Override
    public List<Ad> getAllByUserId(Long id) {
        String hql = "from Ad t where t.id = :id";
        Query query = getSession().createQuery(hql);
        query.setParameter("id", id);
        return query.list();
    }
}

