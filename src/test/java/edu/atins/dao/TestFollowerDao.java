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

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
@Transactional
@Rollback(true)
public class TestFollowerDao {

    @Autowired
    FollowerDao followerDAO;

    @Autowired
    UzytkownikDao uzytkownikDAO;

    User newFollowee;
    User newFollower;

    @Before
    public void setUp() {
        newFollower = new User();
        newFollower.setUsername("follower1");
        uzytkownikDAO.addUser(newFollower);

        newFollowee = new User();
        newFollowee.setUsername("followee1");
        uzytkownikDAO.addUser(newFollowee);
    }

    @Test
    public void testAddFollower() {
        followerDAO.follow(newFollower, newFollowee);
        assertTrue(followerDAO.isFollowing(newFollower, newFollowee));
    }
}
