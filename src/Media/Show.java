package Media;

import java.util.List;

public class Show extends Media {
    private List <String> seasons;
    public String getInfo() {
        return "Serie " + getTitleNoSpace() + " " + getRelease() + " " + getGenreString() + " " + rating + " " + ("" + seasons).replaceAll(" ", "_");
    }

    /**
     * @return
     */
    @Override
    public String getPosterPath() {
        return "Data/serieforsider/" + title.replaceAll(" ","_").replaceAll("'", "") + ".jpg";
    }
}
