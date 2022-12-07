package Presentation.Login;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Window;


public class LoginController {

    @FXML
    private ImageView backgroundImage;
    @FXML
    private void initialize()
    {
        System.out.println("Initializing LOGIN");
        System.out.println(backgroundImage.getImage().getUrl());
    }




}
