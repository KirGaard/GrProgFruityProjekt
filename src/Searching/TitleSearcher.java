package Searching;

import Media.Media;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TitleSearcher extends Searcher {
    public TitleSearcher(List<Media> mediaList) {
        super(mediaList);
    }

    @Override
    public List<Media> Search(String searchTerm) {
        Pattern pattern = CreatePattern(searchTerm.toLowerCase());

        List<Media> matchingMedia = new ArrayList<Media>();
        for (Media media : mediaList) {

            Matcher matcher = pattern.matcher(media.getTitle().toLowerCase());
            if (matcher.find()) matchingMedia.add(media);
        }

        return matchingMedia;

    }



}
