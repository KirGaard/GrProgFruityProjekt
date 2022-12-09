package Presentation;


import javafx.fxml.FXML;

import java.io.IOException;

public interface IController {
    @FXML
    void initialize();

    @FXML
    void exit() throws IOException;

}
