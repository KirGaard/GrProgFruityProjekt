package Sorting;

import Media.Media;
import Sorting.Exceptions.MediaListEmptyException;
import Sorting.Exceptions.MediaListNullPointerException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for sorting genre that extends the sorter abstract class
 */

public class GenreSorter extends Sorter{

    /**
     * Instantiates a sortedMap HashMap with key String and value of an ArrayList with Media as type
     */

    private HashMap<String, ArrayList<Media>> sortedMap;

    /**
     * The following method sorts genre and initializes a new HashMap that is sorted
     * @param unsortedMediaList media list of films or shows that is unsorted
     * @throws MediaListNullPointerException in case media list has value null
     * @throws MediaListEmptyException in case media list is empty
     */

    public GenreSorter(List<Media> unsortedMediaList) throws MediaListNullPointerException, MediaListEmptyException {
        super(unsortedMediaList);
        sortedMap = new HashMap<>();
    }

    /**
     * The following method sorts the unsorted list of media and matching genre
     */

    @Override
    public void sort() {
        for (Media media : unsortedMediaList){
            List<String> genres = media.getGenre();

            for (String genre : genres){
                if (sortedMap.containsKey(genre)){
                    sortedMap.get(genre).add(media);
                }else{
                    ArrayList<Media> newList = new ArrayList<>();
                    newList.add(media);

                    sortedMap.put(genre, newList);
                }
            }
        }
    }

    /**
     * The following method returns a sorted list of genre
     * @return sorted list of genre and media that match with the genre
     */

    public List<Map.Entry<String, ArrayList<Media>>> getSortedEntryList(){
        return sortedMap.entrySet().stream().toList();


    }
}
