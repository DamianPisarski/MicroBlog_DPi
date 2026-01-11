package edu.atins.service;

import java.util.List;
import edu.atins.model.User;
import edu.atins.model.Wpis;

public interface MicroblogService {

    // Pobranie wpisów użytkownika oraz wpisów obserwowanych
    List<Wpis> getWpisyUzytkownikaIUzytkownikowSledzonych(User user);

    void addWpis(Wpis wpis);

    void addFollower(User follower, User followed);

    void removeFollower(User follower, User followed);

    boolean isFollowing(User follower, User followed);
}



