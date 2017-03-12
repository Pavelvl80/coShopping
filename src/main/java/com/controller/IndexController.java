package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Edvard Piri on 25.02.2017.
 */
@Controller
public class IndexController {
    @RequestMapping(name = "/")
    public ModelAndView index() {
        return new ModelAndView("index.vm");
    }
}
