package Presentation.Overview;

import Presentation.IController;
import javafx.fxml.FXML;

public class OverviewController implements IController {

    @FXML
    public void initialize()
    {
        System.out.println("Initializing Overview");
    }

    @Override
    public void exit() {

    }

}
