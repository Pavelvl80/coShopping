package com.service;

import com.dao.UserDAO;
import com.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Edvard Piri on 28.01.2017.
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public Users register(Users user) {
        if (user == null)
            return null;

        Users checkUser = userDAO.getByEmailOrUserName(user.getEmail(), user.getUserName());

        if (checkUser != null)
            return null;

        userDAO.save(user);
        return user;
    }

    @Override
    public Users login(String email, String pass) {
        return userDAO.getByEmailAndPassword(email, pass);
    }
}
