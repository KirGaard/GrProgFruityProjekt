package Presentation;

import java.util.Objects;
import java.util.Scanner;

public class LoginUI implements IPresenter {
    public static void main(String[] args) {
        /*LoginUI loginUI = new LoginUI();
        loginUI.init();*/
        OverviewUI overviewUI = new OverviewUI();
        overviewUI.init();
    }

    @Override
    public void init() {
        System.out.println("Welcome to iFruity, please provide login information");
        System.out.println("Username:");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        if (Objects.equals(username, "")) {
            System.out.println("Login failed");
            throw new NullPointerException("Invalid username");
        }
        System.out.println("Password:");
        String password = scanner.nextLine();
        if (Objects.equals(password, "")) {
            System.out.println("Login failed");
            throw new NullPointerException("Invalid password");
        }
        System.out.println("Login success");
    }

    @Override
    public void exit() {

    }
}
