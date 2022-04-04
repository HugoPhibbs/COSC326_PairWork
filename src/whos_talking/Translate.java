package whos_talking;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;
import java.util.Scanner;

/**
 * Class to translate input of simple English Sentences into Maori
 */
public class Translate{

    /**
     * HashMap mapping English pronouns to Maori ones
     */
    private HashMap<String, String> pronounMap;

    /**
     * HashMap mapping English verbs to Maori ones, plus their tenses
     */
    private HashMap<String, String> verbMap;

    /**
     * HashMap containing English tenses with their Maori marker equivalents
     */
    private HashMap<String, String> tenseMap;

    /**
     * Constructor for a Translate object
     */
    Translate() {
        createMaps();
    }

    public static void main(String[] args) throws IOException {
        new Translate().start();
    }

    /**
     * Starts the Who's Talking program
     */
    public void start() {
        ArrayList<String> phrases = getInput();
        printTranslatedPhrases(phrases);
    }

    /**
     * Prints Maori translations of entered English translations from a user
     *
     * @param phrases ArrayList containing Strings for English phrases
     */
    private void printTranslatedPhrases(ArrayList<String> phrases) {
        System.out.println("Output:");
        for (String phrase : phrases) {
            System.out.println(translatePhrase(phrase));
        }
    }

    /**
     * Gets a list of English phrases to be translated to Maori
     *
     * @return ArrayList containing phrases as described
     */
    private ArrayList<String> getInput() {
        System.out.println("Welcome to Translator\nEnter one English phrase per line\nHit enter on an empty line to submit");
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> phrases = new ArrayList<String>();
        String phrase = scanner.nextLine();
        while (!phrase.equals("")) {
            phrases.add(phrase);
            phrase = scanner.nextLine();
        }
        return phrases;
    }

    /**
     * Translates an English phrase to a Maori one
     *
     * Main method where other tasks branch off from
     *
     * @param phrase String for an English phrase
     * @return String for a Maori phrase as described
     */
    private String translatePhrase(String phrase) {
        try {
            return translatePhraseHelper(phrase);
        } catch (TranslateError te) {
            return te.getMessage();
        }
    }

    /**
     * Helper method to translatePhrase(String). Where the actual logic lies for translatePhrase.
     *
     * Throws methods up to call level to allow for easy testing, hence why it is also public and translatePhrase is not
     *
     * @param phrase String for a phrase
     * @return String for an english phrase translated into Maori
     * @throws TranslateError if the inputted phrase could not be translated
     */
    public String translatePhraseHelper(String phrase) throws TranslateError{
        ArrayList<String> phraseParts = phraseToParts(phrase);
        return translateParts(phraseParts);
    }

    /**
     * Splits a Phrase into it's parts, i.e. into it's pronoun and verb clauses
     *
     * @param phrase String for an inputted phrase
     * @return ArrayList for the parts of the phrase. First element is the pronoun clause, and the second is the verb clause
     */
    private ArrayList<String> phraseToParts(String phrase) throws TranslateError {
        String[] splitPhrase = phrase.split(" ");
        checkSplitPhraseLength(splitPhrase);
        ArrayList<String> phraseParts = new ArrayList<>();
        switch (splitPhrase.length) {
            case 2 -> {
                phraseParts.add(splitPhrase[0]);
                phraseParts.add(splitPhrase[1]);
            }
            case 3 -> {
                phraseParts.add(splitPhrase[0] + ' ' + splitPhrase[1]);
                phraseParts.add(splitPhrase[2]);
            }
            case 4 -> {
                phraseParts.add(splitPhrase[0] + ' ' + splitPhrase[1]);
                phraseParts.add(splitPhrase[2] + ' ' + splitPhrase[3]);
            }
            default -> throw new IllegalStateException("Split phrase length is invalid!");
        }
        return phraseParts;
    }

    /**
     * Checks the length of a phrase is too long or too short.
     *
     * @param splitPhrase String[] for a split phrase from a user
     * @throws TranslateError if the inputted phr
     */
    private void checkSplitPhraseLength(String[] splitPhrase) throws TranslateError {
        if (splitPhrase.length > 4) {
            throw new TranslateError("Phrase length is too long");
        }
        else if (splitPhrase.length < 2) {
            throw new TranslateError("Phrase length is too short");
        }

    }

    private String translateParts(ArrayList<String> phrase) {
        return null;
        // Translate pronoun clause
        // Translate verb clause
        // Add results together TODO how to do this, given that tense is given at the start of a maori sentence?

        // Could break the maori phrase into Marker-Pronoun-Verb.
        // Verb clause could return the marker and verb? -  in the form of String array/ArrayList
        // Then this makes adding the results easy. Up to you Ben, don't want to micromanage ur part.
    }

    private String translatePronounClause(String pronounClause) {
        // TODO
        return "";
    }

    private String translateVerbClause(String verbClause) {
        return "";
    }
    
    // Creating Class HashMap attributes

    /**
     * Creates the HashMaps used for translating English to Maori phrases
     */
    private void createMaps() {
        createPronounMap();
        createVerbMap();
        createTenseMap();
    }

    private void createPronounMap() {
        pronounMap = new HashMap<>(Map.ofEntries(
                entry("I", "au"),
                entry("he",  "ia"),
                entry("she", "ia"),
                entry("they (2 excl)", "rāua"),
                entry("they (3 excl)", "rātou"),
                entry("we (2 incl)", "tāua"),
                entry("we (3 incl)", "tātou"),
                entry("we (2 excl)", "māua"),
                entry("we (3 excl)", "mātou"),
                entry("you (3 excl)", "koutou"),
                entry("you (2 excl)", "kōrua"),
                entry("you (1 excl)", "koe")
        ));
    }
    
    /**
     * Creates the verb map used for translation from english to maori
     *
     */
    private void createVerbMap() {
        verbMap = new HashMap<>();
        verbMap.put("go","haere");
        verbMap.put("make","hanga");
        verbMap.put("see","kite");
        verbMap.put("want", "hiahia");
        verbMap.put("call","karanga");
        verbMap.put("ask","pātai");
        verbMap.put("read","pānui");
        verbMap.put("learn","ako");
    }

    /**
     * Creates a HashMap that maps English tenses to Maori tense markers
     *
     */
    private void createTenseMap() {
        tenseMap = new HashMap<>();
        tenseMap.put("Past", "I");
        tenseMap.put("Present", "Kei te");
        tenseMap.put("Future", "Ka");
    }
}