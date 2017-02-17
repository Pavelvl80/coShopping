package com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Ad;
import com.model.Users;
import com.utils.ToJSON;
import com.service.AdService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Edvard Piri on 04.02.2017.
 */
@Controller
public class AdController {

    @Autowired
    AdService adService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/get-ads-by-email/expensive={expensive}&cheapest={cheapest}")
    public ResponseEntity getAdsByEmail(@RequestParam String email,
                                        @PathVariable String expensive,
                                        @PathVariable String cheapest) throws Exception {

        if (email == null || email.equals("")) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        List<Ad> ads = adService.getAllAdsByOwnerEmail(email);
        if (ads == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        String result = adService.getAdsEmailService(ads, expensive, cheapest);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @RequestMapping("/register-ad-request")
    public ResponseEntity<String> registerAdRequest(HttpSession session, @ModelAttribute Ad ad) {
        Users curUser = Users.Current(session);
        if (curUser == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("location", "/");
        ad.setOwner(curUser);
        adService.register(ad);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/get-all-ads")
    public ModelAndView getAllAds() {
        return new ModelAndView("task.vm");
    }

    @RequestMapping("/register-ad")
    public ModelAndView registerAd() {
        return new ModelAndView("registerAd.vm");
    }

    @RequestMapping("/ad")
    public ModelAndView adMapping() {
        return new ModelAndView("ad.vm");
    }
}
