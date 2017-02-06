package com.dao;


import com.model.Users;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Edvard Piri on 28.01.2017.
 */

@Repository
@Transactional
public class UserDAOImpl extends AbstractDAOImpl<Users> implements UserDAO {

    @Override
    public Users getByEmail(String email) {
        String hql = "from Users t where t.email = :email";
        Query query = getSession().createQuery(hql);
        query.setParameter("email", email);
        return (Users) query.uniqueResult();
    }

    @Override
    public Users getByEmailAndPassword(String email, String password) {
        String hql = "from Users t where t.email = :email and t.password = :password";
        Query query = getSession().createQuery(hql);
        query.setParameter("email", email);
        query.setParameter("password", password);
        return (Users) query.uniqueResult();
    }

}
