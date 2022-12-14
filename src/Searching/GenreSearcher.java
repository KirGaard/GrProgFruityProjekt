package Searching;

import Media.Media;
import Sorting.Exceptions.MediaListEmptyException;
import Sorting.Exceptions.MediaListNullPointerException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenreSearcher extends Searcher{
    public GenreSearcher(List<Media> mediaList) throws MediaListNullPointerException, MediaListEmptyException {
        super(mediaList);
    }

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
