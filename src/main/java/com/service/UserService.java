package com.service;

import com.model.Ad;
import com.model.Users;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by Edvard Piri on 28.01.2017.
 */
public interface UserService {
    Users register(Users user) throws Exception;

    Users login(String email, String pass);

    Users getByEmail(String email);

    Users getUserById(Long Id);

    Users save(Users user);


}
