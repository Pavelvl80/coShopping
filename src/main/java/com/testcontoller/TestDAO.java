package com.testcontoller;

import com.model.Users;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Edvard Piri on 13.01.2017.
 */

@Repository
@Transactional
public class TestDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public Users save(Users user) {
        if (user.getId() == null)
            entityManager.persist(user);
        else entityManager.merge(user);
        return user;
    }

    public List<Users> getUsers() {
        String sql = "from Users t";

        Query query = getSession().createQuery(sql);

        return query.list();
    }
}
