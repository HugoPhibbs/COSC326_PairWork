package whos_talking.errors;

/**
 * Class to represent error when a phrase is too long or short
 */
public class PhraseLengthError extends TranslateError {

    /**
     * Constructor for an InvalidError
     *
     * @param msg String for the error message to be displayed to a user
     */
    public PhraseLengthError(String msg) {
        super(msg);
    }
}
