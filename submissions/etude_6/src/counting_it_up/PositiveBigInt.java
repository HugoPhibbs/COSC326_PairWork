package counting_it_up;

/**
 * A class to represent a custom BigInteger class, for Positive integers only
 * <p>
 * Tailored towards finding combinations, thus only necessary operations are implemented
 */
public class PositiveBigInt {

    /**
     * String (in base 10) for the value of this BigInt
     */
    private String value = "0";

    /**
     * Constructor for a PositiveBigInt object
     *
     * @param value string of digits (integer) representing the value of this PositiveBigInt. This is in base 10
     */
    PositiveBigInt(String value) {
        setValue(value);
    }

    /**
     * Sets the value of this PositiveBigInt
     *
     * @param val String to be set
     * @throws AssertionError if the inputted value could not be set
     */
    public void setValue(String val) {
        String stripped = stripZeros(val);
        assert (stringIsInt(stripped) && (valueIsPositive(val))) : "Inputted value must a positive integer!";
        value = stripped;
    }

    /**
     * Strips leading zeros from an Integer represented as a String
     *
     * @param val String to be stripped
     * @return a new String as described
     */
    public static String stripZeros(String val) {
        assert (val.length() != 0) : "Val cannot be an empty String!";
        char[] charArray = val.toCharArray();
        int i = 0;
        while ((i < val.length() - 1) && (charArray[i] == '0')) {
            i++;
        }
        return val.substring(i);
    }

    /**
     * Adds two PositiveBigInts together
     * <p>
     * Borrows code from StackExchange: https://codereview.stackexchange.com/questions/32954/adding-two-big-integers-represented-as-strings
     *
     * @param b PositiveBigInt digits
     * @return String for value1+value2 (in digits)
     */
    public PositiveBigInt add(PositiveBigInt b) {
        String bValue = b.getValue();
        String aValue = getValue();
        StringBuilder buf = new StringBuilder();
        for (int i1 = aValue.length() - 1, i2 = bValue.length() - 1, carry = 0;
             i1 >= 0 || i2 >= 0 || carry != 0;
             i1--, i2--) {
            int digit1 = i1 < 0 ? 0 :
                    Integer.parseInt(Character.toString(aValue.charAt(i1)));
            int digit2 = i2 < 0 ? 0 :
                    Integer.parseInt(Character.toString(bValue.charAt(i2)));
            int digit = digit1 + digit2 + carry;
            if (digit > 9) {
                carry = 1;
                digit -= 10;
            } else {
                carry = 0;
            }
            buf.append(digit);
        }
        return new PositiveBigInt(buf.reverse().toString());
    }

    /**
     * Checks if an inputted string is a Positive Integer
     *
     * @param str String to be checked
     * @return boolean as described
     */
    public static boolean stringIsPositiveInt(String str) {
        return PositiveBigInt.valueIsPositive(str) && PositiveBigInt.stringIsInt(str);
    }

    /**
     * Checks if an inputted String is an integer or not
     *
     * @param str String for an inputted string
     */
    private static boolean stringIsInt(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    /**
     * Finds out if this BigInt is smaller than another Big Int
     *
     * Uses code from GeeksForGeeks: https://www.geeksforgeeks.org/difference-of-two-large-numbers/
     *
     * Ie returns a < b, where a is this BigInt
     *
     * @param b BigInt to be compared to this BigInt
     * @return boolean as described
     */
    public boolean isSmallerThan(PositiveBigInt b) {
        String value2 = b.getValue();
        int length1 = value.length(), length2 = value2.length();
        if (length1 < length2)
            return true;
        if (length2 < length1)
            return false;
        for (int i = 0; i < length1; i++)
            if (value.charAt(i) < value2.charAt(i))
                return true;
            else if (value.charAt(i) > value2.charAt(i))
                return false;

        return false;
    }

    /**
     * Increments this PositiveBigInt by 1
     */
    public void increment() {
        value = add(new PositiveBigInt("1")).getValue();
    }

    /**
     * Finds if an inputted String value for an integer is positive or not
     *
     * @param val String for an inputted value
     * @return boolean as described
     */
    private static boolean valueIsPositive(String val) {
        return val.charAt(0) != '-';
    }

    /**
     * Finds out if a PositiveBigInt equals another object
     *
     * @param obj Object to be checked
     * @return boolean as described
     */
    public boolean equals(Object obj) {
        if (obj instanceof PositiveBigInt) {
            return ((PositiveBigInt) obj).getValue().equals(value);
        }
        return false;
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
     * Finds the value of this BigInt
     *
     * @return String of digits as described
     */
    public String getValue() {
        return this.value;
    }
}