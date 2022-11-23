package Database;

public class MediaNotInDatabaseException extends Exception{

    public MediaNotInDatabaseException(String movieTitle){
        super(movieTitle);
    }


}
