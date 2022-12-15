package Presentation.Login;

import Database.UserDatabase;
import Presentation.IController;
import Presentation.Login.Exceptions.InvalidInputException;
import Presentation.MainGUI;
import User.UserPrefs;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;


/**
 * This class handles all the logic in login scene
 */
public class LoginController implements IController {

    /**
     * A reference to the backgroundImage in the .fxml
     */
    @FXML
    private ImageView backgroundImage;
    /**
     * A reference to the usernameField in the .fxml
     */
    @FXML
    private TextField usernameField;
    /**
     * A reference to the passwordField in the .fxml
     */
    @FXML
    private PasswordField passwordField;
    /**
     * A reference to the wrongInput label in the .fxml
     */
    @FXML
    private Label wrongInput;
    /**
     * A reference to the instance of the user database from the MainGUI
     */
    private UserDatabase userDatabase;

    /**
     * Called when the scene is loaded
     * This method gets the reference to the MainGUI database
     * Also hides the wrong input label
     */
    @FXML
    public void initialize() {
        userDatabase = MainGUI.userDatabase;
        System.out.println("Initializing LOGIN");
        wrongInput.setVisible(false);
    }

    /**
     * Calls the function on MainGUI to open up the user creation scene
     */
    @FXML
    private void signUp() {
        MainGUI.createNewUser();
    }

    /**
     * Called when the user tries to log in.
     * Validates the inputted username and password to check if the user is in the database
     * If invalid input reset the input fields and set the wrong input label visible
     */
    @FXML
    public void exit(){
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            validateLogin(username, password);
        } catch (InvalidInputException e) {
            usernameField.setText("");
            passwordField.setText("");
            wrongInput.setVisible(true);

            return;
        }
        UserPrefs.currentUser = userDatabase.getUser(username);
        try {
            MainGUI.signIn();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The validateLogin method throws an exception if the input is invalid
     * @param username the inputted username
     * @param password the inputted password
     * @throws InvalidInputException if the user database doesn't contain the user nor a user with the inputted password
     */
    private void validateLogin(String username, String password) throws InvalidInputException {

        if (!userDatabase.ContainsUser(username)) { throw new InvalidInputException(); }
        if (!userDatabase.getUser(username).getPassword().equals(password)) { throw new InvalidInputException(); }
    }

}
