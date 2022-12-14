package Searching;
import Media.Media;

import java.util.List;

public interface ISearcher {

    public List<Media> Search(String searchTerm) throws NullQueryException;


}
