package Presentation;

import Database.UserDatabase;
import Media.Media;
import User.UserPrefs;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application {
    private static Stage mainStage;
    private static Stage inspectionStage;
    private static Stage userCreationStage;
    public static UserDatabase userDatabase;

    public static void main(String[] args) {
        userDatabase = new UserDatabase();
        launch(args);

    }

    /**
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login/Login.fxml"));
        //root.getStylesheets().add(getClass().getResource("CSS/Overview.css").toString());

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

    public static void signIn() throws IOException {
        System.out.println("Signing in...");
        Parent overviewRoot = FXMLLoader.load(Main.class.getResource("Overview/Overview.fxml"));

        mainStage.setTitle("Overview");
        mainStage.setScene(new Scene(overviewRoot));

        // Some wtf magic to make the scene appear in fullscreen again... Otherwise, it would be minimized in the top left corner
        mainStage.hide();
        mainStage.setMaximized(false);
        mainStage.setMaximized(true);
        mainStage.show();

    }
    public static void inspectMedia(Media media) throws IOException {
        inspectionStage.setTitle(media.getTitle());
        UserPrefs.selectedMedia = media;
        Parent root = FXMLLoader.load(Main.class.getResource("Inspection/Inspection.fxml"));

        inspectionStage.setScene(new Scene(root));
        inspectionStage.setMaximized(false);
        inspectionStage.show();
    }
    public static void createNewUser(){
        userCreationStage.setTitle("Ny Bruger");

        Parent root;
        try {
            root = FXMLLoader.load(Main.class.getResource("Login/NewUser.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        userCreationStage.setScene(new Scene(root));
        userCreationStage.show();
    }

    public static void userCreationComplete(){
        userCreationStage.hide();
    }

    public static void signOut(){
        Parent root;
        try {
            root = FXMLLoader.load(Main.class.getResource("Login/Login.fxml"));
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