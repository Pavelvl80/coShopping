package com.service;

import com.model.Ad;

import java.util.List;

/**
 * Created by Edvard Piri on 28.01.2017.
 */
public interface AdService {
    List<Ad> getAllByUserId(Long id);
}
