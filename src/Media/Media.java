package Media;

import java.util.List;

public abstract class Media {
    protected String title;
    protected String release;
    protected double rating;
    protected List<String> genre;

    String getInfo() {
        throw new RuntimeException();
    }
}
