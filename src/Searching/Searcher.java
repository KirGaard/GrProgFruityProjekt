package Searching;

import Database.MediaDatabase;
import Media.Media;

import java.util.List;

public abstract class Searcher implements ISearcher{
    protected List<Media> mediaList;

    public Searcher(List<Media> mediaList){
        this.mediaList = mediaList;
    }

}
