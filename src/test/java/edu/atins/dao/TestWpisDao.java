package edu.atins.dao;

import edu.atins.model.User;
import edu.atins.model.Wpis;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
@Transactional
@Rollback(true)
public class TestWpisDao {

    @Autowired
    WpisDao wpisDAO;

    @Autowired
    UzytkownikDao uzytkownikDAO;

    User testUser;

    @Before
    public void setUp() {
        testUser = new User();
        testUser.setUsername("tester");
        testUser.setPasswordHash("pass");
        uzytkownikDAO.addUser(testUser);
    }

    @Test
    public void testAddWpis() {
        Wpis wpis = new Wpis();
        wpis.setUser(testUser);
        wpis.setContent("Hello World");
        wpis.setDate(new Date());

        wpisDAO.addWpis(wpis);

        List<Wpis> wpisy = wpisDAO.getWpisyByUser(testUser);
        assertTrue(wpisy.contains(wpis));
    }
}
