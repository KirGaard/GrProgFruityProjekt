package Presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage mainStage;

    public static void main(String[] args) {
        launch(args);

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

        mainStage = stage;
    }

    public static void signIn() throws IOException {
        System.out.print("Signing in...");
        Parent overviewRoot = FXMLLoader.load(Main.class.getResource("Login/Login.fxml"));

        mainStage.setTitle("Overview");
        mainStage.setScene(new Scene(overviewRoot));
        mainStage.setMaximized(true);
        mainStage.show();

    }
}