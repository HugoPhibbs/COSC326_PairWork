package counting_it_up;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class that does the "Counting it up" program
 */
public class CountingItUp {

    public static void main(String[] args) {
        new CountingItUp().start();
    }

    /**
     * Starts the Counting it up Program
     */
    public void start() {
        printCombinations(getInput());
    }

    /**
     * Finds combinations for lines of input from a user
     *
     * @param lines ArrayList containing Strings for input from a user
     */
    private void printCombinations(ArrayList<String> lines) {
        int[] nkArray;
        for (String line : lines) {
            try {
                nkArray = parseLine(line);
                System.out.println(combinations(nkArray[0], nkArray[1]));
            } catch (IllegalArgumentException iae) {
                System.out.printf("INVALID '%s': %s%n", line, iae.getMessage());
            }
        }
    }
    /**
     * Returns a line of input from a user, split into an array for n and k
     *
     * @param line String for input from a user
     * @throws IllegalArgumentException if the inputted line is invalid
     * @return integer array, first element is n, and the second is k
     */
    private int[] parseLine(String line) throws IllegalArgumentException {
        String[] splitLine = line.split(" ");
        if (splitLine.length == 2) {
            for (String el : splitLine) {
                if (!isPositiveInt(el)) {
                    throw new IllegalArgumentException("Values in inputted line must be positive integers!");
                }
            }
            int n = (int) Float.parseFloat(splitLine[0]);
            int k = (int) Float.parseFloat(splitLine[1]);
            if (k > n) {
                throw new IllegalArgumentException("k cannot be larger than n!");
            }
            return new int[] {n, k};
        }
        throw new IllegalArgumentException("Line must have 2 parts, one for n, one for k");
    }

    /**
     * Gets input of and k from a user
     *
     * @return ArrayList for lines of input from a user
     */
    private ArrayList<String> getInput() {
        System.out.println("""
                Welcome to counting it up
                Please enter your values of n and k to compute the number of combinations, one per line
                (in the format "n k")
                Click enter on an empty line to submit""");
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> lines = new ArrayList<String>();
        String line = scanner.nextLine();
        while (!line.equals("")) {
            lines.add(line);
            line = scanner.nextLine();
        }
        return lines;
    }

    /**
     * Finds the number of combinations of a given n and k. I.e. finds nCr.
     *
     * Finds a max number of combinations up to 64 bits. In practice this is 66C33 = 7219428434016265740 which is just
     * bellow the threshold of 2^63-1
     *
     * @param n int
     * @param k int
     * @return long for combinations as described
     */
    public long combinations(int n, int k) {
        return nthPascalsRow(n+1).get(k);
    }

    /**
     * Finds the nth row of Pascals triangle
     *
     * @param n int for nth row
     * @return ArrayList containing Longs for the nth row
     */
    private ArrayList<Long> nthPascalsRow(int n) {
        ArrayList<Long> prevRow = new ArrayList<>(List.of((long) 1));
        for (int i = 1; i < n; i++) {
            prevRow = createNextRow(prevRow);
        }
        return prevRow;
    }

    /**
     * Creates the next row of pascals triangle given an inputted previous row
     *
     * @param prevRow List of Long values for the previous row of pascals triangle
     * @return List containing Longs as described
     */
    private static ArrayList<Long> createNextRow(List<Long> prevRow) {
        ArrayList<Long> currRow = new ArrayList<>(prevRow.size() + 1);
        currRow.add((long) 1);
        for (int i = 1; i < prevRow.size(); i++) {
            currRow.add(prevRow.get(i - 1) + prevRow.get(i));
        }
        currRow.add((long) 1);
        return currRow;
    }
    /**
     * Checks if an inputted string is an integer or not
     *
     * @param str String to be checked
     * @return boolean as described
     */
    private boolean isInt(String str) {
        float num;
        try {
            num = Float.parseFloat(str);
        }
        catch (NumberFormatException nfe) {
            return false;
        }
        return (num == (int) num);
    }

    /**
     * Checks if an inputted string is a positive integer or not
     *
     * @param str String to be checked
     * @return boolean as described
     */
    private boolean isPositiveInt(String str) {
        if (isInt(str)) {
            return (int) Float.parseFloat(str) >= 0;
        } return false;
    }

}