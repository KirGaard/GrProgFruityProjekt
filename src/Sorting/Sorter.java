package Sorting;

import Media.Media;
import Sorting.Exceptions.MediaListEmptyException;
import Sorting.Exceptions.MediaListNullPointerException;

import java.util.List;

/**
 * @author Kasper, Victor
 * The following abstract class implements the sorter interface and has a method for sorting
 */

public abstract class Sorter implements ISorter{

    /**
     * Instantiates a list media of type media that is unsorted
     */

    protected List<Media> unsortedMediaList;

    /**
     * The following method throws a MediaListNullPointerException if media list has value null
     * Throws MediaListEmptyException if media list is empty
     * And sorts otherwise
     * @param unsortedMediaList an unsorted media list of films or shows
     * @throws MediaListNullPointerException in case media list has value null
     * @throws MediaListEmptyException in case media list is empty
     */

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
