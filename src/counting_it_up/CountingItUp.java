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
        long[] nkArray;
        for (String line : lines) {
            try {
                nkArray = parseLine(line);
                System.out.println(combinations(nkArray[0], (int) nkArray[1]));
            } catch (IllegalArgumentException iae) {
                System.out.printf("INVALID '%s': %s%n", line, iae.getMessage());
            }
        }
    }

    /**
     * Returns a line of input from a user, split into an array for n and k
     *
     * @param line String for input from a user
     * @return integer array, first element is n, and the second is k
     * @throws IllegalArgumentException if the inputted line is invalid
     */
    private long[] parseLine(String line) throws IllegalArgumentException {
        String[] splitLine = line.split(" ");
        if (splitLine.length == 2) {
            for (String el : splitLine) {
                if (!isPositiveInt(el)) {
                    throw new IllegalArgumentException("Values in inputted line must be positive integers!");
                }
            }
            long n = Long.parseLong(splitLine[0]);
            long k = Long.parseLong(splitLine[1]);
            return new long[]{n, k};
        }
        throw new IllegalArgumentException("Line must have 2 parts, one for n, one for k");
    }

    /**
     * Checks if an inputted string is a positive integer or not
     * <p>
     * Integer can be up to 64 bits.
     *
     * @param str String to be checked
     * @return boolean as described
     */
    private boolean isPositiveInt(String str) {
        if (is64Int(str)) {
            return (long) Float.parseFloat(str) >= 0;
        }
        return false;
    }

    /**
     * Checks if an inputted string is a 64 bit integer or not
     *
     * @param str String
     * @return boolean as described
     */
    private boolean is64Int(String str) {
        float num;
        try {
            num = Float.parseFloat(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return (num == (long) num);
    }

    /**
     * Gets input of and k from a user
     *
     * @return ArrayList for lines of input from a user
     */
    private ArrayList<String> getInput() {
        System.out.println(String.join(System.lineSeparator(),
                "Welcome to counting it up",
                "Please enter your values of n and k to compute the number of combinations, one per line",
                "(in the format 'n k')",
                "Click enter on an empty line to submit"));
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> lines = new ArrayList<>();
        String line = scanner.nextLine();
        while (!line.equals("")) {
            lines.add(line);
            line = scanner.nextLine();
        }
        return lines;
    }

    /**
     * Finds the number of combinations of a given n and k. I.e. finds nCr.
     * <p>
     * Finds a max number of combinations up to 64 bits. In practice this is 66C33 = 7219428434016265740 which is just
     * bellow the threshold of 2^63-1
     *
     * @param n long
     * @param k int
     * @return long for combinations as described
     */
    public long combinations(long n, int k) {
        if (n < k) {
            return 0;
        } else if (k == 0 || k == n) {
            return 1;
        } else if (k == 1 || n - k == 1) {
            return n;
        } else {
            return modifiedNthPascalsTriangleRow(n, k).get(k);
        }
    }

    /**
     * Finds the nth row of a modified Pascals triangle
     *
     * @param rowNum long for row to find
     * @param k      for k in combinations
     * @return ArrayList containing Longs
     */
    private ArrayList<Long> modifiedNthPascalsTriangleRow(long rowNum, long k) {
        int diff = (int) Math.min(k + 1, rowNum - k + 1);
        ArrayList<Long> prevRow = new ArrayList<>();
        prevRow.add((long) 1);
        int currRowNum = 2;
        while (currRowNum <= rowNum + 1) {
            prevRow = nextModifiedPascalsRow(prevRow, diff, currRowNum);
            currRowNum++;
        }
        return prevRow;
    }

    /**
     * Creates the next row of a modified Pascals triangle.
     *
     * @param prevRow    List contianing Longs
     * @param diff       int for the width of row to take
     * @param currRowNum int for next row to be created
     * @return ArrayList containing Longs
     */
    private ArrayList<Long> nextModifiedPascalsRow(List<Long> prevRow, int diff, int currRowNum) {
        int rowLength = Math.min(diff, currRowNum);
        ArrayList<Long> currRow = new ArrayList<>();
        currRow.add((long) 1);
        int i = 2;
        while (i <= rowLength) {
            if (i == currRowNum) {
                currRow.add((long) 1);
            } else {
                currRow.add(prevRow.get(i - 2) + prevRow.get(i - 1));
            }
            i++;
        }
        return currRow;
    }
}