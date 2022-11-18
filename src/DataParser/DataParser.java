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
            String line = sc.nextLine();
            String jsonString = "{";
            String[] element = line.split(";");
            String[] categories = new String[]{
                    "title",
                    "releaseDate",
                    "genre",
                    "rating"
            };
            for (int i = 0; i < element.length; i++) {
                jsonString += '"' + categories[i] + '"' + ":";
                if (i == 0){ // Title string case
                    jsonString += '"' + element[i] + '"';
                }
                else if (i == 2){ // our genres - making it a list
                    String[] genres = element[i].replaceAll(" ", "").split(",");
                    jsonString += "[";
                    for (int j = 0; j < genres.length; j++) {
                        String genre = genres[j];
                        jsonString += '"' + genre + '"';
                        if (j < genres.length - 1) jsonString+= ",";
                    }
                    jsonString += "]";
                }else{ // Our number cases
                    String num = element[i].replaceAll(",", ".");
                    jsonString += num;
                }
                if (i < element.length - 1) jsonString += ",";


            }
            jsonString += "},";

            finalString += jsonString + "\n";


        }
        finalString = finalString.replaceAll(".$", "");
        finalString += "]}";
        writeToJSONFile("parsedFilm", finalString);
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

    }



