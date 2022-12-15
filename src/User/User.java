package User;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles a specific users username, password and favorites.
 */
public class User {
    /**
     * A String of the username.
     */
    private String name;
    /**
     * A String of the password.
     */
    private String password;
    /**
     * A list of Strings, where the Strings are movies and films that the user has as favorites.
     */
    private List<String> favoriteTitles;

    /**
     * The constructor of User. Instantiates and initialises the users name and password.
     * @param name the users username.
     * @param password the users password.
     */
    public User(String name, String password) {
        this.name = name.toLowerCase();
        this.password = password;
    }

    /**
     * Adds a favorite film or show to a user list of favorites. Also instatiates and initialises the list if it is null.
     * @param favorite the film or show that is trying to get added to the list of favorites.
     */
    public void addFavorite(String favorite) {
        if (favoriteTitles == null) favoriteTitles = new ArrayList<>();
        favoriteTitles.add(favorite);
    }

    /**
     * Gets all the users favorites from the users list of favorites and prints the result.
     */
    public void getFavorites() {
        for (String favorite : favoriteTitles) {
            System.out.println(favorite);
        }
    }

    /**
     * Removes a film or show from a users list of favorites. Also instatiates and initialises the list if it is null.
     * @param favorite the film or show that is trying to get removed from list of favorites.
     */
    public void removeFavorite (String favorite) {
        if (favoriteTitles == null) favoriteTitles = new ArrayList<>();
        favoriteTitles.remove(favorite);
    }

    /**
     * Changes a users username.
     * @param name the new name of the user.
     */
    public void changeUsername (String name) {
        this.name = name;
    }

    /**
     * Changes a users pasword.
     * @param password the new password of the user.
     */
    public void changePassword (String password) {
        this.password = password;
    }

    /**
     * Gets the username of the user.
     * @return the username of the user.
     */
    public String getName () {
        return name;
    }

    /**
     * Gets the username of the user.
     * @return the password of the user.
     */
    public String getPassword () {
        return password;
    }

    /**
     * Gets the users favorite titles.
     * @return the users favorite titles.
     */
    public List<String> getFavoriteTitles () {
        if (favoriteTitles == null) favoriteTitles = new ArrayList<>();
        return favoriteTitles;
    }
}

