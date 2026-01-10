package WWSIS.Microblog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import WWSIS.Microblog.dao.FollowerDao;
import WWSIS.Microblog.dao.UzytkownikDao;
import WWSIS.Microblog.dao.WpisDao;
import WWSIS.Microblog.model.Follower;
import WWSIS.Microblog.model.User;
import WWSIS.Microblog.model.Wpis;
import WWSIS.Microblog.service.MicroblogService;

@Service
public class MicroblogServiceImpl implements MicroblogService {

    @Autowired
    private UzytkownikDao userDao;

    @Autowired
    private WpisDao wpisDao;

    @Autowired
    private FollowerDao followerDao;

    @Override
    public List<Wpis> getWpisyUzytkownikaIUzytkownikowSledzonych(User user) {
        // Wpisy użytkownika
        List<Wpis> mojeWpisy = wpisDao.getWpisyByUser(user);

        // Wpisy użytkowników, których obserwuje
        List<User> allUsers = userDao.getAllUsers();
        List<User> sledzeni = allUsers.stream()
                                      .filter(u -> followerDao.isFollowing(user, u))
                                      .collect(Collectors.toList());

        List<Wpis> wpisySledzonych = sledzeni.stream()
                                             .flatMap(u -> wpisDao.getWpisyByUser(u).stream())
                                             .collect(Collectors.toList());

        mojeWpisy.addAll(wpisySledzonych);
        return mojeWpisy;
    }

    @Override
    public void addWpis(Wpis wpis) {
        wpisDao.addWpis(wpis);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void addFollower(Follower follower) {
        // Używamy istniejącej metody follow() zamiast nieistniejącego addFollower
        followerDao.follow(follower.getFollower(), follower.getFollowed());
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
