package com.controller;

import com.model.Users;
import com.service.UserService;
import com.validators.UsersValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Edvard Piri on 26.01.2017.
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    private static final Logger logger = Logger.getLogger(UserController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @RequestMapping("/register")
    public ModelAndView registerUser(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("registerUser.vm");
        modelAndView.addObject("errors", request.getSession().getAttribute("errors"));
        return modelAndView;
    }

    @RequestMapping("/register-request")
    public String registerUser(HttpSession session, @ModelAttribute @Valid Users user, BindingResult result, UsersValidator usersValidator/*, RedirectAttributes redirectAttributes*/) throws Exception {
        //PropertyConfigurator configurator = new PropertyConfigurator();
        //configurator.doConfigure("log4j.properties");


        //Validation code
        usersValidator.validate(user, result);

//        check error here
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            Map<String, String> errorsMap = new HashMap<>();
            for (ObjectError error : errors) {
                errorsMap.put(error.getObjectName(), error.getDefaultMessage());
            }
            session.setAttribute("errors", errorsMap);
//                redirectAttributes.addAllAttributes(errorsMap);

            return "redirect:/user/register";
        }

        logger.info("SessionId: " + session + "; /register-request... for user " + user + " started");

        Users existingUser = userService.getByEmail(user.getEmail());
        if (existingUser != null) {
            logger.info("SessionId: " + session + "; /register-request... User exists...");
            throw new Exception("User exists...");
        }

        try {
            Users users = userService.register(user);
            if (users == null)
                throw new Exception();
        } catch (Exception e) {
            throw new Exception("Server error. Try agai¢n later please...");
        }

        Users.setCurrent(session, user);

        logger.info("SessionId: " + session + "; /register-request... for user " + user + " successful");
        return "redirect:/";
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login.vm");
    }

    @RequestMapping("/login-request")
    public ResponseEntity<String> login(HttpSession session, @RequestParam String email, @RequestParam String password) {
        if (Users.Current(session) != null)
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

    ModelAndView retrunPagewithUser(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(Users.Current(session));
        return modelAndView;
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

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        logger.error("Request: " + req.getRequestURL() + " raised " + ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error.vm");
        return mav;
    }


    //interceptor
}
