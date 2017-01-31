package com.controller;

import com.model.Users;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Edvard Piri on 26.01.2017.
 */


@Controller
public class UserController {
    @Autowired
    UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }


    @RequestMapping("/register-request")
    public ResponseEntity<String> registerUser(HttpSession session, @ModelAttribute Users user) {
        Users checkUser = userService.register(user);
        if (checkUser == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Users.setCurrent(session, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/login-request")
    public ResponseEntity<String> registerUser(HttpSession session, @RequestParam String email, @RequestParam String password) {
//        if(curUser == null)
//            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
//        //userService.getByEmailAndPassword

//        Users.setCurrent(session, user);
        Users curUser = userService.login(email, password);
        if (curUser == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Users.setCurrent(session, curUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping("/log-out-request")
    public ResponseEntity<String> logout(HttpSession session) {
        Users.setCurrent(session, null);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("newUser.vm");
    }

    @RequestMapping(name = "/")
    public ModelAndView index() {
        return new ModelAndView("index.vm");
    }

    //interceptor
}
