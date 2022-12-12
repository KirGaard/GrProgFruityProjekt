package Presentation.Overview;

import Media.Media;
import Presentation.Main;
import User.User;
import User.UserPrefs;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOException;

public class MediaGUIElement implements IGUIElement{
    private Media media;
    private Button button;
    public MediaGUIElement(Media media){
        this.media = media;
    }

    public Button getGUIElement(GridPane parent){
        button = new Button();
        button.setMaxSize(Integer.MAX_VALUE, Integer.MAX_VALUE);

        // Setting css
        button.getStylesheets().add(parent.getStylesheets().get(0));
        button.getStyleClass().add("GUIElement");

        // Some weird filepath magic to get the correct path for javafx -- Needs the path to the out folder
        String pathToCSSDir = button.getStylesheets().get(0).replaceAll("/Overview.css", ""); // Gets the path from the stylesheet which was set in Scenebuilder
        String imageStyle = "-fx-background-image: url(\'" + pathToCSSDir + "/" + media.getPosterPath() +"\')";

        button.setStyle(imageStyle);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Main.inspectMedia(media);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        //
        button.setAlignment(Pos.TOP_CENTER);
        setMouseEvents();

        return button;
    }
    public void setVisible(boolean visible){
        button.setVisible(visible);
    }
    public void updateMedia(Media media){
        this.media = media;

        button.setStyle(getImageStyleFromPath(media.getPosterPath()));
    }

    private void setMouseEvents(){
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setText(media.getTitle());
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setText("");
            }
        });
    }

    private void onFavorite(Button star){
        User user = UserPrefs.currentUser;

        if (user.getFavoriteTitles().contains(media.getTitle())){
            user.removeFavorite(media.getTitle());

            star.setStyle(getImageStyleFromPath("Icons/star_empty.png"));
            return;
        }

        user.addFavorite(media.getTitle());
        star.setStyle(getImageStyleFromPath("Icons/star_full.png"));
    }

    private String getImageStyleFromPath(String path){
        String pathToCSSDir = button.getStylesheets().get(0).replaceAll("/Overview.css", ""); // Gets the path from the stylesheet which was set in Scenebuilder
        return "-fx-background-image: url(\'" + pathToCSSDir + "/"+ path + "\')";
    }


}
