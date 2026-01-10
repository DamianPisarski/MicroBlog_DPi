package WWSIS.Microblog.dao;

import java.util.List;
import WWSIS.Microblog.model.User;

public interface UzytkownikDao {
    // Pobranie użytkownika po loginie
    User getUserByUsername(String username);

    // Dodanie nowego użytkownika
    void addUser(User user);

    // Pobranie wszystkich użytkowników
    List<User> getAllUsers();
}
