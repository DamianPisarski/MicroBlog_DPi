package edu.atins.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.atins.dao.UzytkownikDao;
import edu.atins.model.User;
import edu.atins.model.Wpis;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public class TestMicroblogService {

    @Autowired
    private MicroblogService microblogService;

    @Autowired
    private UzytkownikDao uzytkownikDao; // potrzebny do zapisania użytkowników

    private User user1;
    private User user2;

    @Before
    public void setUp() {
        long ts = System.currentTimeMillis(); // unikalny suffix dla email

        user1 = new User();
        user1.setUsername("user1_" + ts);
        user1.setEmail("user1_" + ts + "@example.com");
        user1.setPasswordHash("pass1");
        uzytkownikDao.addUser(user1); // zapis do bazy

        user2 = new User();
        user2.setUsername("user2_" + ts);
        user2.setEmail("user2_" + ts + "@example.com");
        user2.setPasswordHash("pass2");
        uzytkownikDao.addUser(user2); // zapis do bazy

        // teraz możemy dodać followera
        microblogService.addFollower(user1, user2); // user1 śledzi user2
    }

    @Test
    public void testAddWpis() {
        Wpis wpis1 = new Wpis();
        wpis1.setUser(user1);
        wpis1.setContent("Hello World!");
        microblogService.addWpis(wpis1);

        List<Wpis> wpisy = microblogService.getWpisyUzytkownikaIUzytkownikowSledzonych(user1);
        assertEquals(1, wpisy.size());
        assertEquals("Hello World!", wpisy.get(0).getContent());
    }

    @Test
    public void testIsFollowing() {
        assertTrue(microblogService.isFollowing(user1, user2));
    }
}
