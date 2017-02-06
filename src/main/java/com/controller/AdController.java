package com.controller;

import com.dao.AdDAO;
import com.google.gson.Gson;
import com.model.Ad;
import com.model.Users;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by Edvard Piri on 04.02.2017.
 */
@Controller
public class AdController {
    @Autowired
    AdDAO adDAO;

    @Autowired
    UserService userService;

    @RequestMapping("/get-ads-by-email-request")
    public ResponseEntity getAdsByEmail(@RequestParam String email) {

        if (email.equals(null) || email == "")
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        Users user = userService.findByEmail(email);

        if(user == null) return new ResponseEntity(HttpStatus.NOT_FOUND);

        List<Ad> ads = userService.getAllAdsByOwnerEmail(user);

        Ad expensiveAd = userService.getExpensiveAd(ads);

        Ad cheapestAd = userService.getCheapestAd(ads);
        String adsJson = "null";
        if(ads != null) adsJson = ads.toString();

        String json = "\"allAds\": \"" + ads + "\", \"expensive\": \"" + expensiveAd + "\", \"cheapest\": \"" + cheapestAd + "\"";
        return new ResponseEntity(json, HttpStatus.OK);
    }

    @RequestMapping("/get-all-ads")
    public ModelAndView getAllAds() {
        return new ModelAndView("task.vm");
    }
}
