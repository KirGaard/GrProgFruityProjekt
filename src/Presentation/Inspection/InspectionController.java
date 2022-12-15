package Presentation.Inspection;

import Database.UserDatabase;
import Media.Media;
import Presentation.IController;
import User.User;
import User.UserPrefs;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class InspectionController implements IController {

    private Media media;
    private User user;
    @FXML
    private AnchorPane poster;

    @FXML
    private Label title;

    @FXML
    private Label release;

    @FXML
    private Label genre;

    @FXML
    private Label rating;

    @FXML
    private Label seasonLeft;

    @FXML
    private Label seasonRight;

    @FXML
    private Button favoriteButton;

    @FXML
    private Label type;
    @FXML
    private Button play;

    @FXML
    public void initialize() {
        System.out.println("Initializing Inspection");

        media = UserPrefs.selectedMedia;
        user = UserPrefs.currentUser;

        String[] mediaInfo = media.getInfo().split(" ");

        type.setText(mediaInfo[0]);
        title.setText(mediaInfo[1].replaceAll("_", " "));
        release.setText(mediaInfo[2].replaceAll("_", " "));
        genre.setText(mediaInfo[3].replaceAll("_", " "));
        rating.setText(mediaInfo[4]);

        if (mediaInfo.length == 5){
            seasonLeft.setVisible(false);
            seasonRight.setVisible(false);
        }else{
            seasonLeft.setVisible(true);
            seasonRight.setVisible(true);

            seasonRight.setText(mediaInfo[5].replaceAll("_", " "));
        }

        setFavoriteStar();
        poster.setStyle(getImageStyleFromPath(media.getPosterPath()));
    }


    @Override
    public void exit(){
        // Is never called
    }

    @FXML
    public void favorite(){
        if (user.getFavoriteTitles().contains(media.getTitle())){
            user.removeFavorite(media.getTitle());
        }else{
            user.addFavorite(media.getTitle());
        }

        setFavoriteStar();
    }

    private void setFavoriteStar(){
        if (user.getFavoriteTitles().contains(media.getTitle())){
            favoriteButton.setStyle(getImageStyleFromPath("Icons/star_full.png"));
            return;
        }
        favoriteButton.setStyle(getImageStyleFromPath("Icons/star_empty.png"));
    }

    private String getImageStyleFromPath(String path){

        String pathToCSSDir = favoriteButton.getStylesheets().get(0).replaceAll("/Inspection.css", ""); // Gets the path from the stylesheet which was set in Scenebuilder
        return "-fx-background-image: url(\'" + pathToCSSDir + "/"+ path + "\')";
    }
    @FXML
    public void play(){
        play.setStyle("-fx-background-color: white");
    }
}
