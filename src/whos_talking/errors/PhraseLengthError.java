package whos_talking.errors;

/**
 * Class to represent error when a phrase is too long or short
 */
public class PhraseLengthError extends TranslateError {

    /**
     * Constructor for a PhraseLengthError
     *
     * @param msg: String for a message to be displayed to this user
     */
    public PhraseLengthError(String msg) {
        super(msg);
    }
}
