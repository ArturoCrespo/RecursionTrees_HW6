package edu.miracosta.cs113.change;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * ChangeCalculator : Class containing the recursive method calculateChange, which determines and prints all
 * possible coin combinations representing a given monetary value in cents.
 *
 * Problem derived from Koffman & Wolfgang's Data Structures: Abstraction and Design Using Java (2nd ed.):
 * Ch. 5, Programming Project #7, pg. 291.
 *
 * NOTE: An additional method, printCombinationsToFile(int), has been added for the equivalent tester file to
 * verify that all given coin combinations are unique.
 */
public class ChangeCalculator {

    public static final int[] DENOMINATIONS = { 25, 10, 5, 1 } ;
    public static final File OUTPUT_FILE = new File("/Users/arturo/Desktop/CodeRepository/RecursionTrees_HW6/Tests/edu/miracosta/cs113/change/CoinCombinations.txt") ;
    private static ArrayList<String> combinations = new ArrayList<String>() ;

    /**
     * Wrapper method for determining all possible unique combinations of quarters, dimes, nickels, and pennies that
     * equal the given monetary value in cents.
     *
     * In addition to returning the number of unique combinations, this method will print out each combination to the
     * console. The format of naming each combination is up to the user, as long as they adhere to the expectation
     * that the coins are listed in descending order of their value (quarters, dimes, nickels, then pennies). Examples
     * include "1Q 2D 3N 4P", and "[1, 2, 3, 4]".
     *
     * @param cents a monetary value in cents
     * @return the total number of unique combinations of coins of which the given value is comprised
     */
    public static int calculateChange(int cents) {
        // Implement a recursive solution following the given documentation.
        combinations.clear() ;
        recursiveChange(cents, 0, 0 , 0, cents) ;
        return combinations.size() ;
    }

   public static void recursiveChange(int total, int numQ, int numD, int numN, int numP) {

        //base case, do nothing
        if(DENOMINATIONS[0] * numQ +
                DENOMINATIONS[1] * numD +
                DENOMINATIONS[2] * numN +
                DENOMINATIONS[3] * numP != total) {
            return ;
        }

        String calculatedCombo = "[ " + numQ + "Q " + numD + "D " + numN + "N " + numP + "P" + " ]" ;

        if(!combinations.contains(calculatedCombo)) {
            combinations.add(calculatedCombo) ;
        }

       if (numP >= 5) {
           recursiveChange(total, numQ, numD, numN + 1, numP - 5) ;
       }
       if (numP >= 10) {
           recursiveChange(total, numQ, numD + 1, numN, numP - 10);
       }
       if (numP >= 25) {
           recursiveChange(total, numQ + 1, numD, numN, numP - 25);
       }

   }

    /**
     * Calls upon calculateChange(int) to calculate and print all possible unique combinations of quarters, dimes,
     * nickels, and pennies that equal the given value in cents.
     *
     * Similar to calculateChange's function in printing each combination to the console, this method will also
     * produce a text file named "CoinCombinations.txt", writing each combination to separate lines.
     *
     * @param cents a monetary value in cents
     */
    public static void printCombinationsToFile(int cents) {
        // This when calculateChange is complete. Note that the text file must be created within this directory.
        calculateChange(cents) ;
        try {
            PrintWriter printWriter = new PrintWriter(OUTPUT_FILE) ;
            for(String c : combinations) {
                printWriter.println(c) ;
            }
            printWriter.close() ;
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            System.err.println("Could not access file: " + OUTPUT_FILE);
        }
    }

} // End of class ChangeCalculator

