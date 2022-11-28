package Searching;

import Media.Media;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenreSearcher extends Searcher{
    public GenreSearcher(List<Media> mediaList) {
        super(mediaList);
    }

    @Override
    public List<Media> Search(String searchTerm) {
        Pattern pattern = CreatePattern(searchTerm);

        List<Media> matchingMedia = new ArrayList<Media>();
        for (Media media : mediaList) {

            for (String genre : media.getGenre()){
                Matcher matcher = pattern.matcher(genre);
                if (matcher.find()){
                    matchingMedia.add(media);
                    break;
                }
            }

        }

        return matchingMedia;
    }
}
