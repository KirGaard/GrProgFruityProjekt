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
    void deleteUsernameIsEmptyStringTest() {
        String username = "";
        Throwable exception = assertThrows(RuntimeException.class, () ->
                jsonParser.deleteUser(username));
        assertEquals("Failed to delete user: " + username, exception.getMessage());
    }

    @Test
    void deleteUsernameIsNullTest() {
        String username = null;
        Throwable exception = assertThrows(RuntimeException.class, () ->
                jsonParser.deleteUser(username));
        assertEquals("Failed to delete user: " + username, exception.getMessage());
    }
}