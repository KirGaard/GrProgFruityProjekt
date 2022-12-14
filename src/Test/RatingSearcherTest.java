package Test;

import Database.MediaDatabase;
import Searching.NullQueryException;
import Searching.RatingSearcher;
import Sorting.Exceptions.MediaListEmptyException;
import Sorting.Exceptions.MediaListNullPointerException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RatingSearcherTest {
    private MediaDatabase mediaDatabase;
    private RatingSearcher ratingSearcher;

    @BeforeEach
    void setUp() {
        mediaDatabase = new MediaDatabase();
        try {
            ratingSearcher = new RatingSearcher(mediaDatabase.getAllMedia());
        } catch (MediaListNullPointerException e) {
            throw new RuntimeException(e);
        } catch (MediaListEmptyException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void tearDown() {
        mediaDatabase = null;
        ratingSearcher = null;
    }

    @Test
    void validQueryTest() {
        try {
            assertNotNull(ratingSearcher.Search("test"));
        } catch (NullQueryException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void nullQueryTest() {
        assertThrows(NullQueryException.class, () -> ratingSearcher.Search(null));
    }
}