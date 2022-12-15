package DataParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @Author Kasper
 * The following class formats .txt files to .json files
 */

public class TxtToJSONParser {
    /**
     * Converts .txt file of movies to .json file
     * Creates a new file with pathname of directory equal to the .txt file of films
     * Instantiate and initialize a scanner to read the file
     * End the file with the finalString
     * While there's a next line in the file
     * Remove all unnecessary spaces
     * Put each line in a bracket
     * Split each element by ":"
     * Add categories to the data
     * Parse the .txt file to .json file
     * @throws FileNotFoundException in case a file is not found
     */

    public static void movieTxtToJSON() throws FileNotFoundException {
        File file = new File("Data\\film.txt");
        Scanner sc = new Scanner(file);
        String finalString = "{\"list\":[";

        while (sc.hasNextLine()){
            String line = RemoveUnnecessarySpaces(sc.nextLine());
            String jsonString = "{";
            String[] DataElements = line.split(";");
            String[] categories = new String[]{
                    "title",
                    "release",
                    "genre",
                    "rating"
            };
            for (int i = 0; i < categories.length; i++) {
                jsonString += '"' + categories[i] + '"' + ":";
                switch (i){
                    case 0:
                        jsonString += getString(DataElements[i]);
                        break;
                    case 2:
                        jsonString += getList(DataElements[i]);
                        break;
                    default:
                        jsonString += getNumber(DataElements[i]);
                        break;
                }
                if (i < categories.length - 1) jsonString += ",";


            }
            jsonString += "},";
            finalString += jsonString + "\n";


        }
        finalString = finalString.replaceAll(".$", "");
        finalString += "]}";
        writeToJSONFile("parsedFilm", finalString);
    }


    /**
     * Same as the method above but with series instead and extra categories for series such as seasons
     * @throws FileNotFoundException in case a file is not found
     */

    public static void seriesTxtToJSON() throws FileNotFoundException {
        File file = new File("Data\\serier.txt");
        Scanner sc = new Scanner(file);
        String finalString = "{\"list\":[";

        while (sc.hasNextLine()){
            String line = RemoveUnnecessarySpaces(sc.nextLine());
            String jsonString = "{";
            String[] dataElements = line.split(";");
            String[] categories = new String[]{
                    "title",
                    "release",
                    "genre",
                    "rating",
                    "seasons"
            };
            for (int i = 0; i < categories.length; i++) {
                jsonString += '"' + categories[i] + '"' + ":";
                switch (i){
                    case 0: case 1:
                        jsonString += getString(dataElements[i]);
                        break;
                    case 2: case 4:
                        jsonString += getList(dataElements[i]).replaceAll("[0-9]-", "");
                        break;
                    default:
                        jsonString += getNumber(dataElements[i]);
                        break;
                }
                if (i < categories.length - 1) jsonString += ",";
            }
            jsonString += "},";
            finalString += jsonString + "\n";


        }
        finalString = finalString.replaceAll(".$", "");
        finalString += "]}";
        writeToJSONFile("parsedSeries", finalString);
    }

    /**
     * The following method gets the String element in the .json file
     * @param element category that we want to get "release", "title" etc
     * @return element with space
     */

    private static String getString(String element) {
        return '"' + element + '"';

    }

    // Todo: ved ikke hvad der sker her
    /**
     * The following method gets the number of category
     * Replaces all "." with ","
     * @param element category we want to
     * @return
     */

    private static String getNumber(String element) {
        return element.replaceAll(",", ".");

    }

    /**
     * The following method returns the list of genres a film or show has
     * Wraps the list inside square brackets
     * @param element the genres a given film or show has that we want to format
     * @return list of the genres that are in a square bracket separated by a ","
     */

    private static String getList(String element) {
        String[] genres = element.replaceAll(" ", "").split(",");
        String out = "";

        out += "[";
        for (int j = 0; j < genres.length; j++) {
            String genre = genres[j];
            out += '"' + genre + '"';
            if (j < genres.length - 1) out += ",";
        }
        out += "]";
        return out;
    }


    /**
     * The following method writes a file into a .json file
     * @param filename the file which we want to format into a .json file
     * @param input what we want to be written in the file
     */

    private static void writeToJSONFile(String filename, String input){
        String path = "Data\\" + filename+ ".json";
        new FileHandler(path).writeFile(input);
    }

    /**
     * The following method removes all unnecessary spaces in the given String
     * @param string string that we to remove the unnecessary space from
     * @return string with removed unnecessary spaces
     */

    private static String RemoveUnnecessarySpaces(String string){
        return string.replaceAll("; *", ";");
    }


}



