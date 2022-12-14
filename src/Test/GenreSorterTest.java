package Test;

import Database.MediaDatabase;
import Media.Media;
import Sorting.GenreSorter;
import Sorting.Exceptions.MediaListEmptyException;
import Sorting.Exceptions.MediaListNullPointerException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenreSorterTest {
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
    void mediaListNullTest() {
        List<Media> mediaList = null;
        assertThrows(MediaListNullPointerException.class, () -> new GenreSorter(mediaList));
    }

    @Test
    void mediaListEmptyTest() {
        List<Media> mediaList = new ArrayList<>();
        assertThrows(MediaListEmptyException.class, () -> new GenreSorter(mediaList));
    }

    @Test
    void resultNotNullTest() {
        List<Media> mediaList = mediaDatabase.getAllMedia();
        GenreSorter genreSorter;
        try {
            genreSorter = new GenreSorter(mediaList);
        } catch (MediaListNullPointerException e) {
            throw new RuntimeException(e);
        } catch (MediaListEmptyException e) {
            throw new RuntimeException(e);
        }
        genreSorter.sort();
        assertNotNull(genreSorter.getSortedEntryList());
    }
}