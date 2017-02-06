package com.service;

import com.dao.AdDAO;
import com.model.Ad;
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
    public List<Ad> getAllByUserId(Long id) {
        if (id.equals(null))
            return null;
        return adDAO.getAllByUserId(id);
    }

}
