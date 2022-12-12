package DataAccess;

import Media.Media;
import User.User;

import java.util.List;

public interface IDataAccess {
    public List<User> getUsers();
    public void addUser(User user);
    public void deleteUser(User user);

    public List<Media> getFilms();
    public List<Media> getShows();

}
