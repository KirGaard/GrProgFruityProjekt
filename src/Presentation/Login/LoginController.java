package Presentation.Login;

import Presentation.IController;
import Presentation.Main;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;


public class LoginController implements IController {

    @FXML
    private ImageView backgroundImage;

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    public void initialize()
    {
        System.out.println("Initializing LOGIN");
    }

    @FXML
    private void signUp() {
        try {
            Desktop.getDesktop().browse(new URL("https://www.google.com").toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void exit() throws IOException {
        Main.signIn();
    }

    @FXML
    private boolean validateLogin(){

        String username = usernameField.getText();
        String password = passwordField.getText();

        // Validate login
        throw new RuntimeException("Not implemented yet");
    }



}
