package com.service;

import com.model.Ad;
import com.model.Users;

import java.util.List;
import java.util.Map;

/**
 * Created by Edvard Piri on 28.01.2017.
 */
public interface AdService {
    List<Ad> getAllByUserId(Users user);

    List<Ad> getAllAdsByOwnerEmail(String email);

    Ad getExpensiveAd(List<Ad> ads);

    Ad getCheapestAd(List<Ad> ads);

    Ad register(Ad ad);

    String getAdsEmailService(List<Ad> all, String expensive, String cheapest) throws Exception;
}
