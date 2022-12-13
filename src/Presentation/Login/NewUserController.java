package Presentation.Login;

import Presentation.IController;
import Presentation.MainGUI;
import User.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewUserController implements IController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
@FXML
private Label errorLabel;

    @Override
    public void exit() {
        CreateNewUser();
    }

    private void CreateNewUser(){
        String username = usernameField.getText().toLowerCase();
        String password = passwordField.getText();

        usernameField.setText("");
        passwordField.setText("");

        if (MainGUI.userDatabase.ContainsUser(username)){
            errorLabel.setText("Navn eksisterer allerede!");
            return;
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
