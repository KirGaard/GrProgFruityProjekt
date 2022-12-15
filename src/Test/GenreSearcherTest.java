package Test;

import Database.MediaDatabase;
import Searching.NullQueryException;
import Searching.GenreSearcher;
import Sorting.Exceptions.MediaListEmptyException;
import Sorting.Exceptions.MediaListNullPointerException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenreSearcherTest {
    private MediaDatabase mediaDatabase;
    private GenreSearcher genreSearcher;

    @BeforeEach
    void setUp() {
       mediaDatabase = new MediaDatabase();
        try {
            genreSearcher = new GenreSearcher(mediaDatabase.getAllMedia());
        } catch (MediaListNullPointerException e) {
            throw new RuntimeException(e);
        } catch (MediaListEmptyException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void tearDown() {
        mediaDatabase = null;
        genreSearcher = null;
    }

    @Test
    void validQueryTest() {
        try {
            assertNotNull(genreSearcher.Search("test"));
        } catch (NullQueryException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void nullQueryTest() {
        assertThrows(NullQueryException.class, () -> genreSearcher.Search(null));
    }
}