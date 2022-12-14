package Test;

import Database.MediaDatabase;
import Searching.NullQueryException;
import Searching.TitleSearcher;
import Sorting.Exceptions.MediaListEmptyException;
import Sorting.Exceptions.MediaListNullPointerException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TitleSearcherTest {
    private MediaDatabase mediaDatabase;
    private TitleSearcher titleSearcher;

    @BeforeEach
    void setUp() {
        mediaDatabase = new MediaDatabase();
        try {
            titleSearcher = new TitleSearcher(mediaDatabase.getAllMedia());
        } catch (MediaListNullPointerException e) {
            throw new RuntimeException(e);
        } catch (MediaListEmptyException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void tearDown() {
        mediaDatabase = null;
        titleSearcher = null;
    }

    @Test
    void validQueryTest() {
        try {
            assertNotNull(titleSearcher.Search("test"));
        } catch (NullQueryException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void nullQueryTest() {
        assertThrows(NullQueryException.class, () -> titleSearcher.Search(null));
    }
}