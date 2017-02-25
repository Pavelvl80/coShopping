package com.dao;

import com.model.Users;

/**
 * Created by Edvard Piri on 15.02.2017.
 */
public interface TestDAO {
    Long testPutDb(Users user) throws Exception;

    Long testGetDb();
}
