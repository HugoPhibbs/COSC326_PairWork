package whos_talking;

import org.junit.Test;
import whos_talking.errors.PhraseLengthError;
import whos_talking.errors.PronounError;
import whos_talking.errors.TranslateError;

import static org.junit.jupiter.api.Assertions.*;

public class TranslateTest {

    /**
     * Translate object used for testing
     */
    private final Translate translate = new Translate();

    @Test
    public void testPronouns() {
        // Test incorrect input
        assertThrows(PronounError.class, () -> translate.translatePhraseHelper("we (3 excl) are going"));
        assertThrows(PronounError.class, () -> translate.translatePhraseHelper("They (3excl) are going"));
        assertThrows(PronounError.class, () -> translate.translatePhraseHelper("They are going"));
        assertThrows(PronounError.class, () -> translate.translatePhraseHelper("We saw"));
        assertThrows(PronounError.class, () -> translate.translatePhraseHelper("He (2 excl) is seeing"));
        assertThrows(PronounError.class, () -> translate.translatePhraseHelper("He is is seeing"));

        // Test with correct input
        // TODO update these with implementation!
        try {
            translate.translatePhraseHelper("We (3 excl) are going");
            translate.translatePhraseHelper("I am going");
            translate.translatePhraseHelper("We (2 excl) saw");
            translate.translatePhraseHelper("She saw");
            translate.translatePhraseHelper("He is seeing");
        } catch (TranslateError te) {
            te.printStackTrace();
            fail();
        }

    }

    @Test
    public void testIrrelevantInput() {
        assertThrows(IllegalArgumentException.class, () -> translate.translatePhraseHelper(null));
        assertThrows(IllegalArgumentException.class, () -> translate.translatePhraseHelper(""));
    }

    @Test
    public void testPhraseLength() {
        assertThrows(PhraseLengthError.class, () -> translate.translatePhraseHelper("hello"));
        assertThrows(PhraseLengthError.class, () -> translate.translatePhraseHelper("Gibberish"));
        assertThrows(PhraseLengthError.class, () -> translate.translatePhraseHelper("He is slowly going away"));
    }
}