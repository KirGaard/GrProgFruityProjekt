package DataAccess;

import DataParser.JSONParser;
import Media.Media;
import User.User;

import java.util.List;

/**
 * Class that has the responsibilities of accessing different data and implements the DataAccess interface
 */

public class DataAccessor implements IDataAccess{

    /**
     * The following method allows to get a list of users
     * @return list of users
     */

    @Override
    public List<User> getUsers() {
        return JSONParser.loadUsers();
    }

    /**
     * The following method allows to get a list of all films
     * @return list of all films
     */

    @Override
    public List<Media> getFilms() {
        return JSONParser.loadFilms();
    }

    /**
     * The following method allows to get a list of all shows
     * @return list of all shows
     */

    @Override
    public List<Media> getShows() {
        return JSONParser.loadShows();
    }

    /**
     * The following method allows to delete a user from the list of users
     * @param user the user we want to delete from the list of users
     */

    @Override
    public void deleteUser(User user) {
        JSONParser.deleteUser(user.getName());
    }

    /**
     * The following method allows to add a user to the list of users
     * @param user the user that we want to add to the list of users
     */

    @Override
    public void addUser(User user) {
        JSONParser.saveUser(user);
    }
}
