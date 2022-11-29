package Database;

public class UsernameNotInDatabaseException extends Exception{
    public UsernameNotInDatabaseException(String username){
        super(username);
    }

}
