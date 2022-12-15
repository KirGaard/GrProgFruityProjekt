package Searching;

import Database.MediaDatabase;
import Media.Media;
import Sorting.Exceptions.MediaListEmptyException;
import Sorting.Exceptions.MediaListNullPointerException;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Kasper, Victor
 * The following abstract class implements the searcher interface and has a method for creating a pattern
 */

public abstract class Searcher implements ISearcher{
    /**
     * Instantiates list media list of type media
     */
    protected List<Media> mediaList;

    /**
     *
     * @param mediaList list of films or shows
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

    // Todo: ikke sikker hvad der sker her
    /**
     *
     * @param searchTerm
     * @return
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
