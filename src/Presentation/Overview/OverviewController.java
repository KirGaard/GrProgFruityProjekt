package Presentation.Overview;

import Database.MediaDatabase;
import Database.MediaNotInDatabaseException;
import Media.Media;
import Presentation.IController;
import Presentation.Main;
import Searching.GenreSearcher;
import Searching.RatingSearcher;
import Searching.Searcher;
import Searching.TitleSearcher;
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

public class OverviewController implements IController {

    @FXML
    private GridPane contentPane;
    @FXML
    private Label titleLabel;
    @FXML
    private TextField searchField;
    private ArrayList<MediaRow> mediaRows;
    private MediaDatabase mediaDatabase;
    private User user;

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

    @FXML
    public void createHomepageRows(){
        titleLabel.setText("Oversigt:");
        List<Media> allMedia = mediaDatabase.getAllMedia();
        Collections.shuffle(allMedia);

        createSortedNewMediaRows(allMedia);

    }

    @FXML
    public void createShowRows(){
        titleLabel.setText("Serier:");
        List<Media> allSeries = mediaDatabase.getShows();
        createSortedNewMediaRows(allSeries);
    }
    @FXML
    public void createFilmRows(){
        titleLabel.setText("Film:");
        List<Media> allFilms = mediaDatabase.getFilms();
        createSortedNewMediaRows(allFilms);
    }

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

    @FXML
    public void search(){
        String query = searchField.getText();
        titleLabel.setText("Resultater");
        HashSet<Media> results = new HashSet<Media>();

        GenreSearcher genreSearcher = new GenreSearcher(mediaDatabase.getAllMedia());
        results.addAll(genreSearcher.Search(query));

        TitleSearcher titleSearcher = new TitleSearcher(mediaDatabase.getAllMedia());
        results.addAll(titleSearcher.Search(query));

        RatingSearcher ratingSearcher = new RatingSearcher(mediaDatabase.getAllMedia());
        results.addAll(ratingSearcher.Search(query));

        createNewUnsortedMediaRows(results.stream().toList());
    }

    @Override
    public void exit() {
        Main.signOut();
    }

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

    private void createSortedNewMediaRows(List<Media> allMedia){
        // This method creates the media rows


        ArrayList<MediaRowData> initialSetting = new ArrayList<>();
        GenreSorter genreSorter = new GenreSorter(allMedia);
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
