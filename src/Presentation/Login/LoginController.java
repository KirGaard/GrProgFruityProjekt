package Presentation.Login;

import Presentation.Main;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;


public class LoginController {

    @FXML
    private ImageView backgroundImage;
    @FXML
    private void initialize()
    {
        System.out.println("Initializing LOGIN");
        System.out.println(backgroundImage.getImage().getUrl());
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
    private void exit() {
        Main.signIn();
    }

    @FXML
    private void changeColorOnMouseEnter() {
        
    }




}
