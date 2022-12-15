package Presentation;


import javafx.fxml.FXML;

import java.io.IOException;

/**
 * An interface all the controllers inherits from
 */
public interface IController {
    //@FXML
    //public void initialize();

    /**
     * Called when the scene tries to exit
     */
    @FXML
    void exit();

}
