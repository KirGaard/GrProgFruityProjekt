package Presentation;

import java.util.Scanner;

public class LoginUI implements IPresenter {
    public static void main(String[] args) {
        LoginUI loginUI = new LoginUI();
        loginUI.init();
    }

    @Override
    public void init() {
        System.out.println("Welcome to iFruity, please provide the according username and password for the user you want to use");
        System.out.println("Username:");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        if (username == "") {
            throw new NullPointerException("Invalid username");
        }
        System.out.println("Password:");
        String password = scanner.nextLine();
        if (password == "") {
            throw new NullPointerException("Invalid password");
        }
        System.out.println("Login success");
    }

    @Override
    public void exit() {

    }
}
