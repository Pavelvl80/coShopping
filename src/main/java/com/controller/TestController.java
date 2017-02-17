package com.controller;

import com.dao.TestDAO;
import com.service.UserService;
import com.dao.TestDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Edvard Piri on 13.02.2017.
 */
@Controller
public class TestController {
    @Autowired
    TestDAO TestDAO;

    @Autowired
    UserService userService;

    @RequestMapping("/test-put-db")
    public ModelAndView testPutDb() throws Exception{
        Long beforeTime = System.currentTimeMillis();
        Long result = this.TestDAO.testPutDb(this.userService.findByEmail("lotar@smail.ru"));
        ModelAndView modelAndView = new ModelAndView("test/test.vm");
        modelAndView.addObject("allTime", System.currentTimeMillis() - beforeTime);
        modelAndView.addObject("DbTime", result);
        return modelAndView;
    }

    @RequestMapping("/test-get-db")
    public ModelAndView testGetDb() throws Exception{
        Long beforeTime = System.currentTimeMillis();
        Long result = this.TestDAO.testGetDb();
        ModelAndView modelAndView = new ModelAndView("test/test.vm");
        modelAndView.addObject("allTime", System.currentTimeMillis() - beforeTime);
        modelAndView.addObject("DbTime", result);
        return modelAndView;
    }

}
