package Database;

import DataAccess.DataAccessor;
import DataParser.JSONParser;
import Media.Film;
import Media.Media;
import Media.Show;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores all media while also supporting the function to get media from the database.
 */
public class MediaDatabase {
    /**
     * The list that films are stored in.
     */
    private List<Media> films;
    /**
     * The list shows are stored in.
     */
    private List<Media> shows;
    /**
     * The list that media is stored in.
     */
    private List<Media> media;

    /**
     * A constructor of MediaDatabase. It instantiates and initializes DataAccessor, which is used to get all films and shows and store them in the list of media.
     */
    public MediaDatabase(){
        DataAccessor dataAccessor = new DataAccessor();

        this.films = dataAccessor.getFilms();
        this.shows = dataAccessor.getShows();

        List media = new ArrayList<Media>();
        media.addAll(this.films);
        media.addAll(this.shows);

        this.media = media;
    }

    /**
     * Gets all films from the list of media.
     * @return all the films.
     */
    public List<Media> getFilms(){
        return films;
    }

    /**
     * Gets all shows from the list of media.
     * @return all the shows.
     */
    public List<Media> getShows(){
        return shows;
    }

    /**
     * Gets all media from the list of media.
     * @return all media.
     */
    public List<Media> getAllMedia(){
        return media;
    }

    /**
     * Gets all media of the list of media that has a specific title.
     * @return returns the media that has a specific title.
     */
    private List<String> allMediaByTitle(){
        List out = new ArrayList<String>();
        media.forEach(media -> out.add(media.getTitle()));
        return out;
    }

    /**
     * Checks to see if a title of media is contained in the list of all media.
     * @param mediaTitle the title of the media.
     * @return a boolean that is true if the title is in the list of media and false if the title is not in the list of media.
     */
    public boolean containsTitle(String mediaTitle){
        return allMediaByTitle().contains(mediaTitle);
    }

    /**
     * Gets a specific media with a specific title.
     * @param title the title of the media.
     * @return the index of the title of the media.
     * @throws MediaNotInDatabaseException if the title of the media is not found in the database.
     */
    public Media getMediaByTitle(String title) throws MediaNotInDatabaseException{
        List<String> allTitles = allMediaByTitle();
        if (!allTitles.contains(title)) throw new MediaNotInDatabaseException(title);

        int indexOfTitle = allTitles.indexOf(title);
        return media.get(indexOfTitle);
    }

}
