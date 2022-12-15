package Sorting.Exceptions;

/**
 * Exception class that creates a homemade exception
 */


public class MediaListNullPointerException extends Exception {

    /**
     * The following method creates an exception that gets thrown when the media list has value null
     */

    public MediaListNullPointerException() { super("Trying to instantiating a new sorter with a mediaList pointing to null"); }
}
