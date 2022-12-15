package Presentation;

/**
 * This class is the main entrypoint in the application
 * When building a .jar in JavaFX we want to avoid having the Application class as the main entrypoint
 * That is why it is here
 */
public class Launcher {
    public static void main(String[] args){
        MainGUI.main(args);
    }
}
