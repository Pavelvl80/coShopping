package com.utils;

import com.model.Users;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Component
public class MainInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Users curUser = Users.current(session);
        String logMessage;
        Date date = new Date();
        if (curUser != null) {
            //"http://localhost:8080/"
            logMessage = "User with id " + curUser.getId() + "was accessed page " + request.getRequestURL() + " date: " + date.toString();
        }
        logMessage = "Some user was accessed page " + request.getRequestURL() + " date: " + date.toString();

        System.out.println(logMessage);


        return true;
    }


    //controller logic


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


        System.out.println("postHandle");


    }

//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        super.afterCompletion(request, response, handler, ex);
//
//    }
}