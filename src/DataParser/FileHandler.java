package DataParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @Author Kasper
 * The following class is an important class which handles data files in the program
 */

public class FileHandler {

    /**
     * Create path String object
     */

    private String path;

    /**
     * The following constructor initializes the parameter path to be equal to the field path
     * @param path path directory to a certain place
     */

    public FileHandler(String path){
        this.path = path;
    }

    /**
     * The following method is responsible for writing files in the program
     * It first creates an object instance of FileWriter with path as a parameter
     * It then takes String input and then writes the file with it
     * Catches an IOException in case of an error
     * @param input whatever you want to write into a file
     */

    public void writeFile(String input){
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(input);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred creating trying to write file");
            e.printStackTrace();
        }

    }

    /**
     * The following method reads the file
     * Creates a File instance object with pathname path
     * Instantiate a Scanner
     * Read the file
     * If file doesn't exist
     * Throws an exception
     * If file exists
     * @return everything in the file as a String
     */

    public String readFile(){

        File file = new File(path);

        Scanner sc;

        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + path);

        }

        StringBuilder out = new StringBuilder();
        while(sc.hasNextLine()) out.append(sc.nextLine()).append("\n");

        return out.toString();
    }



}
