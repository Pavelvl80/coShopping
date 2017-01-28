package com.dao;

import com.model.Users;

/**
 * Created by Edvard Piri on 28.01.2017.
 */
public interface UserDAO {
     Users getByEmailOrUserName(String email, String userName);

     Users getByEmailAndPassword(String email, String password);

     Users save(Users user);
}
