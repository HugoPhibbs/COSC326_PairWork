package whos_talking.errors;

/**
 * Class to represent an error when a user enters in invalid input when using the Translate program
 */
public class TranslateError extends Exception {

    /**
     * Constructor for a TranslateError
     *
     * @param msg String for a message to be displayed to a user
     */
    public TranslateError(String msg) {
        super(msg);
    }

    /**
     * Constructor for a TranslateError
     *
     * @param value String for the value of a parameter that caused an error
     * @param label String for a label for 'value'
     * @param msg String for a message to accompany this error
     */
    public TranslateError(String value, String label, String msg) {
        super(TranslateError.errorMsg(value, label, msg));
    }

    /**
     * Formats and returns a String that describes the problem with a particular value.
     *
     * Made into it's own method to avoid repetition
     *
     * @param value String for the value that is causing an error
     * @param label String for a label for the inputted value. I.e. 'Pronoun' or 'Verb'
     * @param msg String for a short comment to be displayed to a user, indicating the problem
     * @return String as described
     */
    private static String errorMsg(String value, String label, String msg){
        return String.format("Invalid: %s \"%s\" is %s", label, value, msg);
    }
}
