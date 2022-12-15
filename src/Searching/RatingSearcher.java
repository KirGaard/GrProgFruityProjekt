package Searching;

import Media.Media;
import Sorting.Exceptions.MediaListEmptyException;
import Sorting.Exceptions.MediaListNullPointerException;

import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Kasper, Victor
 * The following class allows to search by rating and implements the Searcher interface
 */

public class RatingSearcher extends Searcher{

    /**
     * The following method searches for rating in the list of media
     * @param mediaList list of films or shows
     * @throws MediaListNullPointerException in case media list has null value
     * @throws MediaListEmptyException in case media list is empty
     */

    public RatingSearcher(List<Media> mediaList) throws MediaListNullPointerException, MediaListEmptyException {
        super(mediaList);
    }

    /**
     * The following method allows to search in the media list and returning a list with matching search terms
     * @param searchTerm the rating we're looking for
     * @return list of media that matches with search term
     * @throws NullQueryException in case null query is empty
     */

    @Override
    public List<Media> Search(String searchTerm) throws NullQueryException {
        if(searchTerm == null) {
            throw new NullQueryException();
        }
        HashSet<Media> matchingMedia = new HashSet<Media>();

        Pattern pattern = Pattern.compile("[0-9]+\\.[0-9]*|[0-9]+");
        Matcher matcher = pattern.matcher(searchTerm);

        if (!matcher.find()) return matchingMedia.stream().toList();

        double searchRating = Double.parseDouble(matcher.group(0));

        for(Media media : mediaList){
            if (media.getRating() >= searchRating){
                matchingMedia.add(media);
            }
        }

        return matchingMedia.stream().toList();
    }
}
