package Searching;

/**
 * Exception class that creates a homemade exception
 */

public class NullQueryException extends Exception {

    /**
     * The following method creates an exception that gets thrown when the query is null
     */

    public NullQueryException() {super("Search query is null");}
}
