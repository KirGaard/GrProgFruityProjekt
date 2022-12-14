package Searching;

import Database.MediaDatabase;
import Media.Media;
import Sorting.Exceptions.MediaListEmptyException;
import Sorting.Exceptions.MediaListNullPointerException;

import java.util.List;
import java.util.regex.Pattern;

public abstract class Searcher implements ISearcher{
    protected List<Media> mediaList;

    public Searcher(List<Media> mediaList) throws MediaListEmptyException, MediaListNullPointerException {
        if(mediaList == null) {
            throw new MediaListNullPointerException();
        }
        if(mediaList.size() == 0) {
            throw new MediaListEmptyException();
        }
        this.mediaList = mediaList;
    }

    protected Pattern CreatePattern(String searchTerm){
        StringBuilder stringPattern = new StringBuilder();
        char[] searchLetters = searchTerm.toCharArray();

        for (char searchLetter : searchLetters){
            StringBuilder append = stringPattern.append(searchLetter).append(".*");
        }

        return Pattern.compile(stringPattern.toString(), Pattern.CASE_INSENSITIVE);
    }
}
