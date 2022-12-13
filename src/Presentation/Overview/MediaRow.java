package Presentation.Overview;

import Media.Media;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class MediaRow {
    private ArrayList<Media> media;
    private  MediaGUIElement[] GUIElements;
    private GridPane container;
    private GridPane contentPane;
    private int currentIndex;

    private Button leftButton;
    private Button rightButton;
    private Label titleLabel;

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

    public void setVisible(boolean visible){
        container.setVisible(visible);
    }

    public void updateMedia(MediaRowData mediaRowData){
        titleLabel.setText(mediaRowData.getTitle());
        media = mediaRowData.getMedia();
        currentIndex = 0;
        displayMedia();

        boolean scrollable = media.size() > 4;
        rightButton.setVisible(scrollable);
        leftButton.setVisible(scrollable);
    }

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

    private void swipe(int dir){
        currentIndex += dir;
        if (currentIndex >= media.size()) currentIndex = 0;
        if (currentIndex < 0) currentIndex = media.size() - 1;
    }


}
