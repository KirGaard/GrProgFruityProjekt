package Sorting.Exceptions;

/**
 * Exception class that creates a homemade exception
 */

public class MediaListEmptyException extends Exception {

    /**
     * The following method creates an exception that gets thrown when the media list is empty
     */

    public MediaListEmptyException() {super("Trying to instantiate a new sorter on an empty mediaList");}
}
