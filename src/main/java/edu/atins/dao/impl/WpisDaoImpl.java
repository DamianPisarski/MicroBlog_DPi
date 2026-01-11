package edu.atins.dao.impl;

import edu.atins.dao.WpisDao;
import edu.atins.model.User;
import edu.atins.model.Wpis;
import org.springframework.stereotype.Repository; 

import java.util.ArrayList;
import java.util.List;

@Repository
public class WpisDaoImpl implements WpisDao {
    private final List<Wpis> wpisy = new ArrayList<>();

    @Override
    public void addWpis(Wpis wpis) {
        wpisy.add(wpis);
    }

    @Override
    public List<Wpis> getWpisyByUser(User user) {
        List<Wpis> result = new ArrayList<>();
        for (Wpis w : wpisy) {
            if (w.getUser().getUsername().equals(user.getUsername())) {
                result.add(w);
            }
        }
        return result;
    }
}
