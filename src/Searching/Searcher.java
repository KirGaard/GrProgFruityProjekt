package Searching;

import Database.MediaDatabase;
import Media.Media;
import Sorting.Exceptions.MediaListEmptyException;
import Sorting.Exceptions.MediaListNullPointerException;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Kasper, Victor
 * The following abstract class implements the searcher interface and has a method for creating a pattern.
 * This is the base for the implementation of the decorator pattern
 */

public abstract class Searcher implements ISearcher{
    /**
     * list of all the media which should be searched
     */
    protected List<Media> mediaList;

    /**
     * @param mediaList list of all the media which should be searched
     * @throws MediaListEmptyException in case media list is empty
     * @throws MediaListNullPointerException in case media list has null value
     */
    public Searcher(List<Media> mediaList) throws MediaListEmptyException, MediaListNullPointerException {
        if(mediaList == null) {
            throw new MediaListNullPointerException();
        }
        if(mediaList.size() == 0) {
            throw new MediaListEmptyException();
        }
        this.mediaList = mediaList;
    }

    /**
     * This method takes a search term and creates a waterfall regex pattern
     * This pattern is then used in the decorators to find matches
     * @param searchTerm The term which we want to search for
     * @return We return the compiled pattern to use with a Matcher
     */
    protected Pattern CreatePattern(String searchTerm){
        StringBuilder stringPattern = new StringBuilder();
        char[] searchLetters = searchTerm.toCharArray();

        for (char searchLetter : searchLetters){
            StringBuilder append = stringPattern.append(searchLetter).append(".*");
        }

        return Pattern.compile(stringPattern.toString(), Pattern.CASE_INSENSITIVE);
    }
}
