package Searching;

import Media.Media;
import Sorting.Exceptions.MediaListEmptyException;
import Sorting.Exceptions.MediaListNullPointerException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TitleSearcher extends Searcher {
    public TitleSearcher(List<Media> mediaList) throws MediaListNullPointerException, MediaListEmptyException {
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

            Matcher matcher = pattern.matcher(media.getTitle().toLowerCase());
            if (matcher.find()) matchingMedia.add(media);
        }

        return matchingMedia;

    }



}
