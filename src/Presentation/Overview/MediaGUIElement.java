package Presentation.Overview;

import Media.Media;
import Presentation.MainGUI;
import User.User;
import User.UserPrefs;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOException;

public class MediaGUIElement implements IGUIElement{
    private Media media;
    private Button button;
    private Label title;
    public MediaGUIElement(Media media){
        this.media = media;
    }

    public GridPane getGUIElement(GridPane parent){
        GridPane gridpane = new GridPane();
        gridpane.getRowConstraints().add(new RowConstraints(50, 50, 50));


        ColumnConstraints colConstraint = new ColumnConstraints();
        colConstraint.setHgrow(Priority.ALWAYS);

        RowConstraints rowConstraint = new RowConstraints();
        rowConstraint.setVgrow(Priority.ALWAYS);

        gridpane.getColumnConstraints().add(colConstraint);
        gridpane.getRowConstraints().add(rowConstraint);

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
                    MainGUI.inspectMedia(media);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        //
        button.setAlignment(Pos.TOP_CENTER);
        button.setMaxSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        setMouseEvents();

        gridpane.add(button, 0 , 1);

        title = new Label(media.getTitle());
        title.setMaxSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        title.getStyleClass().add("GUIElement-title");
        title.setAlignment(Pos.CENTER);

        gridpane.add(title, 0, 0);
        gridpane.setMaxSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        title.setVisible(false);

        return gridpane;
    }
    public void setVisible(boolean visible){
        button.setVisible(visible);
        title.setVisible(false);
    }
    public void updateMedia(Media media){
        this.media = media;

        button.setStyle(getImageStyleFromPath(media.getPosterPath()));
        title.setText(media.getTitle());
    }

    private void setMouseEvents(){
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                title.setVisible(true);
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                title.setVisible(false);
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
