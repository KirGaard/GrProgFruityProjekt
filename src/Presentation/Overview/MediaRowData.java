package Presentation.Overview;

import Media.Media;

import java.util.ArrayList;

public class MediaRowData {
    private ArrayList<Media> media;
    private String title;


    public MediaRowData(ArrayList<Media> media, String title) {
        this.media = media;
        this.title = title;
    }

    public ArrayList<Media> getMedia() {
        return media;
    }

    public String getTitle() {
        return title;
    }
}
