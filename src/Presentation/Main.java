package Presentation;

import Database.MediaDatabase;
import Database.MediaNotInDatabaseException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class Main extends Application {
    public static void main(String[] args) {
        MediaDatabase mediaDatabase = new MediaDatabase();
    }
    /**
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login/Login.fxml"));


        stage.setTitle("Hello World!");
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();
    }

    public static void signIn() {
        System.out.print("Signing in...");
    }
}