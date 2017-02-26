package com.controller;

import com.model.Users;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Edvard Piri on 26.01.2017.
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }


    @RequestMapping("/register-request")
    public String registerUser(HttpSession session, @ModelAttribute Users user) throws Exception {
        Users checkUser = userService.register(user);
        if (checkUser == null)
            throw new Exception("User exist");
        Users.setCurrent(session, user);
        return "redirect:/";
    }

    @RequestMapping("/login-request")
    public ResponseEntity<String> login(HttpSession session, @RequestParam String email, @RequestParam String password) {
        if(Users.Current(session) != null)
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        Users curUser = userService.login(email, password);
        if (curUser == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Users.setCurrent(session, curUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping("/log-out")
    public String logout(HttpSession session) {
        Users.setCurrent(session, null);
        return "redirect:/user/";
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ModelAndView userProfile(@PathVariable String userId) throws Exception {
        Users user = userService.getUserById(Long.parseLong(userId));
        if (user == null)
            throw new Exception();
        ModelAndView modelAndView = new ModelAndView("UserProfile.vm");
        modelAndView.addObject("user", user);
        return modelAndView;
    }


    @RequestMapping(value = "/edit{userId}", method = RequestMethod.GET)
    public ModelAndView editUser(@PathVariable String userId) {
        Users user = userService.getUserById(Long.valueOf(userId));
        ModelAndView modelAndView = new ModelAndView("editUser.vm");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/edit{userId}", method = RequestMethod.POST)
    public String editUserRequest(@ModelAttribute Users newUser, @PathVariable String userId) {
        //TODO get old user from cash
        Users user = userService.getUserById(Long.valueOf(userId));
        newUser.setId(user.getId());
        newUser.setAdsPublished(user.getAdsPublished());
        newUser.setAdsJoined(user.getAdsJoined());
        newUser.setFriends(user.getFriends());
        if (newUser.equals(user))
            return "redirect:/user/edit";
        userService.save(newUser);
        return "redirect:/user/edit" + newUser.getId();
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login.vm");
    }

    @RequestMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("newUser.vm");
    }


    //interceptor
}
