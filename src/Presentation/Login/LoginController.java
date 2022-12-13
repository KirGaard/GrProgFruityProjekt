package Presentation.Login;

import Database.UserDatabase;
import Presentation.IController;
import Presentation.MainGUI;
import User.UserPrefs;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;


public class LoginController implements IController {

    @FXML
    private ImageView backgroundImage;

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label wrongInput;
    UserDatabase userDatabase;

    @FXML
    public void initialize() {
        userDatabase = MainGUI.userDatabase;
        System.out.println("Initializing LOGIN");
        wrongInput.setVisible(false);
    }

    @FXML
    private void signUp() {
        MainGUI.createNewUser();
    }

    @FXML
    public void exit(){
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            validateLogin(username, password);
        } catch (InvalidUserException e) {
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

    private void validateLogin(String username, String password) throws InvalidUserException {

        if (!userDatabase.ContainsUser(username)) { throw new InvalidUserException(); }
        if (!userDatabase.getUser(username).getPassword().equals(password)) { throw new InvalidUserException(); }
    }

}
