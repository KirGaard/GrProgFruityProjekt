package DataParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataParser {

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
                    "releaseDate",
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
                    "runTime",
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

    private static String getString(String element) {
        return '"' + element + '"';

    }
    private static String getNumber(String element) {
        return element.replaceAll(",", ".");

    }

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


    private static void writeToJSONFile(String filename, String input){
            try {
                FileWriter fileWriter = new FileWriter("Data\\" + filename+ ".json");
                fileWriter.write(input);
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred creating JsonFile.");
                e.printStackTrace();
            }
    }

    private static String RemoveUnnecessarySpaces(String string){
        return string.replaceAll("; *", ";");
    }


}



