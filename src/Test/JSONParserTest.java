package Test;

import DataParser.FileHandler;
import DataParser.JSONParser;
import Database.MediaNotInDatabaseException;
import Media.Media;
import User.User;
import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JSONParserTest {
    private JSONParser jsonParser;

    @BeforeEach
    void setUp() {
        jsonParser = new JSONParser();
    }

    @AfterEach
    void tearDown() {
        jsonParser = null;
    }

    @Test
    void loadFilms() {
    }

    @Test
    void loadShows() {
    }

    @Test
    void loadUsers() {
    }

    @Test
    void saveUsernameWithJson() {
        /*User userName = "omar.json";
        jsonParser.saveUser(userName)*/
    }

    @Test
    void SuccessThrowsRuntimeExceptionIfUserIsNull() {
        String userName = "";
        Throwable exception = assertThrows(RuntimeException.class, () ->
                jsonParser.deleteUser(userName));
        assertEquals("Failed to delete user: " + userName, exception.getMessage());
    }
}