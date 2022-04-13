package whos_talking.errors;

/**
 * Class to represent an error to do with an invalid verb being input by a user
 *
 */
public class VerbError extends TranslateError{

    /**
     * Constructor for a VerbError
     *
     * @param value String for the value of a parameter that caused an error
     * @param label String for a label for 'value'
     * @param msg   String for a message to accompany this error
     */
    public VerbError(String value, String label, String msg) {
        super(value, label, msg);
    }
}
