package Test;

import Database.MediaDatabase;
import Database.MediaNotInDatabaseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MediaDatabaseTest {
    private MediaDatabase mediaDatabase;

    @BeforeEach
    void setUp() {
        mediaDatabase = new MediaDatabase();
    }

    @AfterEach
    void tearDown() {
        mediaDatabase = null;
    }

    @Test
    void titleNotInMediaTest() {
        Throwable exception = assertThrows(MediaNotInDatabaseException.class, () ->
                mediaDatabase.getMediaByTitle("Avatar 2"));
        assertEquals("Avatar 2", exception.getMessage());
    }

    @Test
    void titleCapitalizationTest() {
        Throwable exception = assertThrows(MediaNotInDatabaseException.class, () ->
                mediaDatabase.getMediaByTitle("raIderS oF ThE loST ARK"));
        assertEquals("raIderS oF ThE loST ARK", exception.getMessage());
    }

    @Test
    void titleLetterSpacesTest() {
        Throwable exception = assertThrows(MediaNotInDatabaseException.class, () ->
                mediaDatabase.getMediaByTitle("RaidersOfTheLostArk"));
        assertEquals("RaidersOfTheLostArk", exception.getMessage());
    }
}