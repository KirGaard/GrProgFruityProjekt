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

    }

    @Override
    public void exit() {

    }
}
