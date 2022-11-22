package DataParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler {
    private String path;

    public FileHandler(String path){
        this.path = path;
    }

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
