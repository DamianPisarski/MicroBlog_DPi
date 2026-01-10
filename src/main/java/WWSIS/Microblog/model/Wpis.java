package WWSIS.Microblog.model;

import java.util.Date;

public class Wpis {
    private User user;
    private String content;
    private Date date;

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Wpis)) return false;
        Wpis other = (Wpis) obj;
        return user.getUsername().equals(other.user.getUsername()) &&
               content.equals(other.content) &&
               date.equals(other.date);
    }

    @Override
    public int hashCode() {
        return user.getUsername().hashCode() + content.hashCode() + date.hashCode();
    }
}
