package Presentation.Overview;

import Database.MediaDatabase;
import Database.MediaNotInDatabaseException;
import Media.Media;
import Presentation.IController;
import Presentation.MainGUI;
import Searching.NullQueryException;
import Searching.GenreSearcher;
import Searching.RatingSearcher;
import Searching.Searcher;
import Searching.TitleSearcher;
import Sorting.Exceptions.MediaListEmptyException;
import Sorting.Exceptions.MediaListNullPointerException;
import Sorting.GenreSorter;
import User.User;
import User.UserPrefs;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.*;

/**
 * This class handles the logic in the Overview.fxml
 */
public class OverviewController implements IController {

    /**
     * A reference to the contentPane in the fxml
     */
    @FXML
    private GridPane contentPane;
    /**
     * A reference to the titleLabel in the fxml
     */
    @FXML
    private Label titleLabel;
    /**
     * A reference to the searchField in the fxml
     */
    @FXML
    private TextField searchField;
    /**
     * The ArrayList of all the mediaRows
     */
    private ArrayList<MediaRow> mediaRows;
    /**
     * A reference to the mediaDatabase
     */
    private MediaDatabase mediaDatabase;
    /**
     * A reference to the user
     */
    private User user;

    /**
     * Called when the fxml is initialised.
     * This method instantiates all the MediaRows and the MediaDatabase.
     * The method also adds the search method as a listnener to the textFields textProperty() - so when the text is updated.
     * Also creates the homepage rows
     */
    @FXML
    private void initialize()
    {
        System.out.println("Initializing Overview");
        user = UserPrefs.currentUser;
        mediaRows = new ArrayList<>();
        mediaDatabase = new MediaDatabase();

        searchField.textProperty().addListener((observable, oldvalue, newvalue) -> search());

        createMediaRows();
        createHomepageRows();

    }

    /**
     * A method to create the homepage rows
     * method shuffles all the media randomly and creates the sorted media rows
     */
    @FXML
    public void createHomepageRows(){
        titleLabel.setText("Oversigt:");
        List<Media> allMedia = mediaDatabase.getAllMedia();
        Collections.shuffle(allMedia);

        createSortedNewMediaRows(allMedia);

    }

    /**
     * Creates sorted media rows with all the shows
     */
    @FXML
    public void createShowRows(){
        titleLabel.setText("Serier:");
        List<Media> allSeries = mediaDatabase.getShows();
        createSortedNewMediaRows(allSeries);
    }

    /**
     * Creates sorted media rows with all the films
     */
    @FXML
    public void createFilmRows(){
        titleLabel.setText("Film:");
        List<Media> allFilms = mediaDatabase.getFilms();
        createSortedNewMediaRows(allFilms);
    }

    /**
     * Creates unsorted media rows based on the favorite media from the user
     */
    @FXML
    public void createFavoriteRows(){
        titleLabel.setText("Favoritter:");
        List<String> favoriteTitles = user.getFavoriteTitles();
        List<Media> favoriteMedia = new ArrayList<>();

        for(String title : favoriteTitles){
            try {
                Media media = mediaDatabase.getMediaByTitle(title);
                favoriteMedia.add(media);
            } catch (MediaNotInDatabaseException e) {
                // Should never happen as the media loaded and saved in the user favorites already have gone through exception check
                throw new RuntimeException(e);
            }
        }
        createNewUnsortedMediaRows(favoriteMedia);
    }

    /**
     * Creates a HashSet with all the results from the different searches.
     * The Exceptions are used in verbose, since none of these errors could happen in runtime.
     * We are using a HashSet to make sure we don't get the same media twice.
     */
    @FXML
    public void search(){
        String query = searchField.getText();
        titleLabel.setText("Resultater");
        HashSet<Media> results = new HashSet<Media>();

        GenreSearcher genreSearcher = null;
        try {
            genreSearcher = new GenreSearcher(mediaDatabase.getAllMedia());
        } catch (MediaListNullPointerException e) {
            throw new RuntimeException(e);
        } catch (MediaListEmptyException e) {
            throw new RuntimeException(e);
        }
        try {
            results.addAll(genreSearcher.Search(query));
        } catch (NullQueryException e) {
            throw new RuntimeException(e);
        }

        TitleSearcher titleSearcher = null;
        try {
            titleSearcher = new TitleSearcher(mediaDatabase.getAllMedia());
        } catch (MediaListNullPointerException e) {
            throw new RuntimeException(e);
        } catch (MediaListEmptyException e) {
            throw new RuntimeException(e);
        }
        try {
            results.addAll(titleSearcher.Search(query));
        } catch (NullQueryException e) {
            throw new RuntimeException(e);
        }

        RatingSearcher ratingSearcher = null;
        try {
            ratingSearcher = new RatingSearcher(mediaDatabase.getAllMedia());
        } catch (MediaListNullPointerException e) {
            throw new RuntimeException(e);
        } catch (MediaListEmptyException e) {
            throw new RuntimeException(e);
        }
        try {
            results.addAll(ratingSearcher.Search(query));
        } catch (NullQueryException e) {
            throw new RuntimeException(e);
        }

        createNewUnsortedMediaRows(results.stream().toList());
    }

