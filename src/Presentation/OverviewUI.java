package Presentation;

import Database.MediaDatabase;
import Media.Media;

import java.util.Scanner;

public class OverviewUI implements IPresenter {
    @Override
    public void init() {
        System.out.println("Welcome to the homepage, do you wish to browse films or shows. Please choose 'films' or 'shows'");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();
        MediaDatabase mediaDatabase = new MediaDatabase();
        if (input.equals("films")) {
            for (Media media : mediaDatabase.getFilms()) {
                System.out.println(media.getTitle());
            }
            System.out.println("\nWhich film would you like to view?");
        } else if (input.equals("shows")) {
            for (Media media : mediaDatabase.getShows()) {
                System.out.println(media.getTitle());
            }
            System.out.println("\nWhich show would you like to view?");
        }
    }

    @Override
    public void exit() {

    }
}
