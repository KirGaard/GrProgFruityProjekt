package Sorting;

import Media.Media;
import Sorting.Exceptions.MediaListEmptyException;
import Sorting.Exceptions.MediaListNullPointerException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenreSorter extends Sorter{
    private HashMap<String, ArrayList<Media>> sortedMap;

    public GenreSorter(List<Media> unsortedMediaList) throws MediaListNullPointerException, MediaListEmptyException {
        super(unsortedMediaList);
        sortedMap = new HashMap<>();
    }

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

    public List<Map.Entry<String, ArrayList<Media>>> getSortedEntryList(){
        return sortedMap.entrySet().stream().toList();


    }
}
