package Sorting;

import Media.Media;

import java.util.List;

public abstract class Sorter implements ISorter{
    protected List<Media> unsortedMediaList;

    public Sorter(List<Media> unsortedMediaList){
        this.unsortedMediaList = unsortedMediaList;
    }
}
