package Presentation;

import Database.MediaDatabase;
import Database.MediaNotInDatabaseException;

import java.util.Scanner;

public class DisplayUI implements IPresenter {
    @Override
    public void init() {
        System.out.println(media.getInfo());
    }

    @Override
    public void exit() {

    }
}
