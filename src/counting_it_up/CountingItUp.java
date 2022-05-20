package counting_it_up;

import java.util.*;

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
        System.out.println(primeFactorMap(429496721L));
        //printCombinations(getInput());
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
    public long combinations(long n, long k) {
        if (n < k) {
            return 0;
        } else if (k == 0 || k == n) {
            return 1;
        } else if (k == 1 || n - k == 1) {
            return n;
        } else {
            return combinationsPrimeFact(n, k);
        }
    }

    /**
     * Finds the number of combinations for a given n and k using prime factorisations
     *
     * @param n long
     * @param k long
     * @return long for combinations as described
     */
    private long combinationsPrimeFact(long n, long k) {
        long effectiveK = Math.min(k, n - k);
        HashMap<Long, Long> topPrimeMap = limitedFactorialPrimeMap(n, n - effectiveK);
        Set<Map.Entry<Long, Long>> bottomPrimeMapPairs = limitedFactorialPrimeMap(effectiveK, 0).entrySet();
        for (Map.Entry<Long, Long> entry : bottomPrimeMapPairs) {
            topPrimeMap.put(entry.getKey(), topPrimeMap.get(entry.getKey()) - entry.getValue());
        }
        Set<Map.Entry<Long, Long>> topPrimeMapPairs = topPrimeMap.entrySet();
        long result = 1;
        for (Map.Entry<Long, Long> entry : topPrimeMapPairs) {
            if (entry.getValue() > 0) {
                result *= (powerRec(entry.getKey(), entry.getValue()));
            }
        }
        return result;
    }

    /**
     * Computes a^b using recursion.
     * <p>
     * Don't want to use Math.pow to avoid any rounding errors with Doubles
     * <p>
     * Code shamelessly stolen from:
     * https://stackoverflow.com/questions/8071363/calculating-powers-of-integers
     *
     * @param a long
     * @param b long
     * @return long
     */
    private long powerRec(long a, long b) {
        if (b == 0) {
            return 1;
        }
        if (b == 1) {
            return a;
        }
        if (b % 2 == 0) {
            return powerRec(a * a, b / 2); //even a=(a^2)^b/2
        } else {
            return a * powerRec(a * a, b / 2); //odd  a=a*(a^2)^b/2
        }
    }

    /**
     * Finds a HashMap the prime factors of a limited factorial value.
     * <p>
     * Limit truncates end terms of a factorial, e.g. limiting 11! with 8, gives 11*10*9
     * <p>
     * Limit should be zero in the case of finding the full factorial
     *
     * @param x     number to find factorial for
     * @param limit limiting number of the factorial
     * @return HashMap with Integer key value pairs for finding the prime factor map
     */
    private HashMap<Long, Long> limitedFactorialPrimeMap(long x, long limit) {
        HashMap<Long, Long> primeMap = new HashMap<>();
        long i = x;
        while (i > limit) {
            addToPrimeMap(primeMap, i);
            i--;
        }
        return primeMap;
    }

    /**
     * Adds the prime factors of a given i to a primeMap
     *
     * @param primeMap HashMap
     * @param i        long
     */
    private void addToPrimeMap(HashMap<Long, Long> primeMap, long i) {
        Set<Map.Entry<Long, Long>> iFactorsPairs;
        iFactorsPairs = primeFactorMap(i).entrySet();
        for (Map.Entry<Long, Long> entry : iFactorsPairs) {
            if (!primeMap.containsKey(entry.getKey())) {
                primeMap.put(entry.getKey(), entry.getValue());
            } else {
                primeMap.put(entry.getKey(), entry.getValue() + primeMap.get(entry.getKey()));
            }
        }
    }

    /**
     * Finds the prime factorisation of a given long number
     * <p></p>
     * Code borrowed from https://www.geeksforgeeks.org/print-all-prime-factors-of-a-given-number/.
     * Uses the sieve of Eratosthenes
     *
     * @param n long for a number
     * @return HashMap, keys are prime numbers, and values are the power of this prime in the factorisation of n
     */
    public static HashMap<Long, Long> primeFactorMap(long n) {
        HashMap<Long, Long> primeFactorMap = new HashMap<>();
        long c = 2;
        while (n > 1) {
            if (n % c == 0) {
                if (!primeFactorMap.containsKey(c)) {
                    primeFactorMap.put(c, (long) 1);
                } else {
                    primeFactorMap.put(c, primeFactorMap.get(c) + 1);
                }
                n /= c;
            } else {
                c++;
            }
        }
        return primeFactorMap;
    }
}