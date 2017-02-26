package com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Ad;
import com.model.Users;
import com.utils.ToJSON;
import com.service.AdService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Edvard Piri on 04.02.2017.
 */
@Controller
@RequestMapping("/ad")
public class AdController {

    //TODO incapsulation..private is missed
    @Autowired
    AdService adService;

    @Autowired
    UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }


    @RequestMapping(value = "/get-ads-by-email/expensive={expensive}&cheapest={cheapest}")
    public ResponseEntity getAdsByEmail(@RequestParam String email,
                                        @PathVariable String expensive,
                                        @PathVariable String cheapest) throws Exception {
        if (email == null || email.equals("")) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        List<Ad> ads = adService.getAllAdsByOwnerEmail(email);
        if (ads == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        String result = adService.getAdsByEmailService(ads, expensive, cheapest);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping("/register-ad-request")
    public ResponseEntity<String> registerAdRequest(HttpSession session, @ModelAttribute Ad ad) {
        Users curUser = Users.Current(session);
        if (curUser == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        ad.setOwner(curUser);
        adService.register(ad);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/{AdId}")
    public ModelAndView userProfile(@PathVariable String AdId) {
        Ad ad = adService.getAbById(Long.parseLong(AdId));
        ModelAndView modelAndView = new ModelAndView("ad.vm");
        modelAndView.addObject("ad", ad);
        return modelAndView;
    }

    @RequestMapping(value = "/edit{adId}", method = RequestMethod.GET)
    public ModelAndView editUser(@PathVariable String adId) {
        Ad ad = adService.getAbById(Long.valueOf(adId));
        if(ad == null)
            throw new NullPointerException();
        ModelAndView modelAndView = new ModelAndView("editAd.vm");
        modelAndView.addObject("ad", ad);
        return modelAndView;
    }

    //TODO methods naming...
    @RequestMapping(value = "/edit{adId}", method = RequestMethod.POST)
    public String editUserRequest(@ModelAttribute Ad newAd, @PathVariable String adId) {
        //TODO get old ad from cash
        Ad ad = adService.getAbById(Long.valueOf(adId));
        newAd.setId(ad.getId());
        newAd.setOwner(ad.getOwner());
        newAd.setParticipants(ad.getParticipants());
        adService.save(newAd);
        return "redirect:/ad/edit" + ad.getId();
    }



    @RequestMapping("/get-all-ads")
    public ModelAndView getAllAds() {
        return new ModelAndView("adsByEmail.vm");
    }

    @RequestMapping("/register-ad")
    public ModelAndView registerAd() {
        return new ModelAndView("registerAd.vm");
    }
}
