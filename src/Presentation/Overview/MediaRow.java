package Presentation.Overview;

import Media.Media;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the graphical and logical representation of a row filled with media
 */
public class MediaRow {
    /**
     * The media which is contained in the row
     */
    private ArrayList<Media> media;
    /**
     * A reference to the GUIElements
     */
    private  MediaGUIElement[] GUIElements;
    /**
     * The GridPane containing the arrows and the GUIElemetns
     */
    private GridPane container;
    /**
     * The content pane which is every MediaRows parent
     * This is also the scrollable pane
     */
    private GridPane contentPane;
    /**
     * The current index from which we display the media from the ArrayList in the MediaGUIElements
     */
    private int currentIndex;
    /**
     * The left arrow Button
     */
    private Button leftButton;
    /**
     * The right arrow Button
     */
    private Button rightButton;
    /**
     * The title Label
     */
    private Label titleLabel;

    /**
     * The constructor creates and instantiates all the GUI Objects
     * It also creates all the EventHandlers for the arrows
     * Ends with displaying the media and checking if the row is scrollable
     * @param label The title which should be displayed in the titleLabel
     * @param media An ArrayList with the media which should be displayed
     * @param container The container for all the objects the row creates
     * @param contentPane The parent ScrollablePane which holds all the content
     */
    public MediaRow(String label, ArrayList<Media> media, GridPane container, GridPane contentPane) {
        this.contentPane = contentPane;
        this.container = container;
        this.media = media;
        currentIndex = 0;
        titleLabel = (Label) container.getChildren().get(2);
        titleLabel.setText(label);


        // Since we only display 4 elements at once

        GUIElements = new MediaGUIElement[4];
        for (int i = 0; i < 4; i++) {
            MediaGUIElement element = new MediaGUIElement(media.get(i));
            container.add(element.getGUIElement(contentPane), i + 1, 1);
            GUIElements[i] = element;
        }

        leftButton = (Button) container.getChildren().get(1);
        rightButton = (Button) container.getChildren().get(0);

        leftButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                swipe(-1);
                displayMedia();
            }
        });
        rightButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                swipe(1);
                displayMedia();
            }
        });

        displayMedia();
        boolean scrollable = media.size() > 3;
        rightButton.setVisible(scrollable);
        leftButton.setVisible(scrollable);
    }

    /**
     * Hides or shows the container
     * @param visible showe or hide
     */
    public void setVisible(boolean visible){
        container.setVisible(visible);
    }

    /**
     * Updates the media which should be displayed in the GUIelements and the text in the title.
     * Resets the currentIndex to 0, and checks if the row is scrollable
     * @param mediaRowData
     */
    public void updateMedia(MediaRowData mediaRowData){
        titleLabel.setText(mediaRowData.getTitle());
        media = mediaRowData.getMedia();
        currentIndex = 0;
        displayMedia();

        boolean scrollable = media.size() > 4;
        rightButton.setVisible(scrollable);
        leftButton.setVisible(scrollable);
    }

    /**
     * Displays the correct media in the GUIElements
     * We start by displaying from the current index in the media ArrayList
     * if we overflow when we update the index, we go from 0
     */
    private void displayMedia(){
        for (int i = 0; i < 4; i++) {
            if (i >= media.size()){
                GUIElements[i].setVisible(false);
                continue;
            }

            int index = i + currentIndex;
            if (index >= media.size()) index -= media.size();

            Media newMedia = media.get(index);
            GUIElements[i].updateMedia(newMedia);
            GUIElements[i].setVisible(true);
        }

    }

    /**
     * updates the current index to fit the direction the user is scrolling
     * If the currentIndex overflows it is set to 0 or the max - depending on dir
     * This makes the MediaRow scrollable forever since it repeats when it reaches the end
     * @param dir the direction the user wants to swipe -1 for left and 1 for right
     */
    private void swipe(int dir){
        currentIndex += dir;
        if (currentIndex >= media.size()) currentIndex = 0;
        if (currentIndex < 0) currentIndex = media.size() - 1;
    }


}
