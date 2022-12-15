package Searching;

import Media.Media;
import Sorting.Exceptions.MediaListEmptyException;
import Sorting.Exceptions.MediaListNullPointerException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A
 */

public class TitleSearcher extends Searcher {

    /**
     * The following method searches for rating in the list of media
     * @param mediaList list of films or shows
     * @throws MediaListNullPointerException in case media list has null value
     * @throws MediaListEmptyException in case media list is empty
     */
    public TitleSearcher(List<Media> mediaList) throws MediaListNullPointerException, MediaListEmptyException {
        super(mediaList);
    }

    /**
     * The following method allows to search in the media list and returning a list with matching search terms
     * @param searchTerm the title we're looking for
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

            Matcher matcher = pattern.matcher(media.getTitle().toLowerCase());
            if (matcher.find()) matchingMedia.add(media);
        }

        return matchingMedia;

    }



}
