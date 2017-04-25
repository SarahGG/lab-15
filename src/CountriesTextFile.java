import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Operates off of a file in the program
 *
 * @author Sarah Guarino
 * @version !.0
 */
class CountriesTextFile {
    /**
     * Creates a file if none exists
     * @param dirString string name of directory file being edited is within
     * @param fileString string name of file being edited
     */
    static void createFile(String dirString, String fileString) {
        Path filePath = Paths.get(dirString, fileString);

        if (Files.notExists(filePath)) {
            try {
                Files.createFile(filePath);
                // System.out.println("File Name: " + filePath.getFileName());
                // System.out.println("Absolute Path: " + filePath.toAbsolutePath());
                // System.out.println("Is Writable: " + Files.isWritable(filePath));
            } catch (IOException createFile) {
                System.out.println("There was an error: " + createFile);
            }
        }
    }

    /**
     * Creates the appropriate directory if none exists
     * @param dirString string representation of the directory's given name
     */
    static void createDirectory(String dirString) {
        Path dirPath = Paths.get(dirString);

        if (Files.notExists(dirPath)) {
            try {
                Files.createDirectories(dirPath);
            } catch (IOException createDir) {
                // createDir.printStackTrace();
                System.out.println("There was an error: " + createDir);
            }
        }
    }

    /**
     * reads every line from the file and prints them in order
     * @param dirString string representation of the directory file being read is within
     * @param fileString string representation of the file being read
     */
    static void readFrom(String dirString, String fileString) {
        try {
            // Creates a path object, accepts a string of the file path
            Path filePath = Paths.get("" + dirString + "/" + fileString);
            // Creates a file object with the path given
            File textFile = filePath.toFile();

            // Filereader object reads given files
            FileReader fileRead = new FileReader(textFile);
            // Buffered reader object handles reading better, accepts a filereader object as a parameter
            BufferedReader buffRead = new BufferedReader(fileRead);

            // Integer number counter
            int i = 1;

            // Buffered reader object .readline() method
            try {
                // Checks first line
                String line = buffRead.readLine();

                System.out.println("\nCurrent Middle Eastern Countries:\n");


                // While next line is not null
                while(line != null) {
                    // Prints line
                    System.out.println("" + i + ": " + line);
                    // Moves to next line in file
                    line = buffRead.readLine();
                    // Increments counter
                    i++;
                }
                //Extra line for spacing
                System.out.println();

                fileRead.close();
                buffRead.close();
            } catch (IOException buffread) {
                System.out.println("There was an error: " + buffRead);
            }
        } catch (FileNotFoundException readingFile) {
            System.out.println("There was an error: " + readingFile);
        }
    }

    /**
     * adds a new country to the file
     * @param dirString string representation of the directory the file being written to is within
     * @param fileString string representation of the file being written to
     * @param scnr Scanner object
     */
    static void writeTo(String dirString, String fileString, Scanner scnr) {
        // String to collect text that will be added.
        String addText = "";
        // Creates a Path object with our file's path
        Path countriesPath = Paths.get("" + dirString + "/" + fileString);
        // Creates a File object using our newly made Path object
        File countriesFile = countriesPath.toFile();

        try {
            // Initializing my PrintWriter
            PrintWriter out = new PrintWriter(new FileOutputStream(countriesFile, true));

            //Prompts for country name to add to list
            System.out.print("\nADD A COUNTRY - Please enter the name of a country:\nInput: ");
            // Gets country name to add
            addText = scnr.nextLine();

            //Prints this to the file, then closes the stream
            out.print("\n" + addText);
            out.close();
        } catch (FileNotFoundException fileNotFound) {
            fileNotFound.printStackTrace();
            System.out.println("There was an error: " + fileNotFound);
        }

        System.out.println("Country Added: " + addText + "\n");
    }

    /**
     * removes a given country from the file in question
     * @param dirstring string representation of the directory the file being written to is within
     * @param fileString string representation of the file being written to
     * @param scnr Scanner object
     */
    static void removeFrom(String dirstring, String fileString, Scanner scnr) {
        // Creates Path and File objects for my countries file
        Path countriesPath = Paths.get("" + dirstring + "/" + fileString);
        Path tempPath = Paths.get("files/temp.txt");
        File countriesFile = countriesPath.toFile();
        File tempFile = tempPath.toFile();

        try {
            // creates a temp file
            CountriesTextFile.createFile("files", "temp.txt");
            //creates writer object that writes to temp file
            PrintWriter out = new PrintWriter(new FileOutputStream(tempFile, true));

            // Creates a FileReader (needs a File object as a parameter
            // and BufferReader (needs a FileReader object as a parameter)
            FileReader fileRead = new FileReader(countriesFile);
            BufferedReader buffRead = new BufferedReader(fileRead);

            // text to be removed, string to read current line
            String removeText;
            String currentLine = buffRead.readLine();

            // prompts user and collects string
            System.out.print("\nREMOVE A COUNTRY - Please enter the name of a country:\nInput: ");
            removeText = scnr.nextLine();

            // while there is something on the next line
            while (currentLine != null) {
                // trim extra spaces and newlines off of both strings
                removeText = removeText.trim();
                currentLine = currentLine.trim();

                // if the lines do not match, print line as normal
                if (!(removeText.equals(currentLine))) {
                    out.print(currentLine + "\n");
                } else {
                    // if lines do match, skips the printing, and alerts the user that the country has been removed
                    System.out.println("Country removed: " + removeText);
                }
                //moves to the next line
                currentLine = buffRead.readLine();
            }

            out.close();
            fileRead.close();
            buffRead.close();

            // Deletes countries file, renames temp to countries, then deletes the
            Files.deleteIfExists(countriesPath);
            tempFile.renameTo(countriesFile);
        } catch (IOException remText) {
            System.out.println("There was an error: " + remText.getMessage());
        }

    }
}
