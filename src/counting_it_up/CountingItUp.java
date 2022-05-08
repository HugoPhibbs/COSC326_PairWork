package counting_it_up;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Long.parseLong;
import static java.lang.Integer.parseInt;

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
        PositiveBigInt[] nkArray;
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
     * @return PositiveBigInt array, first element is n, and the second is k
     * @throws IllegalArgumentException if the inputted line is invalid
     */
    private PositiveBigInt[] parseLine(String line) throws IllegalArgumentException {
        String[] splitLine = line.split(" ");
        if (splitLine.length == 2) {
            for (String el : splitLine) {
                if (!PositiveBigInt.stringIsPositiveInt(el)) {
                    throw new IllegalArgumentException("Values in inputted line must be positive integers!");
                }
            }
            PositiveBigInt n = new PositiveBigInt(splitLine[0]);
            PositiveBigInt k = new PositiveBigInt(splitLine[1]);
            return new PositiveBigInt[]{n, k};
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
     * @param n PositiveBigInt
     * @param k PositiveBigInt
     * @return long for combinations as described
     */
    public long combinations(PositiveBigInt n, PositiveBigInt k) {
        if (n.isSmallerThan(k)) {
            return parseLong(nthPascalsRow(k.add(new PositiveBigInt("1"))).get(parseInt(n.getValue())).getValue());
        } else if (k.equals(new PositiveBigInt("0"))) {
            return 1;
        } else {
            return parseLong(nthPascalsRow(n.add(new PositiveBigInt("1"))).get(parseInt(k.getValue())).getValue());
        }
    }

    /**
     * Finds the nth row of Pascals triangle
     *
     * @param n PositiveBigInt for nth row
     * @return ArrayList containing Longs for the nth row
     */
    private ArrayList<PositiveBigInt> nthPascalsRow(PositiveBigInt n) {
        ArrayList<PositiveBigInt> prevRow = new ArrayList<>(List.of(new PositiveBigInt("1")));
        PositiveBigInt i = new PositiveBigInt("1");
        while (i.isSmallerThan(n)) {
            prevRow = createNextRow(prevRow);
            i.increment();
        }
        return prevRow;
    }

    /**
     * Creates the next row of pascals triangle given an inputted previous row
     *
     * @param prevRow List of PositiveBigInt values for the previous row of pascals triangle
     * @return List containing Longs as described
     */
    private static ArrayList<PositiveBigInt> createNextRow(ArrayList<PositiveBigInt> prevRow) {
        ArrayList<PositiveBigInt> currRow = new ArrayList<>(prevRow.size() + 1);
        currRow.add(new PositiveBigInt("1"));
        for (int i = 1; i < prevRow.size(); i++) {
            currRow.add(prevRow.get(i - 1).add(prevRow.get(i)));
        }
        currRow.add(new PositiveBigInt("1"));
        return currRow;
    }
}