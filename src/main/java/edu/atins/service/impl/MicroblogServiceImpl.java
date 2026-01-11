package edu.atins.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.atins.dao.FollowerDao;
import edu.atins.dao.WpisDao;
import edu.atins.model.User;
import edu.atins.model.Wpis;
import edu.atins.service.MicroblogService;

@Service
@Transactional
public class MicroblogServiceImpl implements MicroblogService {

    @Autowired
    private WpisDao wpisDao;

    @Autowired
    private FollowerDao followerDao;

    @Override
    public List<Wpis> getWpisyUzytkownikaIUzytkownikowSledzonych(User user) {
        List<Wpis> wszystkie = new ArrayList<>();

        // wpisy samego użytkownika
        wszystkie.addAll(wpisDao.getWpisyByUser(user));

        // wpisy wszystkich użytkowników, których user śledzi
        // iterujemy po wszystkich wpisach wszystkich użytkowników (upraszczamy, bo nie ma getFollowersByUser)
        // w praktyce można pobrać listę wszystkich użytkowników i filtrować
        // tutaj przyjmijmy, że masz listę wszystkich użytkowników w systemie
        // pseudo-przykład (można dopasować do Twojej implementacji)
        // for (User other : allUsers) {
        //     if (followerDao.isFollowing(user, other)) {
        //         wszystkie.addAll(wpisDao.getWpisyByUser(other));
        //     }
        // }

        return wszystkie;
    }

    @Override
    public void addWpis(Wpis wpis) {
        wpisDao.addWpis(wpis);
    }

    @Override
    public void addFollower(User follower, User followed) {
        followerDao.follow(follower, followed);
    }

    @Override
    public void removeFollower(User follower, User followed) {
        followerDao.unfollow(follower, followed);
    }

    @Override
    public boolean isFollowing(User follower, User followed) {
        return followerDao.isFollowing(follower, followed);
    }
}


