package Presentation.Login.Exceptions;

public class UsernameAlreadyExistsException extends Exception {
    public UsernameAlreadyExistsException() { super("Name already exists"); }
}