    /**
     * Method is called when the user tries to log out.
     * Calls the sign-out method in the MainGUI
     */
    @Override
    public void exit() {
        MainGUI.signOut();
    }

    /**
     * This method creates MediaRowData which is used to update the displayed media in the MediaGUIElements
     * this just means that none of the rows have a title, and every row has at max four media
     * This also means that we can only display 40 elements at max.
     * This is okay since no sane person would have so many media added to their favorites.
     * And if they do they wouldn't remember them all anyway, so they wouldn't notice.
     * @param allMedia The media we need to display
     */
    private void createNewUnsortedMediaRows(List<Media> allMedia){
        // We only show at max the 40 first elements of the allMedia list
        // We also only show 4 elements per row
        // This method is only called from search and favorites
        // And we do not expect somebody to have more than 40 favorite media

        ArrayList<MediaRowData> initialSetting = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int remainingMediaCount = allMedia.size() - 4 * i;

            if (remainingMediaCount <= 0){
                initialSetting.add(new MediaRowData(new ArrayList<Media>(), ""));
                continue;
            }

            int count = remainingMediaCount > 4 ? 4 : remainingMediaCount;
            ArrayList<Media> media = new ArrayList<Media>();
            for (int j = 0; j < count; j++) {
                media.add(allMedia.get(4 * i + j));
            }
            initialSetting.add(new MediaRowData(media, ""));

        }
        updateMediaRows(initialSetting);
    }

    /**
     * This method creates MediaRowData which is to be display in the MediaGUIElements
     * The media is sorted with a GenreSorter and we create a MediaDataRow for the first 10 genres.
     * This is because we only have 10 MediaRows
     * @param allMedia all the media we sort and display
     */
    private void createSortedNewMediaRows(List<Media> allMedia){
        // This method creates the media rows


        ArrayList<MediaRowData> initialSetting = new ArrayList<>();
        GenreSorter genreSorter = null;
        try {
            genreSorter = new GenreSorter(allMedia);
        } catch (MediaListNullPointerException e) {
            throw new RuntimeException(e);
        } catch (MediaListEmptyException e) {
            throw new RuntimeException(e);
        }
        genreSorter.sort();

        // Making the sorted hashmap iterable
        List<Map.Entry<String, ArrayList<Media>>> entryList = genreSorter.getSortedEntryList();

        for (int i = 0; i < 10; i++) {
            if (i >= entryList.size()) break;
            ArrayList<Media> media = entryList.get(i).getValue();
            String title = entryList.get(i).getKey();

            MediaRowData data = new MediaRowData(media, title);
            initialSetting.add(data);
        }

        updateMediaRows(initialSetting);
    }

    /**
     * this method updates the MediaRows to display a new MediaDataRow. If there is no more MediaDataRows to display we hide the MediaRow
     * @param mediaData All the MediaRowData which we need to display in the MediaRows
     */
    private void updateMediaRows(ArrayList<MediaRowData> mediaData){
        int length = mediaData.size();
        if (length > 10) throw new RuntimeException("Too many media rows are trying to be instantiated");

        for (int i = 0; i < 10; i++) {
            if (i >= length){
                mediaRows.get(i).setVisible(false);
                continue;
            }
            mediaRows.get(i).updateMedia(mediaData.get(i));
            mediaRows.get(i).setVisible(true);
        }
    }

    /**
     * This method creates the instances of the media rows.
     * This method is called only once in the initialization().
     */
    private void createMediaRows(){
        // We instantiate the mediaRows with a dummy title and dummy media,since they are going to change
        // TODO: Refactor this so the constructor doesn't take a title and media

        ObservableList<Node> children = contentPane.getChildren();

        for (int i = 1; i < contentPane.getRowCount(); i++) {
            GridPane mediaRow = (GridPane) children.get(i);
            ArrayList<Media> media= new ArrayList<Media>();
            for (int j = 0; j < 10; j++) {
                media.add(mediaDatabase.getAllMedia().get(i * 10 + j));
            }
            mediaRows.add(new MediaRow("title", media, mediaRow, contentPane));
        }

    }
}
