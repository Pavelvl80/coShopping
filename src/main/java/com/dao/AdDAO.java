package com.dao;

import com.model.Ad;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Edvard Piri on 28.01.2017.
 */

public interface AdDAO {
    Ad save(Ad ad);

    List<Ad> getAllByUserId(Long id);


}
