package com.service;

import com.model.Ad;
import com.model.Users;

import java.util.List;

/**
 * Created by Edvard Piri on 28.01.2017.
 */
public interface AdService {
    List<Ad> getAllByUserId(Users user);

    List<Ad> getAllAdsByOwnerEmail(Users user);

    Ad getExpensiveAd(List<Ad> ads);

    Ad getCheapestAd(List<Ad> ads);

    String toAdsAllExpChip(List<Ad> ads, Ad expensiveAd, Ad cheapestAd);
}
