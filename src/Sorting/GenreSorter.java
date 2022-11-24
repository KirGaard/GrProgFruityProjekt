package Sorting;

import Media.Media;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GenreSorter extends Sorter{
    private HashMap<String, List<Media>> sortedMap;

    public GenreSorter(List<Media> unsortedMediaList) {
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
                    List<Media> newList = new ArrayList<>();
                    newList.add(media);

                    sortedMap.put(genre, newList);
                }
            }
        }
    }

    public HashMap<String, List<Media>> getSortedMap(){
        return sortedMap;


    }
}
