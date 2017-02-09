package com.service;

import com.dao.AdDAO;
import com.model.Ad;
import com.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Edvard Piri on 28.01.2017.
 */
@Service
public class AdServiceImpl implements AdService {
    @Autowired
    AdDAO adDAO;

    @Override
    public List<Ad> getAllByUserId(Users users) {
        if (users.equals(null))
            return null;
        return adDAO.getAllByUserId(users);
    }

    @Override
    public List<Ad> getAllAdsByOwnerEmail(Users user) {
        if (user == null)
            return null;
        List<Ad> ads = adDAO.getAllByUserId(user);
        return ads;
    }

    @Override
    public Ad getExpensiveAd(List<Ad> ads) {
        int max = Integer.MIN_VALUE;
        Ad mostExpensive = null;
        for (Ad ad : ads) {
            if (max < ad.getTotalPrice()) {
                mostExpensive = ad;
                max = ad.getTotalPrice();
            }
        }
        return mostExpensive;
    }

    @Override
    public Ad getCheapestAd(List<Ad> ads) {
            int min = Integer.MAX_VALUE;
            Ad mostCheapest = null;
            for (Ad ad : ads) {
                if (min > ad.getTotalPrice()) {
                    mostCheapest = ad;
                    min = ad.getTotalPrice();
                }
            }
            return mostCheapest;
    }

    @Override
    public Ad register(Ad ad) {
       return adDAO.save(ad);
    }

}
