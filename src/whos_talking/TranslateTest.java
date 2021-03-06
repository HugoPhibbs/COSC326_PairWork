package whos_talking;

import org.junit.jupiter.api.Test;
import whos_talking.errors.PhraseLengthError;
import whos_talking.errors.PronounError;
import whos_talking.errors.TranslateError;
import whos_talking.errors.VerbError;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing the 'Translate' program
 */
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
        assertThrows(PronounError.class, () -> translate.translatePhraseHelper("You (3 excl9) are seeing"));

        assertEquals("Ka haere koutou", translate.translatePhrase("You (4 incl) will go"));
        assertEquals("Ka haere koutou", translate.translatePhrase("You (3 incl) will go"));
        assertEquals("Ka haere koutou", translate.translatePhrase("You (100 incl) will go"));
        assertEquals("Kei te haere mātou", translate.translatePhrase("We (4 excl) are going"));

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
    public void testExamples() {
        assertEquals("Kei te haere mātou", translate.translatePhrase("We (3 excl) are going"));
        assertEquals("Kei te haere au", translate.translatePhrase("I am going"));
        assertEquals("Kei te pānui rāua", translate.translatePhrase("They (2 excl) are reading"));
        assertEquals("Kei te pānui kōrua", translate.translatePhrase("You (2 incl) are reading"));
        assertEquals("I haere au", translate.translatePhrase("I went"));
        assertEquals("Ka haere au", translate.translatePhrase("I will go"));

        assertThrows(PhraseLengthError.class, () -> translate.translatePhraseHelper("gibberish"));
        assertThrows(VerbError.class, () -> translate.translatePhraseHelper("We (3 excl) are coming"));
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
    @Test
    public void testTranslation(){
        assertThrows(TranslateError.class, () -> translate.translatePhraseHelper("We (3 excl) to want"));
        assertThrows(TranslateError.class, () -> translate.translatePhraseHelper("We (3) are going"));
        assertThrows(TranslateError.class, () -> translate.translatePhraseHelper("We (3) are go"));
    }

    @Test
    public void testVerbs(){
        assertThrows(TranslateError.class, () -> translate.translatePhraseHelper("We (3 excl) coming"));
        assertThrows(TranslateError.class, () -> translate.translatePhraseHelper("We (3 excl) tooo make"));
        assertThrows(TranslateError.class, () -> translate.translatePhraseHelper("We (3) are go"));
    }
}
