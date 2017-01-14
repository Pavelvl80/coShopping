package com.testcontoller;

import com.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by Edvard Piri on 13.01.2017.
 */
@Controller
public class TestController {

    @Autowired
    TestDAO testDAO;

    @RequestMapping(value = "/")
    public ModelAndView index() {
        return new ModelAndView("index.vm");
    }

    @RequestMapping(value = "/newUser")
    public ModelAndView newUser() {
        Date date = new Date();
        Users user = new Users("Lotar", "Hagen", "lotar@mail.ru", "+380312323", date, "horinis");
        ModelAndView modelAndView = new ModelAndView("testUser.vm");
        testDAO.save(user);

        modelAndView.addObject("result", user.getFirstName());
        return modelAndView;
    }

    @RequestMapping(value = "/showUsers")
    public ModelAndView showUser() {
        ModelAndView modelAndView = new ModelAndView("testShowUsers.vm");
        List<Users> users = testDAO.getUsers();

        modelAndView.addObject("result", users);

        return modelAndView;
    }
}
