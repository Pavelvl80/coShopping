package com.dao;

import com.model.BaseEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Edvard Piri on 28.01.2017.
 */

@Repository
@Transactional
public class AbstractDAOImpl<T extends BaseEntity> implements AbstractDAO<T> {


    @PersistenceContext
    public EntityManager entityManager;


    @Override
    public T save(T t) {
        if (t.getId() == null)
            entityManager.persist(t);
        else entityManager.merge(t);
        return t;
    }


    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }
}
