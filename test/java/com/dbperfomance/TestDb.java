package com.dbperfomance;

import com.dao.AdDAO;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.model.Ad;
import com.model.Users;
import com.service.AdService;
import com.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Edvard Piri on 13.02.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class TestDb {
//    @PersistenceContext
//    EntityManager entityManager;
//
//    @Autowired
//    private AdService adService;
//
//    @Autowired
//    private UserService userService;
//
//    @TestDb
//    public void testFind() throws Exception {
//        Users user = userService.getByEmail("lotar@smail.ru");
//        List<Ad> personList = adService.getAllAdsByOwner(user);
//        assertEquals(1, personList.size());
//        assertEquals("Phillip", personList.get(0).getFirstName());
//    }


    //others tests

}