package WWSIS.Microblog.dao;

import WWSIS.Microblog.model.User;
import WWSIS.Microblog.model.Wpis;
import java.util.List;

public interface WpisDao {
    void addWpis(Wpis wpis);
    List<Wpis> getWpisyByUser(User user);
}
