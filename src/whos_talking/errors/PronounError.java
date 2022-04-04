package whos_talking.errors;

/**
 * Class to represent an error to do with translating an English pronoun phrase to Maori
 */
public class PronounError extends TranslateError {
    /**
     * Constructor for an InvalidError
     *
     * @param msg String for the error message to be displayed to a user
     */
    public PronounError(String msg) {
        super(msg);
    }
}
