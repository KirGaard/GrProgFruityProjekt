package Presentation.Overview;

import Media.Media;

import java.util.ArrayList;

/**
 * This is a helper class to better store the data which should be displayed in a MediaRow
 * This is instead of storing a HashMap in an ArrayList
 * This class should be seen as a struct
 */
public class MediaRowData {
    /**
     * The media which should be displayed in the MediaRow
     */
    private ArrayList<Media> media;
    /**
     * The title which should be shown to the MediaRow
     */
    private String title;


    /**
     * A default constructor
     * @param media The media to be set
     * @param title The title to be set
     */
    public MediaRowData(ArrayList<Media> media, String title) {
        this.media = media;
        this.title = title;
    }

    /**
     * A default getter
     * @return media
     */
    public ArrayList<Media> getMedia() {
        return media;
    }

    /**
     * A default getter
     * @return title
     */
    public String getTitle() {
        return title;
    }
}
