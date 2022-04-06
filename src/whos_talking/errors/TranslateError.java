package whos_talking.errors;

/**
 * Class to represent an error when a user enters in invalid input when using the Translate program
 */
public class TranslateError extends Exception {

    /**
     * Constructor for a TranslateError
     *
     * @param msg String for the error message to be displayed to a user
     */
    public TranslateError(String msg) {
        super(msg);
    }

    /**
     * Gets the message of this Error
     *
     * @return String as described
     */
    public String getMessage() {
        return String.format("Invalid: %s", super.getMessage());
    }

}
