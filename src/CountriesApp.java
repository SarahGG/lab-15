import java.util.Scanner;

/**
 * Operates on a text file of countries
 *
 * @author Sarah Guarino
 * @version 1.0
 */
public class CountriesApp {

    /**
     * Main app of program
     * navigates through my menu
     *
     * @param args main method string args
     */
    public static void main(String[] args) {
        String dirString = "files";
        String fileString = "countries.txt";
        boolean doAgain = true;
        Integer menuNav;
        Scanner scnr = new Scanner(System.in);

        // Checks for a directory and creates one if none is found
        CountriesTextFile.createDirectory(dirString);

        // Checks for a file within dirString directory
        // and creates one if it does not exist
        CountriesTextFile.createFile(dirString, fileString);

        // Welcomes the user
        System.out.println("Welcome to My Countries List!");

        do {
            menuNav = Validator.getInt(scnr,
                    "\nMAIN MENU - Please enter 1, 2, 3, or 4\n[1: List Countries][2: Add a Country][3: Delete a Country][4: Exit]\nInput: ",
                    1, 4);

            if (menuNav == 1) {
                // 1: PRINT ALL COUNTRIES
                // Prints all the countries in the file, in order
                CountriesTextFile.readFrom(dirString, fileString);
            } else if (menuNav == 2) {
                // 2: ADD A COUNTRY
                // ADD: Oman, Bahrain, Kuwait, Cyprus
                // Prompts user for a country to add and then appends it to the file
                CountriesTextFile.writeTo(dirString, fileString, scnr);
            } else if (menuNav == 3) {
                // 3: REMOVE A COUNTRY
                // Prompts the user to type in a country name, removes it if one is found
                CountriesTextFile.removeFrom(dirString, fileString, scnr);
            } else if (menuNav == 4) {
                // 4: EXIT PROGRAM
                System.out.println("Goodbye!");
                doAgain = false;
            }
        } while (doAgain);
    }
}
