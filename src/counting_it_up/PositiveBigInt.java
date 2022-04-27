package counting_it_up;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * A class to represent a custom BigInteger class, for Positive integers only
 *
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
     * @throws AssertionError if the inputted value could not be set
     * @param val String to be set
     */
    public void setValue(String val) {
        String stripped = stripZeros(val);
        assert (stringIsInt(stripped) && (valueIsPositive(val))) : "Inputted value must a positive integer!";
        value = stripped;
    }

    /**
     * Finds the factorial of this PositiveBigInt
     *
     * @return a new PositiveBigInt object
     */
    public PositiveBigInt factorial() {
        final PositiveBigInt ONE = new PositiveBigInt("1");
        if (value.equals("0")) {
            return ONE;
        }
        PositiveBigInt result = new PositiveBigInt("1");
        PositiveBigInt curr = new PositiveBigInt("1");
        while (curr.isSmallerThan(this) || curr.equals(this)) {
            result = result.mul(curr);
            curr.increment();
        }
        return result;
    }

    /**
     * Calculates a*b, where 'a' is this PositiveBigInt
     *
     * Code borrowed from GeeksForGeeks: https://www.geeksforgeeks.org/multiply-large-numbers-represented-as-strings/
     *
     * @param b BigInt object to be multiplied by this one
     * @return BigInt object that is the product a and b as described
     */
    public PositiveBigInt mul(PositiveBigInt b){
        String value2 = b.getValue();
        int len1 = value.length();
        int len2 = value2.length();
        if (len1 == 0 || len2 == 0)
            return new PositiveBigInt("0");
        int[] result = new int[len1 + len2];
        int i_n1 = 0;
        int i_n2;
        for (int i = len1 - 1; i >= 0; i--)
        {
            int carry = 0;
            int n1 = value.charAt(i) - '0';
            i_n2 = 0;
            for (int j = len2 - 1; j >= 0; j--)
            {
                int n2 = value2.charAt(j) - '0';
                int sum = n1 * n2 + result[i_n1 + i_n2] + carry;
                carry = sum / 10;
                result[i_n1 + i_n2] = sum % 10;
                i_n2++;
            }
            if (carry > 0)
                result[i_n1 + i_n2] += carry;
            i_n1++;
        }
        int i = result.length - 1;
        while (i >= 0 && result[i] == 0) {
            i--;
        }
        if (i == -1) {
            return new PositiveBigInt("0");
        }
        StringBuilder s = new StringBuilder();
        while (i >= 0)
            s.append(result[i--]);
        return new PositiveBigInt(s.toString());
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
        while ((i < val.length()-1) && (charArray[i] == '0')) {
            i ++;
        }
        return val.substring(i);
    }

    /**
     * Adds two String values together.
     *
     * Implemented without using BigInts to save on performance. Private to hide from users.
     *
     * Assumes that the two inputted values can both be interpreted as integers
     *
     * Borrows code from StackExchange: https://codereview.stackexchange.com/questions/32954/adding-two-big-integers-represented-as-strings
     *
     * @param a String digits of an integer
     * @param b String digits of another integer
     * @return String for value1+value2 (in digits)
     */
    public static String addValues(String a, String b) {
        StringBuilder buf = new StringBuilder();
        for ( int i1 = a.length() - 1, i2 = b.length() - 1, carry = 0;
              i1 >= 0 || i2 >= 0 || carry != 0;
              i1--, i2-- ) {
            int digit1 = i1 < 0 ? 0 :
                    Integer.parseInt(Character.toString(a.charAt(i1)));
            int digit2 = i2 < 0 ? 0 :
                    Integer.parseInt(Character.toString(b.charAt(i2)));

            int digit = digit1 + digit2 + carry;
            if (digit > 9) {
                carry = 1;
                digit -= 10;
            } else {
                carry = 0;
            }

            buf.append(digit);
        }
        return buf.reverse().toString();
    }


        /**
         * Checks if an inputted String is an integer or not
         *
         * @param str String for an inputted string
         */
    private boolean stringIsInt(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }

    /**
     * Finds if an inputted String value for an integer is positive or not
     *
     * @param val String for an inputted value
     * @return boolean as described
     */
    private boolean valueIsPositive(String val) {
        return val.charAt(0) != '-';
    }

    /**
     * Calculates integer division of a/b, where 'a' is this BigInt.
     *
     * @param b BigInt object that divides this BigInt
     * @return BigInt object as described
     */
    public PositiveBigInt div(PositiveBigInt b) {
        PositiveBigInt ZERO = new PositiveBigInt("0");
        assert !b.equals(ZERO): "b cannot be zero!";
        assert (!isSmallerThan(b)) : "Calling PositiveBigInt cannot be less than the one in method parameter!";
        if (equals(b)){
            return new PositiveBigInt("1");
        }
        PositiveBigInt prevRemainder = this;
        PositiveBigInt remainder = diff(b);
        PositiveBigInt result = new PositiveBigInt("0");
        while (remainder.isSmallerThan(prevRemainder) && !remainder.equals(ZERO)) {
            result.increment();
            prevRemainder = remainder;
            remainder = remainder.diff(b);
        }
        if (remainder.equals(ZERO)) {
            result.increment();
        }
        return result;
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
     * Increments this PositiveBigInt by 1
     *
     */
    private void increment() {
         setValue(PositiveBigInt.addValues(value, "1"));
    }

    /**
     * Calculates abs(a-b), where a is this BigInt
     *
     * Equivalent to simple subtraction if a is bigger than b.
     *
     * Code borrowed from GeeksForGeeks: https://www.geeksforgeeks.org/difference-of-two-large-numbers/
     *
     * @param b BigObject to minus off this BigInt
     * @return BigInt object as described
     */
    public PositiveBigInt diff(PositiveBigInt b){
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
        return new PositiveBigInt(result.reverse().toString());
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
