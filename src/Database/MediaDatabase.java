package Database;

import DataAccess.DataAccessor;
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
        DataAccessor dataAccessor = new DataAccessor();

        this.films = dataAccessor.getFilms();
        this.shows = dataAccessor.getShows();

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

    private List<String> allMediaByTitle(){
        List out = new ArrayList<String>();
        media.forEach(media -> out.add(media.getTitle()));
        return out;
    }
    public Media getMediaByTitle(String title) throws MediaNotInDatabaseException{
        List<String> allTitles = allMediaByTitle();
        if (!allTitles.contains(title)) throw new MediaNotInDatabaseException(title);

        int indexOfTitle = allTitles.indexOf(title);
        return media.get(indexOfTitle);

    }

}
