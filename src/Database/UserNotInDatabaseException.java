package Database;

import User.User;

public class UserNotInDatabaseException extends Exception{
    public UserNotInDatabaseException(User user){
        super("User not found in database: " + user.getName());
    }
}
