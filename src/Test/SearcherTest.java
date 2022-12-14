package Test;

import Database.MediaDatabase;
import Media.Media;
import Searching.Searcher;
import Sorting.Exceptions.MediaListEmptyException;
import Sorting.Exceptions.MediaListNullPointerException;
import Sorting.GenreSorter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearcherTest {
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
        assertThrows(MediaListNullPointerException.class, () -> new Searcher(mediaList) {
            @Override
            public List<Media> Search(String searchTerm) {
                return null;
            }
        });
    }

    @Test
    void mediaListEmptyTest() {
        List<Media> mediaList = new ArrayList<>();
        assertThrows(MediaListEmptyException.class, () -> new GenreSorter(mediaList));
    }
}