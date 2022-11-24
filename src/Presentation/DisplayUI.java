package Presentation;

import Database.MediaDatabase;
import Database.MediaNotInDatabaseException;

import java.util.Scanner;

public class DisplayUI implements IPresenter {
    @Override
    public void init() {
        MediaDatabase mediaDatabase = new MediaDatabase();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            System.out.println(mediaDatabase.getMediaByTitle(input).getGenre());
        } catch (MediaNotInDatabaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void exit() {

    }
}
