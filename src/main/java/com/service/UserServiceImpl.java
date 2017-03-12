package com.service;

import com.dao.AbstractDAOImpl;
import com.dao.AdDAO;
import com.dao.UserDAO;
import com.model.Ad;
import com.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Edvard Piri on 28.01.2017.
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public Users register(Users user) throws Exception {
        if (user == null)
            throw  new Exception("User equals null");
        Users checkUser = userDAO.getByEmail(user.getEmail());
        if (checkUser != null)
            throw new Exception("User exist");
        userDAO.save(user);
        return user;
    }

    @Override
    public Users login(String email, String pass) {
        return userDAO.getByEmailAndPassword(email, pass);
    }

    @Override
    public Users getByEmail(String email) {
        if (email.equals(null) || email == "")
            return null;
        return userDAO.getByEmail(email);
    }

    @Override
    public Users getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public Users save(Users user) {
        if(user == null)
            return null;
        return userDAO.save(user);
    }
}
