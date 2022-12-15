package Presentation.Login.Exceptions;

/**
 * An Exception to handle the chance if the username already exist in the user database
 */
public class UsernameAlreadyExistsException extends Exception {
    public UsernameAlreadyExistsException() { super("Name already exists"); }
}
