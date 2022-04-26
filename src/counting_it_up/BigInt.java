package counting_it_up;

/**
 * A class to represent a custom BigInteger class
 *
 * Tailored towards finding combinations, thus only necessary operations are implemented
 */
public class BigInt {

    /**
     * String (in base 10) for the value of this BigInt
     */
    private String value = "0";

    /**
     * Constructor for a BigInt object
     *
     * @param value string of digits (integer) representing the value of this BigInt. This is in base 10
     */
    BigInt(String value) {
        this.value = value;
    }

    /**
     * Default constructor for a BigInt
     */
    BigInt(){}

    /**
     * Calculates a*b, where a is this BigInt
     *
     * @param b BigInt object to be multipiled by this one
     * @return BigInt object that is the product a and b as described
     */
    public BigInt mul(BigInt b){
        String product = "";
        return null; // TODO
    }

    /**
     * Adds two String values together.
     *
     * Implemented without using BigInts to save on performance. Private to hide from users.
     *
     * Assumes that the two inputted values can both be interpreted as integers
     *
     * Borrows code from GeeksForGeeks TODO insert link here
     *
     * @param value1 String digits of an integer
     * @param value2 String digits of another integer
     * @return String for value1+value2 (in digits)
     */
    private String addValues(String value1, String value2) {
        if (value1.length() > value2.length()) {
            String temp = value1;
            value1 = value2;
            value2 = temp;
        }
        StringBuilder result = new StringBuilder();
        int length1 = value1.length(), length2 = value2.length();
        int lengthDiff = length2 - length1;
        int carry = 0;
        int currSum;
        for (int i = length1 - 1; i >= 0; i--) {
            currSum = ((int) value1.charAt(i) - (int) '0') + ((int) value2.charAt(i + lengthDiff) - (int) '0') + carry;
            result.append((char) (currSum % 10 + '0'));
            carry = currSum / 10;
        }
        for (int j = length2 - length1 - 1; j >= 0; j--) {
            currSum = value2.charAt(j);
            result.append((char) (currSum % 10) + '0');
            carry = currSum / 10;
        }
        if (carry > 0) {
            result.append((char) carry);
        }
        return result.reverse().toString();
    }

    /**
     * Calculates a/b, where a is this BigInt
     *
     * @param b BigInt object that divides this BigInt
     * @return BigInt object as described
     */
    public BigInt div(BigInt b) {
        return null; // TODO
    }

    /**
     * Returns a String representation of this BigInt
     *
     * @return String
     */
    public String toString() {
        return String.format("BigInt with value: %s", value);
    }

    /**
     * Calculates a-b, where a is this BigInt
     *
     * Assumes that a is bigger than b, As this is the only case that subtraction is used in this algorithm.
     *
     * Code borrowed from GeeksForGeeks TODO get the link for this!
     *
     * @param b BigObject to minus off this BigInt
     * @return BigInt object as described
     */
    public BigInt minus(BigInt b){
        StringBuilder result = new StringBuilder();
        String value2 = b.getValue();
        int length1 = value.length(), length2 = value2.length();
        int lengthDiff = length1-length2;
        int carry = 0;
        int currSub;
        for (int i = length2-1; i>=0;i--) {
            currSub = ((int) value.charAt(i+lengthDiff)- (int) '0') - ((int) value2.charAt(i) - (int) '0') - carry;
            if (currSub < 0) {
                currSub = currSub + 10;
                carry = 1;
            }else {
                carry = 0;
            }
            result.append(currSub);
        }
        for (int j = length1-length2-1; j>=0;j--) {
            if ((int) value.charAt(j) == 0 && carry > 0) {
                result.append(9);
                continue;
            }
            currSub = ((int) value.charAt(j) - (int) '0') - carry;
            if (j > 0 || currSub > 0) {
                result.append(currSub);
            }
            carry = 0;
        }
        return new BigInt(result.reverse().toString());
    }

    /**
     * Finds the value of this BigInt
     *
     * @return String of digits as described
     */
    public String getValue() {
        return this.value;
    }
}
