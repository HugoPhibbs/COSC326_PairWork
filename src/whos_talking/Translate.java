package whos_talking;
import whos_talking.errors.PhraseLengthError;
import whos_talking.errors.PronounError;
import whos_talking.errors.TranslateError;

import java.util.*;

import static java.util.Map.entry;

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

    public static void main(String[] args) {
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
        ArrayList<String> phrases = new ArrayList<>();
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
    public String translatePhrase(String phrase) {
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
        preTranslateChecks(phrase);
        ArrayList<String> phraseParts = phraseToParts(phrase);
        return translateParts(phraseParts.get(0), phraseParts.get(1));
    }

    /**
     * Performs pre parse checks before attempting to translate a phrase.
     *
     * Makes sure that a phrase isn't completely irrelevant before attempting to translate
     *
     * @param phrase String for an entered phrase from a user
     */
    private void preTranslateChecks(String phrase) {
        if (phrase == null) {
            throw new IllegalArgumentException("Phrase cannot be null!");
        }
        else if (phrase.equals("")) {
            throw new IllegalArgumentException("Phrase cannot be empty!");
        }
    }

    /**
     * Splits a Phrase into it's parts, i.e. into it's pronoun and verb clauses
     *
     * @param phrase String for an inputted phrase
     * @return ArrayList for the parts of the phrase. First element is the pronoun clause, and the second is the verb clause
     */
    private ArrayList<String> phraseToParts(String phrase) throws TranslateError {
        String[] splitPhrase = splitPhrase(phrase);
        checkSplitPhraseLength(splitPhrase);
        ArrayList<String> phraseParts = new ArrayList<>();
        switch (splitPhrase.length) {
            case 2 -> {handlePhraseLengthOf2(phraseParts, splitPhrase);}
            case 3 -> {handlePhraseLengthOf3(phraseParts, splitPhrase);}
            case 4 -> {handlePhraseLengthOf4(phraseParts, splitPhrase);}
            default -> throw new IllegalStateException("Split phrase length is invalid!");
        }
        return phraseParts;
    }

    /**
     * Handles case where a split phrase has a length of 3
     *
     * @param phraseParts Empty arraylist that the parts of phrase are to be added to
     * @param splitPhrase String array for an array split into its words (including a pronoun specifier)
     */
    private void handlePhraseLengthOf3(ArrayList<String> phraseParts, String[] splitPhrase) {
        assert splitPhrase.length == 3: "Split phrase must have a length of 3";
        assert phraseParts.size() == 0: "Phrase parts must be empty";
        if (splitPhrase[1].startsWith("(") && splitPhrase[1].endsWith(")")) {
            phraseParts.add(splitPhrase[0] + ' ' + splitPhrase[1]);
            phraseParts.add(splitPhrase[2]);
        }
        else {
            phraseParts.add(splitPhrase[0]);
            phraseParts.add(splitPhrase[1] + ' '+ splitPhrase[2]);
        }
    }

    /**
     * Handles case where a split phrase has a length of 2
     *
     * @param phraseParts Empty arraylist that the parts of phrase are to be added to
     * @param splitPhrase String array for an array split into its words (including a pronoun specifier)
     */
    private void handlePhraseLengthOf2(ArrayList<String> phraseParts, String[] splitPhrase) {
        phraseParts.add(splitPhrase[0]);
        phraseParts.add(splitPhrase[1]);
    }

    /**
     * Handles case where a split phrase has a length of 4
     *
     * @param phraseParts Empty arraylist that the parts of phrase are to be added to
     * @param splitPhrase String array for an array split into its words (including a pronoun specifier)
     */
    private void handlePhraseLengthOf4(ArrayList<String> phraseParts, String[] splitPhrase) {
        phraseParts.add(splitPhrase[0] + ' ' + splitPhrase[1]);
        phraseParts.add(splitPhrase[2] + ' ' + splitPhrase[3]);
    }

    /**
     * Splits a phrase into its words. Including any specifier on a pronoun
     *
     * @return String array containing phrase parts as described
     */
    private String[] splitPhrase(String phrase) {
        assert phrase != null: "Phrase cannot be null!";
        ArrayList<String> splitPhrase = new ArrayList<>(Arrays.asList((phrase.split(" "))));
        if (splitPhrase.size() >= 3) {
            if (splitPhrase.get(1).startsWith("(") & splitPhrase.get(2).endsWith(")")) {
                String pronoun_specifier = String.format("%s %s", splitPhrase.get(1), splitPhrase.get(2));
                splitPhrase.remove(1);
                splitPhrase.remove(1);
                splitPhrase.add(1, pronoun_specifier);
            }
        }
        return splitPhrase.toArray(new String[0]);
    }

    /**
     * Checks the length of a phrase is too long or too short.
     *
     * @param splitPhrase String[] for a split phrase from a user
     * @throws PhraseLengthError if the inputted phrase is too long or short
     */
    private void checkSplitPhraseLength(String[] splitPhrase) throws PhraseLengthError {
        if (splitPhrase.length > 4) {
            throw new PhraseLengthError("Phrase length is too long");
        }
        else if (splitPhrase.length < 2) {
            throw new PhraseLengthError("Phrase length is too short");
        }

    }

    /**
     * Translates the parts of an English phrase to Maori
     *
     * @param pronounClause String for an English pronoun clause
     * @param verbClause String for an English verb clause
     * @return String for the inputted clauses combined into a coherent Maori sentence
     * @throws TranslateError if the inputted clauses could not be translated to Maori
     */
    private String translateParts(String pronounClause, String verbClause) throws TranslateError {
        String pronoun = translatePronounClause(pronounClause);
        // Now get verb clause
        // return constructMaoriSentence(tenseMarker, verb, pronoun);
        return null;
        // Translate pronoun clause
        // Translate verb clause
        // Add results together TODO how to do this, given that tense is given at the start of a maori sentence?

        // Could break the maori phrase into Marker-Pronoun-Verb.
        // Verb clause could return the marker and verb? -  in the form of String array/ArrayList
        // Then this makes adding the results easy. Up to you Ben, don't want to micromanage ur part.
    }

    /**
     * Construct a Maori sentence from inputted Maori words
     *
     * @param tenseMarker String for the tense marker of a sentence
     * @param verb String for the verb of a sentence
     * @param pronoun String for the pronoun of a sentence
     * @return String for a constructed Maori sentence
     */
    private String constructMaoriSentence(String tenseMarker, String verb, String pronoun) {
        return String.format("%s %s %s", tenseMarker, verb, pronoun);
    }

    /**
     * Translates a Pronoun clause in English to Maori
     *
     * @param pronounClause String for a pronoun clause
     * @throws PronounError if the inputted pronounClause could not be translated to Maori
     * @return String for a Maori translation of the inputted English pronoun clause
     */
    private String translatePronounClause(String pronounClause) throws PronounError {
        checkPronounClause(pronounClause);
        assert pronounMap.containsKey(pronounClause): "Pronoun should be valid";
        return pronounMap.get(pronounClause);
    }

    /**
     * Runs checks on an inputted pronoun clause
     *
     * @param pronounClause String for an inputted pronoun clause from a user
     * @throws PronounError if the inputted pronoun clause is invalid
     */
    private void checkPronounClause(String pronounClause) throws PronounError{
        if (!pronounMap.containsKey(pronounClause)) {
            if (pronounMap.containsKey(capitalizeString(pronounClause))) {
                throw new PronounError("Pronoun should be capitalized");
            }
            throw new PronounError("Pronoun is not valid");
        }
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

    /**
     * Creates a HashMap that is used to map English to Maori pronouns
     */
    private void createPronounMap() {
        pronounMap = new HashMap<>(Map.ofEntries(
                entry("I", "au"),
                entry("He",  "ia"),
                entry("She", "ia"),
                entry("They (2 excl)", "rāua"),
                entry("They (3 excl)", "rātou"),
                entry("We (2 incl)", "tāua"),
                entry("We (3 incl)", "tātou"),
                entry("We (2 excl)", "māua"),
                entry("We (3 excl)", "mātou"),
                entry("You (3 excl)", "koutou"),
                entry("You (2 excl)", "kōrua"),
                entry("You (1 excl)", "koe")
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

    // Utility Methods

    /**
     * Capitalizes a String, all characters following the first are put into lower case
     *
     * @param str String to be capitalized
     * @return A capitalized String
     */
    private String capitalizeString(String str) {
        if (str.length() == 0){
            return str;
        }
        return str.substring(0, 1).toUpperCase(Locale.ROOT) + str.substring(1).toLowerCase(Locale.ROOT);
    }

    /**
     * Checks if a string is capitalized or not
     *
     * Check capitalizeString(String) for my definition for a capitalized String
     *
     * @param str String as described
     * @return boolean as described
     */
    private boolean stringIsCapitalized(String str) {
        return (str.equals(this.capitalizeString(str)));
    }

}