package com.controller;

import com.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by Edvard Piri on 25.02.2017.
 */
@Controller
public class HomeController {
//    @RequestMapping(name = "/")
//    public ModelAndView index() {
//        return new ModelAndView("index.vm");
//    }

    @RequestMapping(name = "/home")
    public String home(HttpSession session) {
        Users curUser = Users.current(session);
        if(curUser == null)
            return "redirect:/user/login";
        return "redirect:/user/" + curUser.getId();
    }
}
