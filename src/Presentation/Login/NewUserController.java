package Presentation.Login;

import Presentation.IController;
import Presentation.MainGUI;
import Presentation.Login.Exceptions.UsernameAlreadyExistsException;
import User.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This Controller is responsible for handling the newUser scene.
 * it is responsible for creating a new user and validating the fields
 */
public class NewUserController implements IController {
    /**
     * A reference to the usernameField in the .fxml file
     */
    @FXML
    private TextField usernameField;
    /**
     * A reference to the passwordField in the .fxml file
     */
    @FXML
    private PasswordField passwordField;
    /**
     * A reference to the errorLabel in the .fxml file
     */
    @FXML
    private Label errorLabel;

    /**
     * Is called when the user tries to create the new user
     * Catches the user for verbose purposes
     */
    @Override
    public void exit() {
        try {
            CreateNewUser();
        } catch (UsernameAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates the new user.
     * First validates the two fields to check if they are valid - using regex
     * Shows the error to the user if the input are invalid through the error label
     * @throws UsernameAlreadyExistsException Throws this error if the username already exists in the database
     */
    private void CreateNewUser() throws UsernameAlreadyExistsException {
        String username = usernameField.getText().toLowerCase();
        String password = passwordField.getText();

        usernameField.setText("");
        passwordField.setText("");

        if (MainGUI.userDatabase.ContainsUser(username)){
            errorLabel.setText("Navn eksisterer allerede!");
            throw new UsernameAlreadyExistsException();
        }

        Pattern legalPattern = Pattern.compile("^([a-z]|[0-9]|[?!])+$");

        Matcher usernameMatcher = legalPattern.matcher(username);
        if (!usernameMatcher.find()){
            errorLabel.setText("Anvend kun bogstaver og tal!");
            return;
        }

        Matcher passwordMatcher = legalPattern.matcher(username);
        if (!passwordMatcher.find()){
            errorLabel.setText("Anvend kun bogstaver og tal!");
            return;
        }

        User newUser = new User(username, password);
        MainGUI.userDatabase.addUser(newUser);

        MainGUI.userCreationComplete();
    }

}
