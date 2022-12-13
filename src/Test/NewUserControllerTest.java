package Test;

import Presentation.Login.NewUserController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewUserControllerTest {
    private NewUserController newUserController;

    @BeforeEach
    void setUp() {
        newUserController = new NewUserController();
    }

    @AfterEach
    void tearDown() {
        newUserController = null;
    }

    @Test
    void createUserThatExistsTest() {
        //We can only test this while the program is running, since we use the buttons.
    }
}