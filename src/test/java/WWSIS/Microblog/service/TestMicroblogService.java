package WWSIS.Microblog.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import WWSIS.Microblog.model.Follower;
import WWSIS.Microblog.model.User;
import WWSIS.Microblog.model.Wpis;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
@Transactional
@Rollback(true)
public class TestMicroblogService {

    @Autowired
    private MicroblogService microblogService;

    private User u1, u2;
    private Wpis wpis1;

    @Before
    public void setUp() {
        u1 = new User();
        u1.setUsername("user1");
        microblogService.addUser(u1);

        u2 = new User();
        u2.setUsername("user2");
        microblogService.addUser(u2);

        Follower follower = new Follower();
        follower.setFollower(u1);
        follower.setFollowed(u2);
        microblogService.addFollower(follower);

        wpis1 = new Wpis();
        wpis1.setUser(u2);
        wpis1.setContent("Hello world!");
        microblogService.addWpis(wpis1);
    }

    @Test
    public void testGetWpisyUzytkownikaIUzytkownikowSledzonych() {
        List<Wpis> wpisy = microblogService.getWpisyUzytkownikaIUzytkownikowSledzonych(u1);
        assertEquals(1, wpisy.size());
        assertEquals("Hello world!", wpisy.get(0).getContent());
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = microblogService.getAllUsers();
        assertTrue(users.contains(u1));
        assertTrue(users.contains(u2));
    }
}
