package DataAccess;

import DataParser.JSONParser;
import Media.Media;
import User.User;

import java.util.List;

public class DataAccessor implements IDataAccess{
    @Override
    public List<User> getUsers() {
        return JSONParser.loadUsers();
    }

    @Override
    public List<Media> getFilms() {
        return JSONParser.loadFilms();
    }

    @Override
    public List<Media> getShows() {
        return JSONParser.loadShows();
    }

    @Override
    public void deleteUser(User user) {
        JSONParser.deleteUser(user.getName());
    }

    @Override
    public void addUser(User user) {
        JSONParser.saveUser(user);
    }
}
