package Presentation;

import Database.MediaDatabase;
import Media.Media;

import java.util.Scanner;

public class OverviewUI implements IPresenter {
    @Override
    public void init() {
        System.out.println("Welcome to the home page");
        final MediaDatabase mediaDatabase = new MediaDatabase();
        System.out.println("Do you wish to browse films or shows? Choose 'Films' or 'Shows'");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();
        if (input.equals("films")) {
            for (Media media : mediaDatabase.getFilms()) {
                System.out.println(media.getTitle());
            }
        } else if (input.equals("shows")) {
            for (Media media : mediaDatabase.getShows()) {
                System.out.println(media.getTitle());
            }
        } else {
            throw new IllegalArgumentException("Invalid argument, please choose films or movies");
        }

    }

    @Override
    public void exit() {

    }
}
