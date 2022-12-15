package Database;

import User.User;

/**
 * A custom Exception that gets thrown if you try to delete a user that is not in the database.
 */
public class UserNotInDatabaseException extends Exception{
    /**
     * Prints the exception message along with the user.
     * @param user the user that you are trying to delete from the database.
     */
    public UserNotInDatabaseException(User user){
        super("User not found in database: " + user.getName());
    }
}
