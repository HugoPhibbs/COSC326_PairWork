package whos_talking.errors;

/**
 * Class to represent an error to do with translating an English pronoun phrase to Maori
 */
public class PronounError extends TranslateError {

    /**
     * Constructor for a TranslateError
     *
     * @param value String for the value of a parameter that caused an error
     * @param label String for a label for 'value'
     * @param msg   String for a message to accompany this error
     */
    public PronounError(String value, String label, String msg) {
        super(value, label, msg);
    }
}
