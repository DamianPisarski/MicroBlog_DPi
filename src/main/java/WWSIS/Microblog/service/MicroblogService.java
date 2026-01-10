package WWSIS.Microblog.service;

import java.util.List;

import WWSIS.Microblog.model.Follower;
import WWSIS.Microblog.model.User;
import WWSIS.Microblog.model.Wpis;

public interface MicroblogService {

    List<Wpis> getWpisyUzytkownikaIUzytkownikowSledzonych(User user);

    void addWpis(Wpis wpis);

    void addUser(User user);

    void addFollower(Follower follower);

    List<User> getAllUsers();
}
