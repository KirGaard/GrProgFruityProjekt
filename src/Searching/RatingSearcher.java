package Searching;

import Media.Media;
import Sorting.Exceptions.MediaListEmptyException;
import Sorting.Exceptions.MediaListNullPointerException;

import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RatingSearcher extends Searcher{
    public RatingSearcher(List<Media> mediaList) throws MediaListNullPointerException, MediaListEmptyException {
        super(mediaList);
    }

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
