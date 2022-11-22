package Database;

import DataParser.JSONParser;
import Media.Film;
import Media.Media;
import Media.Show;

import java.util.ArrayList;
import java.util.List;

public class MediaDatabase {
    private List<Media> films;
    private List<Media> shows;

    private List<Media> media;

    public MediaDatabase(){
        this.films = JSONParser.getFilms();
        this.shows = JSONParser.getShows();

        List media = new ArrayList<Media>();
        media.addAll(this.films);
        media.addAll(this.shows);

        this.media = media;


    }
    public List<Media> getFilms(){
        return films;
    }

    public List<Media> getShows(){
        return shows;
    }

    public List<Media> getAllMedia(){
        return media;
    }



}
