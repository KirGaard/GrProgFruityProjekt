package DataParser;

import java.io.File;

/**
 * @Author Kasper
 * The following class formats the posters given as data
 */

public class FormatPosterTitles {

    /**
     * The following method takes the poster files and renames them
     * @param dirPath directory path of file
     */

    public static void RenameFiles(String dirPath){

        // Create a File object for the directory

        File dir = new File(dirPath);

        // Get a list of all the files in the directory

        File[] files = dir.listFiles();

        // Loop through the files in the directory

        for (File file : files) {

            // Get the old file name

            String oldName = file.getName();

             // Replace all the spaces in the old file name with underscores

            String newName = oldName.replaceAll(" ", "_");
            newName = newName.replaceAll("'", "");

            // Create a File object for the old and new file names

            File oldFile = new File(dir, oldName);
            File newFile = new File(dir, newName);

             // Rename the file

            oldFile.renameTo(newFile);
        }
    }
}