package edu.atins.dao;

import edu.atins.model.User;
import edu.atins.model.Wpis;
import java.util.List;

public interface WpisDao {
    void addWpis(Wpis wpis);
    List<Wpis> getWpisyByUser(User user);
}
