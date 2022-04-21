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
     * @param value1 String digits of an integer
     * @param value2 String digits of another integer
     * @return String for value1+value2 (in digits)
     */
    private String addValues(String value1, String value2) {
        return null; // TODO
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
     * Calculates a-b, where a is this BigInt
     *
     * @param b BigObject to minus off this BigInt
     * @return BigInt object as described
     */
    public BigInt minus(BigInt b){
        return null; // TODO
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
