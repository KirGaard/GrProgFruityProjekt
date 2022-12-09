package Presentation.Login;

public class InvalidUserException extends Exception {
    public InvalidUserException() { super("Invalid user, try again"); }
}
