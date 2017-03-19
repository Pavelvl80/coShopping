package com.controller;

import com.model.Ad;
import com.model.Order;
import com.model.Users;
import com.service.AdService;
import com.service.OrderService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Edvard Piri on 04.02.2017.
 */
@Controller
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private AdService adService;
    @Autowired
    private OrderService orderService;

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

    @RequestMapping("/register")
    public ModelAndView registerAd(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("registerAd.vm");
        modelAndView.addObject("errors", request.getSession().getAttribute("errors"));
        return modelAndView;
    }

    @RequestMapping("/register-ad-request")
    public String registerAdRequest(HttpSession session, @ModelAttribute Ad ad, BindingResult result) {
        Users curUser = Users.current(session);

        if (curUser == null)
            return "redirect:/user/login";

        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            Map<String, String> errorsMap = new HashMap<>();
            for (ObjectError error : errors) {
                errorsMap.put(error.getObjectName(), error.getDefaultMessage());
            }
            session.setAttribute("errors", errorsMap);
//                redirectAttributes.addAllAttributes(errorsMap);

            return "redirect:/ad/register";
        }

        ad.setOwner(curUser);
        adService.register(ad);
        return "redirect:/user/" + Users.current(session).getId();
    }

    @RequestMapping("/{adId}")
    public ModelAndView adProfile(@PathVariable String adId) {
        Ad ad = adService.getAbById(Long.parseLong(adId));
        ModelAndView modelAndView = new ModelAndView("ad.vm");
        modelAndView.addObject("ad", ad);
        return modelAndView;
    }

    @RequestMapping(value = "/{adId}/edit", method = RequestMethod.GET)
    public ModelAndView adEdit(@PathVariable String adId) {
        Ad ad = adService.getAbById(Long.valueOf(adId));
        if (ad == null)
            throw new NullPointerException();
        ModelAndView modelAndView = new ModelAndView("editAd.vm");
        modelAndView.addObject("ad", ad);
        return modelAndView;
    }

    @RequestMapping(value = "/{adId}/edit", method = RequestMethod.POST)
    public String adEditRequest(@ModelAttribute Ad newAd, @PathVariable String adId) {
        //TODO get old add from cash
        Ad ad = adService.getAbById(Long.valueOf(adId));
        newAd.setId(ad.getId());
        newAd.setOwner(ad.getOwner());
        newAd.setOrders(ad.getOrders());
        newAd.setDateEdited(new Date());
        adService.save(newAd);
        return "redirect:/ad/edit" + ad.getId();
    }

    @RequestMapping(value = "/{adId}/join", method = RequestMethod.POST)
    public ResponseEntity<String> joinToAd(HttpServletRequest request, @PathVariable String adId, @RequestParam String itemsCount) {
        Users curUser = Users.current(request.getSession());

        if (curUser == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if(itemsCount == null || itemsCount.equals("0"))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Long adIdLong;
        try {
            adIdLong = Long.valueOf(adId);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (curUser.getId().equals(adIdLong))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        //TODO optimise
        Ad curAd = adService.getAbById(Long.valueOf(adId));

        //TODO create and save order
        Integer itemsCountInt;
        try {
            itemsCountInt = Integer.parseInt(itemsCount);
        } catch (NumberFormatException e ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Order order = orderService.save(new Order(curAd, curUser, itemsCountInt, new Date()));
        if(order == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @RequestMapping("/get-all-ads")
    public ModelAndView getAllAds() {
        return new ModelAndView("adsByEmail.vm");
    }


}
