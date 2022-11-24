package Presentation;

import Database.MediaDatabase;
import Database.MediaNotInDatabaseException;
import Media.Media;

import java.util.Scanner;

public class DisplayUI implements IPresenter {
    private Media media;

    DisplayUI(Media media) {
        this.media = media;
    }

    @Override
    public void init() {
        System.out.println(media.getInfo());
    }

    @Override
    public void exit() {

    }
}
