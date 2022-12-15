package Presentation;

import Database.UserDatabase;
import Media.Media;
import User.UserPrefs;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * This class is where the MainGUI is handled.
 * This class is responsible for storing the main user database and responsible for the scene handling
 */
public class MainGUI extends Application {
    /**
     * The main stage of the program. This is the Stage Login and Overview uses
     */
    private static Stage mainStage;
    /**
     * The stage used in the inspection
     */
    private static Stage inspectionStage;
    /**
     * The stage used in the user creation
     */
    private static Stage userCreationStage;
    /**
     * The main user database which is saved when to program closes
     */
    public static UserDatabase userDatabase;

    /**
     * This method instantiates the main UserDatabase and launches the JavaFX application
     * @param args program arguments
     */
    public static void main(String[] args) {
        userDatabase = new UserDatabase();
        launch(args);

    }

    /**
     * This method is called from the JavaFX application when the programs start.
     * Here we load the Login Fxml file and create all the stages.
     * We handle the user database saving on close
     * @param stage The main stage of the program passed down from the application
     * @throws Exception Propagated down from parent class
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login/Login.fxml"));

        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();

        mainStage = stage;
        inspectionStage = new Stage();
        userCreationStage = new Stage();
        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                userDatabase.saveDatabase();
            }
        });
    }

    /**
     * This function is called when the user has succesfully logged in
     * This function sets the new scene to the Overview.fxml and maximazes the window
     * @throws IOException if the fxml file is not found
     */
    public static void signIn() throws IOException {
        System.out.println("Signing in...");
        Parent overviewRoot = FXMLLoader.load(MainGUI.class.getResource("Overview/Overview.fxml"));

        mainStage.setTitle("Overview");
        mainStage.setScene(new Scene(overviewRoot));

        // Some wtf magic to make the scene appear in fullscreen again... Otherwise, it would be minimized in the top left corner
        mainStage.hide();
        mainStage.setMaximized(false);
        mainStage.setMaximized(true);
        mainStage.show();

    }

    /**
     * This function is called when the user inspects a media
     * Opens and sets the stage for the Inspection.fxml file
     * @param media
     * @throws IOException
     */
    public static void inspectMedia(Media media) throws IOException {
        inspectionStage.setTitle(media.getTitle());
        UserPrefs.selectedMedia = media;
        Parent root = FXMLLoader.load(MainGUI.class.getResource("Inspection/Inspection.fxml"));

        inspectionStage.setScene(new Scene(root));
        inspectionStage.setMaximized(false);
        inspectionStage.show();
    }

    /**
     * This function is called when the user pressed the "Ny Bruger" button in the Login scene
     * This function opens and sets the stage for the NewUser.fxml file
     */
    public static void createNewUser(){
        userCreationStage.setTitle("Ny Bruger");

        Parent root;
        try {
            root = FXMLLoader.load(MainGUI.class.getResource("Login/NewUser.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        userCreationStage.setScene(new Scene(root));
        userCreationStage.show();
    }

    /**
     * This function hides the userCreationStage when a new user has been successfully created
     */
    public static void userCreationComplete(){
        userCreationStage.hide();
    }

    /**
     * This function is called from the overview scene when the user tries to log out
     * This function opens and sets the stage for the Login.fxml scene
     */
    public static void signOut(){
        Parent root;
        try {
            root = FXMLLoader.load(MainGUI.class.getResource("Login/Login.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mainStage.setTitle("Login");
        mainStage.setScene(new Scene(root));

        mainStage.hide();
        mainStage.setMaximized(false);
        mainStage.setMaximized(true);
        mainStage.show();
    }


}