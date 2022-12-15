package DataAccess;

import Media.Media;
import User.User;

import java.util.List;

/**
 * Interface for accessing data with methods that need to be implemented
 */

public interface IDataAccess {
    /**
     * The following method allows to get a list of users
     * @return list of users
     */
    public List<User> getUsers();

    /**
     * The following method allows to add a user to the list of users
     * @param user the user that we want to add to the list of users
     */
    public void addUser(User user);

    /**
     * The following method allows to delete a user from the list of users
     * @param user the user we want to delte from the list of users
     */
    public void deleteUser(User user);

    /**
     * The following method allows to get a list of all films
     * @return list of all films
     */
    public List<Media> getFilms();

    /**
     * The following method allows to get a list of all shows
     * @return list of all shows
     */
    public List<Media> getShows();

}
