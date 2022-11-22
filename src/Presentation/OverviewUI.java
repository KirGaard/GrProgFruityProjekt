package Presentation;

import Database.MediaDatabase;

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
            mediaDatabase.getFilms();
        } else if (input.equals("shows")) {
            mediaDatabase.getShows();
        } else {
            throw new IllegalArgumentException("Invalid argument, please choose films or movies");
        }

    }

    @Override
    public void exit() {

    }
}
