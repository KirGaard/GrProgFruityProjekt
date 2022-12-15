package Searching;
import Media.Media;

import java.util.List;

/**
 * @Author Kasper, Victor
 * Interface for searching with a search method that needs to implemented in the classes that implement this interface
 */

public interface ISearcher {

    /**
     * The following method allows to search for a search term and returns a list of media matching with search term
     * @param searchTerm the term we want to look for, could be genre, title etc
     * @return list of media matching with search term
     * @throws NullQueryException in case search query is empty
     */
    public List<Media> Search(String searchTerm) throws NullQueryException;


}
