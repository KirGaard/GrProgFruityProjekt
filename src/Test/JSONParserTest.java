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
        assertThrows(RuntimeException.class, () -> jsonParser.deleteUser(username));
    }

    @Test
    void deleteUsernameIsNullTest() {
        String username = null;
        assertThrows(RuntimeException.class, () -> jsonParser.deleteUser(username));
    }
}