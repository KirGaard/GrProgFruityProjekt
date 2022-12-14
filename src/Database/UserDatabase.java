package Database;

import DataAccess.DataAccessor;
import DataParser.JSONParser;
import User.User;

import java.util.Comparator;
import java.util.List;

/**
 * The database that contains a list of all users.
 */
public class UserDatabase {
    /**
     * The list that users are stored in.
     */
    private List<User> users;

    /**
     * The constructor of UserDatabase. It instantiates and initializes DataAccessor, which is used to get all users and store them in the users list.
     */
    public UserDatabase(){
        DataAccessor dataAccessor = new DataAccessor();

        users = dataAccessor.getUsers();
    }
    /**
     * A method used by the LoginController.
     * Gets the user in the database by the username
     * @param name the username of the user.
     * @return returns the user that matches the name param
     * If username is not in the database, it will throw a RuntimeException.
     */
    public User getUser(String name){
        for(User user : users){
            if (user.getName().equals(name.toLowerCase())){
                return user;
            }
        }
        throw new RuntimeException("Username not in database");
    }

    /**
     * A method used by both the LoginController and NewUserController. When you are trying to log in or create a user, it checks to see if that user exists. If you are trying to create a user and the user already exits, then it will throw a UsernameAlreadyExistsException.
     * @param name the username of the user you are trying to log in as or create.
     * @return found, which is true if the user exists and false if the user does not exist.
     */
    public boolean ContainsUser(String name){
        boolean found = false;

        for (User user : users) if (user.getName().equals(name.toLowerCase())) found = true;

        return found;
    }

    /**
     * Adds a user to the database.
     * @param user the user that you are trying to add to the database.
     */
    public void addUser(User user){
        users.add(user);
        saveDatabase();
    }
    /**
     * Saves the runtime database.
     * Calls the JSONParser to save and serialize every user in the database.
     * This method must be called when the program closes otherwise there could be a data loss
     */
    public void saveDatabase(){
        users.forEach(user -> JSONParser.saveUser(user));
    }

    /**
     * Deletes a user from the database.
     * @param user the user that you are trying to delete.
     * @throws UserNotInDatabaseException when the user you are trying to delete is not in the database.
     */
    public void deleteUser(User user) throws UserNotInDatabaseException{
        if (!ContainsUser(user.getName())) throw new UserNotInDatabaseException(user);
        JSONParser.deleteUser(user.getName());
        users.remove(user);
        saveDatabase();
    }
}
