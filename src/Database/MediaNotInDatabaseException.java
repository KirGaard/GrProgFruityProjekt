package Database;
//TODO: Har jeg skrevet denne JavaDoc på Exception rigtigt?
/**
 * A custom Exception that gets thrown when you try to get the title of a media that is not in the database.
 */
public class MediaNotInDatabaseException extends Exception{
    /**
     * Prints the title as an Exception message.
     * @param movieTitle the title of the media that you are trying to get from the database
     */
    public MediaNotInDatabaseException(String movieTitle){
        super(movieTitle);
    }


}
