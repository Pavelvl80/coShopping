package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Edvard Piri on 04.02.2017.
 */
@Controller
public class Show {
    @Autowired
    @Qualifier("test")
    BeanExample beanExample;

    @RequestMapping("/show")
    public ModelAndView show() {
        beanExample.setName("ssss");

        System.out.println(beanExample.getName());

        return new ModelAndView("index.vm");
    }
}
