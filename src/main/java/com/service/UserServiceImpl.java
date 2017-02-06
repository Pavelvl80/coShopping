package com.service;

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

    @Autowired
    private AdDAO adDAO;

    @Override
    public Users register(Users user) {
        if (user == null)
            return null;

        Users checkUser = userDAO.getByEmail(user.getEmail());

        if (checkUser != null)
            return null;

        userDAO.save(user);
        return user;
    }

    @Override
    public Users login(String email, String pass) {
        return userDAO.getByEmailAndPassword(email, pass);
    }

    @Override
    public Users findByEmail(String email) {
        if (email.equals(null) || email == "")
            return null;
        return userDAO.getByEmail(email);
    }

    @Override
    public List<Ad> getAllAdsByOwnerEmail(Users user) {
        if (user == null)
            return null;
        List<Ad> ads = adDAO.getAllByUserId(user.getId());
        return ads;
    }

    @Override
    public Ad getExpensiveAd(List<Ad> ad) {
        return null;
    }

    @Override
    public Ad getCheapestAd(List<Ad> ad) {
        return null;
    }
}
