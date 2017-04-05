package com.controller;

import com.dao.TestDAO;
import com.model.Users;
import com.service.UserService;
import com.test.JUnitCoreAndMavenCmdTestClass;
import com.test.MavenInvokerTest;
import com.testJunitCoreRunner.JUnitCoreRunnerExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by Edvard Piri on 13.02.2017.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    //    @Autowired
//    private TestDAO TestDAO;
//
//    @Autowired
//    private UserService userService;
//
//    @RequestMapping("/test-put-db")
//    public ModelAndView testPutDb() throws Exception {
//        Long beforeTime = System.currentTimeMillis();
//        Long result = TestDAO.testPutDb(userService.getByEmail("lotar@smail.ru"));
//        ModelAndView modelAndView = new ModelAndView("test/test.vm");
//        modelAndView.addObject("allTime", System.currentTimeMillis() - beforeTime);
//        modelAndView.addObject("DbTime", result);
//        return modelAndView;
//    }
//
//    @RequestMapping("/test-get-db")
//    public ModelAndView testGetDb() throws Exception {
//        Long beforeTime = System.currentTimeMillis();
//        Long result = TestDAO.testGetDb();
//        ModelAndView modelAndView = new ModelAndView("test/test.vm");
//        modelAndView.addObject("allTime", System.currentTimeMillis() - beforeTime);
//        modelAndView.addObject("DbTime", result);
//        return modelAndView;
//    }
//
//    @RequestMapping("test-session")
//    public String testSession(HttpServletRequest request) {
//        if (Users.current(request.getSession()) == null)
//            return "redirect:/user/login";
//        return "redirect:/user/" + Users.current(request.getSession()).getId();
//    }
    @RequestMapping("/cmdTest")
    public ModelAndView testCall() throws Exception {
        JUnitCoreAndMavenCmdTestClass cmdTest = new JUnitCoreAndMavenCmdTestClass();
        cmdTest.mavenCmdTest();
//       cmdTest.jUnitTest();
//        cmdTest.mavenInvokerTest();
        ModelAndView modelAndView = new ModelAndView("index.vm");
        return modelAndView;
    }

    @RequestMapping("/call-invoker")
    public ModelAndView testCallInvoker() throws Exception {
        JUnitCoreAndMavenCmdTestClass mavenInvokerTest = new JUnitCoreAndMavenCmdTestClass();
        File file = new File("C:\\Users\\Edvard Piri\\IdeaProjects\\enterpriseChecker");
        MavenInvokerTest mit = new MavenInvokerTest();
        mit.publishSite();
        ModelAndView modelAndView = new ModelAndView("index.vm");
        return modelAndView;
    }

    @RequestMapping("/junit")
    public ModelAndView JUnitTest() throws Exception {
        JUnitCoreRunnerExample junitRunner = new JUnitCoreRunnerExample();
        junitRunner.jUnitTest();
        ModelAndView modelAndView = new ModelAndView("index.vm");
        return modelAndView;
    }

}
