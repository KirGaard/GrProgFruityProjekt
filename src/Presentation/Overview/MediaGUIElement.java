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

/**
 * This class is responsible for creating and updating a GUIElement which displays an instance of a media
 */
public class MediaGUIElement {
    /**
     * The reference to the media currently displayed
     */
    private Media media;
    /**
     * A reference the main button in the element
     */
    private Button button;
    /**
     * A reference to the label which displays the title of media
     */
    private Label title;

    /**
     * The constructor assigns the reference to the declaration
     * @param media the first media to be displayed
     */
    public MediaGUIElement(Media media){
        this.media = media;
    }

    /**
     * This method creates the GUIElement with all the components
     * Creates all the minor objects and sets the correct layouts and styles to them
     * Also correctly creates EventHandlers to handle the user clicking on the GUIElement
     * @param parent The parent of the GUIElement - in truth this is the media row. Used to get the correct path to the style sheet
     *               To help with the correct assignment of the .css
     * @return
     */
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

    /**
     * This method hides / shows the GUI objects
     * @param visible if it should be visible or not
     */
    public void setVisible(boolean visible){
        button.setVisible(visible);
        title.setVisible(false);
    }

    /**
     * Updates the media which should be displayed.
     * Also updates all the graphical objects to display the new media
     * Setting the style and setting the title
     * @param media The new media to be displayed
     */
    public void updateMedia(Media media){
        this.media = media;

        button.setStyle(getImageStyleFromPath(media.getPosterPath()));
        title.setText(media.getTitle());
    }

    /**
     * Sets the mouse events for the button
     * Shows the title only when the button is hovered
     */
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
