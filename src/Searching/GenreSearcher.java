package Searching;

import Media.Media;
import Sorting.Exceptions.MediaListEmptyException;
import Sorting.Exceptions.MediaListNullPointerException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Kasper, Victor
 * The following class allows to search by genre and implements the Searcher interface
 */

public class GenreSearcher extends Searcher{

    /**
     * The following method searches for genres in the list of media
     * @param mediaList list of both films or shows
     * @throws MediaListNullPointerException in case media list has null value
     * @throws MediaListEmptyException in case media list is empty
     */

    public GenreSearcher(List<Media> mediaList) throws MediaListNullPointerException, MediaListEmptyException {
        super(mediaList);
    }

    /**
     * The following method allows to search in the media list and returning a list with matching search terms
     * @param searchTerm the term we want to look for, could be genre, title etc
     * @return list of media that matches with search term
     * @throws NullQueryException in case null query is empty
     */
    @Override
    public List<Media> Search(String searchTerm) throws NullQueryException {
        if(searchTerm == null) {
            throw new NullQueryException();
        }
        Pattern pattern = CreatePattern(searchTerm.toLowerCase());

        List<Media> matchingMedia = new ArrayList<Media>();
        for (Media media : mediaList) {

            for (String genre : media.getGenre()){
                Matcher matcher = pattern.matcher(genre.toLowerCase());
                if (matcher.find()){
                    matchingMedia.add(media);
                    break;
                }
            }

        }

        return matchingMedia;
    }
}
