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

/**
 * This class handles the logic of the Inspection.fxml scene
 */
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

    /**
     * Called when the scene is first loaded
     * Initialises the user and media fields to the fields from the userPrefs
     * Initialises the text of all the Labels in the scene.
     * Since we only have a reference to a Media and not Film or Show, we use the getInfo() method
     * And format the text based on the formatting style from the geiInfo() method.
     * This a way of going around object casting
     */
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

        // We only show the season labels if we are dealing with a movie
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


    /**
     * Is never called, since we never exit this scene - just close it
     */
    @Override
    public void exit(){
        // Is never called
    }

    /**
     * Called when the user clicks the favorite Button
     * If the User favorite List already contains the media title remove it otherwise we add it
     * After we call setFavoriteStar to update the image
     */
    @FXML
    public void favorite(){
        if (user.getFavoriteTitles().contains(media.getTitle())){
            user.removeFavorite(media.getTitle());
        }else{
            user.addFavorite(media.getTitle());
        }

        setFavoriteStar();
    }

    /**
     *  Updates the style of the favorite button so the .css references the correct star image
     */
    private void setFavoriteStar(){
        if (user.getFavoriteTitles().contains(media.getTitle())){
            favoriteButton.setStyle(getImageStyleFromPath("Icons/star_full.png"));
            return;
        }
        favoriteButton.setStyle(getImageStyleFromPath("Icons/star_empty.png"));
    }

    /**
     * This method gets the correct image style from the given path
     * This is necesarry since JavaFX uses the path from the src folder, so we find it dynamically at runtime
     * @param path The relative path we wind to find the absolute path for
     * @return returns the absolute correct path
     */
    private String getImageStyleFromPath(String path){
        // Gets the path from the stylesheet which was set in Scenebuilder
        String pathToCSSDir = favoriteButton.getStylesheets().get(0).replaceAll("/Inspection.css", "");
        return "-fx-background-image: url(\'" + pathToCSSDir + "/"+ path + "\')";
    }

    /**
     * When the play button is pressed
     * we change the background color to white, to simulate playing the video
     */
    @FXML
    public void play(){
        play.setStyle("-fx-background-color: white");
    }
}
