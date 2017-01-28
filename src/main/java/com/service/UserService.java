package com.service;

import com.model.Users;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Edvard Piri on 28.01.2017.
 */
public interface UserService {
    Users register(Users user);

    Users login(String email, String pass);
}
