package Presentation;

import Database.UserDatabase;
import User.User;

import java.util.Scanner;

public class LoginUI implements IPresenter {
    public static void main(String[] args) {
        LoginUI loginUI = new LoginUI();
        loginUI.init();
        OverviewUI overviewUI = new OverviewUI();
        overviewUI.init();

    }

    public void init() {
        UserDatabase userDatabase = new UserDatabase();

        System.out.println("Welcome to iFruity, please provide the according login info");
        System.out.println("Username:");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine().toLowerCase();
        if (username.equals("")) {
            throw new IllegalArgumentException("Invalid username, cannot be empty");
        }
        if (!userDatabase.ContainsUser(username)) {
            System.out.println("Creating new user... \nPlease input a password:");
            String password = scanner.nextLine();

            User newUser = new User();
            newUser.changeUsername(username);
            newUser.changePassword(password);

            userDatabase.addUser(newUser);
            System.out.println("User created");
        } else {
            System.out.println("Password:");
            String password = scanner.nextLine().toLowerCase();
            if (password.equals("")) {
                throw new IllegalArgumentException("Invalid password, cannot be empty");
            }
            System.out.println("Login success");

        }

    }

    @Override
    public void exit() {

    }
}
