package Sorting;

import Media.Media;
import Sorting.Exceptions.MediaListEmptyException;
import Sorting.Exceptions.MediaListNullPointerException;

import java.util.List;

public abstract class Sorter implements ISorter{
    protected List<Media> unsortedMediaList;

    public Sorter(List<Media> unsortedMediaList) throws MediaListNullPointerException, MediaListEmptyException {
        if(unsortedMediaList == null) {
            throw new MediaListNullPointerException();
        }
        if(unsortedMediaList.size() == 0) {
            throw new MediaListEmptyException();
        }

        this.unsortedMediaList = unsortedMediaList;
    }
}
