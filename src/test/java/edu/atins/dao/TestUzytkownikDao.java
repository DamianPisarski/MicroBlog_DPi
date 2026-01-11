package edu.atins.dao;

import edu.atins.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
@Transactional
@Rollback(true)
public class TestUzytkownikDao {

    @Autowired
    UzytkownikDao uzytkownikDAO;

    User newUser;

    @Before
    public void setUp() {
        newUser = new User();
        newUser.setUsername("janek");
        newUser.setPasswordHash("haslo");
        uzytkownikDAO.addUser(newUser);
    }

    @Test
    public void testGetUserByUsername() {
        User retrieved = uzytkownikDAO.getUserByUsername("janek");
        assertNotNull(retrieved);
        assertEquals("janek", retrieved.getUsername());
    }
}
