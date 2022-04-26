package whos_talking;

import whos_talking.errors.PhraseLengthError;
import whos_talking.errors.PronounError;
import whos_talking.errors.TranslateError;
import whos_talking.errors.VerbError;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.lang.*;

import static java.util.Map.entry;

/**
 * Class to translate input of simple English Sentences into Maori
 */
public class Translate {

    /**
     * HashMap mapping English pronouns to Maori ones
     */
    private HashMap<String, String> pronounMap;

    /**
     * HashMap mapping English verbs to Maori ones, plus their tenses
     */
    private HashMap<String, String[]> verbMap;

    /**
     * Constructor for a Translate object
     */
    Translate() {
        createMaps();
    }

    /**
     * Main method for this program
     *
     * @param args String array for arguments. If left empty, then Translates saves
     *             to a file, otherwise this can be specified.
     */
    public static void main(String[] args) {
        assert args.length <= 1 : "Must have no more than one argument!";
        if (args.length == 1) {
            new Translate().start(args[0]);
        } else {
            new Translate().start("file");
        }
    }

    /**
     * Starts the Who's Talking program
     * 
     * @param outputOption how the result of this program should be outputted
     */
    public void start(String outputOption) {
        assert outputOption.equals("cmd") || outputOption.equals("file") : "Output option must be either cmd or file!";
        ArrayList<String> translatedPhrases = translatePhrases(getInput());
        if (outputOption.equals("cmd")) {
            printTranslatedPhrases(translatedPhrases);
        } else {
            saveTranslatesPhrases(translatedPhrases);
        }
    }

    /**
     * Prints translated phrases
     *
     * @param translatedPhrases ArrayList containing Strings for translated Maori
     *                          phrases
     */
    private void printTranslatedPhrases(ArrayList<String> translatedPhrases) {
        System.out.println("Output:");
        for (String phrase : translatedPhrases) {
            System.out.println(phrase);
        }
    }

    /**
     * Translates an list of english phrases to Maori, returns a list of this result
     *
     * @param englishPhrases Arraylist of english phrases
     * @return ArrayList as describe
     */
    private ArrayList<String> translatePhrases(ArrayList<String> englishPhrases) {
        ArrayList<String> translatedPhrases = new ArrayList<>();
        for (String phrase : englishPhrases) {
            translatedPhrases.add(translatePhrase(phrase));
        }
        return translatedPhrases;
    }

    /**
     * Writes translated phrases to a text file in the same directory as this class
     *
     * @param phrases ArrayList of translated phrases
     */
    private void saveTranslatesPhrases(ArrayList<String> phrases) {
        String phrasesJoint = String.join("\n", phrases);
        try {
            // TODO make this the same directory as class, rn its going to project directory
            FileWriter fileWriter = new FileWriter("translated_phrases.txt");
            fileWriter.write(phrasesJoint);
            fileWriter.flush();
            fileWriter.close();
            System.out.println("See newly created txt file for output");
        } catch (IOException ioe) {
            System.out.println("Saving to file failed");
            ioe.printStackTrace();
        }

    }

    /**
     * Gets a list of English phrases to be translated to Maori
     *
     * @return ArrayList containing phrases as described
     */
    private ArrayList<String> getInput() {
        System.out.println(
                "Welcome to Translator\nEnter one English phrase per line\nHit enter on an empty line to submit");
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
            return String.format("\"%s\" - %s", phrase, te.getMessage());
        }
    }

    /**
     * Helper method to translatePhrase(String). Where the actual logic lies for
     * translatePhrase.
     *
     * Throws methods up to call level to allow for easy testing, hence why it is
     * also public and translatePhrase is not
     *
     * @param phrase String for a phrase
     * @return String for an english phrase translated into Maori
     * @throws TranslateError if the inputted phrase could not be translated
     */
    public String translatePhraseHelper(String phrase) throws TranslateError {
        preTranslateChecks(phrase);
        ArrayList<String> phraseParts = phraseToParts(phrase);
        return translateParts(phraseParts.get(0), phraseParts.get(1));
    }

    /**
     * Performs pre parse checks before attempting to translate a phrase.
     *
     * Makes sure that a phrase isn't completely irrelevant before attempting to
     * translate
     *
     * @param phrase String for an entered phrase from a user
     */
    private void preTranslateChecks(String phrase) {
        if (phrase == null) {
            throw new IllegalArgumentException("Phrase cannot be null!");
        } else if (phrase.equals("")) {
            throw new IllegalArgumentException("Phrase cannot be empty!");
        }
    }

    /**
     * Splits a Phrase into it's parts, i.e. into it's pronoun and verb clauses
     *
     * @param phrase String for an inputted phrase
     * @return ArrayList for the parts of the phrase. First element is the pronoun
     *         clause, and the second is the verb clause
     */
    private ArrayList<String> phraseToParts(String phrase) throws TranslateError {
        String[] splitPhrase = splitPhrase(phrase);
        checkSplitPhraseLength(splitPhrase);
        ArrayList<String> phraseParts = new ArrayList<>();
        switch (splitPhrase.length) {
            case 2 -> {
                handlePhraseLengthOf2(phraseParts, splitPhrase);
            }
            case 3 -> {
                handlePhraseLengthOf3(phraseParts, splitPhrase);
            }
            case 4 -> {
                handlePhraseLengthOf4(phraseParts, splitPhrase);
            }
            default -> throw new IllegalStateException("Split phrase length is invalid!");
        }
        return phraseParts;
    }

    /**
     * Handles case where a split phrase has a length of 3
     *
     * @param phraseParts Empty arraylist that the parts of phrase are to be added
     *                    to
     * @param splitPhrase String array for an array split into its words (including
     *                    a pronoun specifier)
     */
    private void handlePhraseLengthOf3(ArrayList<String> phraseParts, String[] splitPhrase) {
        assert splitPhrase.length == 3 : "Split phrase must have a length of 3";
        assert phraseParts.size() == 0 : "Phrase parts must be empty";
        if (splitPhrase[1].startsWith("(") && splitPhrase[1].endsWith(")")) {
            phraseParts.add(splitPhrase[0] + ' ' + splitPhrase[1]);
            phraseParts.add(splitPhrase[2]);
        } else {
            phraseParts.add(splitPhrase[0]);
            phraseParts.add(splitPhrase[1] + ' ' + splitPhrase[2]);
        }
    }

    /**
     * Handles case where a split phrase has a length of 2
     *
     * @param phraseParts Empty arraylist that the parts of phrase are to be added
     *                    to
     * @param splitPhrase String array for an array split into its words (including
     *                    a pronoun specifier)
     */
    private void handlePhraseLengthOf2(ArrayList<String> phraseParts, String[] splitPhrase) {
        phraseParts.add(splitPhrase[0]);
        phraseParts.add(splitPhrase[1]);
    }

    /**
     * Handles case where a split phrase has a length of 4
     *
     * @param phraseParts Empty arraylist that the parts of phrase are to be added
     *                    to
     * @param splitPhrase String array for an array split into its words (including
     *                    a pronoun specifier)
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
        assert phrase != null : "Phrase cannot be null!";
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
        } else if (splitPhrase.length < 2) {
            throw new PhraseLengthError("Phrase length is too short");
        }

    }

    /**
     * Translates the parts of an English phrase to Maori
     *
     * @param pronounClause String for an English pronoun clause
     * @param verbClause    String for an English verb clause
     * @return String for the inputted clauses combined into a coherent Maori
     *         sentence
     * @throws TranslateError if the inputted clauses could not be translated to
     *                        Maori
     */
    private String translateParts(String pronounClause, String verbClause) throws TranslateError {
        String maoriPronoun = translatePronounClause(pronounClause);
        String[] tenseVerb = translateVerbClause(verbClause);
        return constructMaoriSentence(tenseVerb[1], tenseVerb[0], maoriPronoun);
    }

    /**
     * Construct a Maori sentence from inputted Maori words
     *
     * @param tenseMarker String for the tense marker of a sentence
     * @param verb        String for the verb of a sentence
     * @param pronoun     String for the pronoun of a sentence
     * @return String for a constructed Maori sentence
     */
    private String constructMaoriSentence(String tenseMarker, String verb, String pronoun) {
        return String.format("%s %s %s", tenseMarker, verb, pronoun);
    }

    /**
     * Translates a Pronoun clause in English to Maori
     *
     * @param pronounClause String for a pronoun clause
     * @throws PronounError if the inputted pronounClause could not be translated to
     *                      Maori
     * @return String for a Maori translation of the inputted English pronoun clause
     */
    private String translatePronounClause(String pronounClause) throws PronounError {
        try{
        String remake = checkGreaterThen3(pronounClause);
        checkPronounClause(remake);
        assert pronounMap.containsKey(remake) : "Pronoun should be valid";
        return pronounMap.get(remake);
        
        } catch (Exception e) {
            throw new PronounError(pronounClause, "Pronoun", "not recognized");
        }

    }

    /**
     * Runs checks on an inputted pronoun clause
     *
     * @param pronounClause String for an inputted pronoun clause from a user
     * @throws PronounError if the inputted pronoun clause is invalid
     */
    private void checkPronounClause(String pronounClause) throws PronounError {
        if (!pronounMap.containsKey(pronounClause)) {
            if (pronounMap.containsKey(capitalizeString(pronounClause))) {
                throw new PronounError(pronounClause, "Pronoun", "should be capitalized");
            }
            throw new PronounError(pronounClause, "Pronoun", "not recognized");
        }
    }

    /**
     * translates verb clause to maori version.
     * 
     * @param verbClause String for translation.
     * @return string translated from english.
     */

    private String[] translateVerbClause(String verbClause) throws TranslateError {
        if (!verbMap.containsKey(verbClause)) {
            throw new VerbError(verbClause, "Verb", "not recognized");
        }
        return verbMap.get(verbClause);
    }

    // Creating Class HashMap attribute.
    /**
     * Creates the HashMaps used for translating English to Maori phrases
     */
    private void createMaps() {
        createPronounMap();
        createVerbMap();

    }
    /** 
     * checks if the number given within the brackets is greater then 3 and swithch to 3 if so.
     * @string the pronoun
     * @string the pronoun remade.
     */
    public String checkGreaterThen3(String pronoun) throws PronounError{
        if(pronounMap.get(pronoun) != null){
            return pronoun;
        }else {
            try{
                StringBuilder sb = new StringBuilder(100);
                for (int i = 0 ;i<pronoun.length(); i++ ) {
                    char ch = pronoun.charAt(i);
                    if(Character.isDigit(ch)){
                        sb.append(ch);
                    }

                }
                String num = sb.toString();
                if(Integer.parseInt(num) > 3){
                    String remake = pronoun.replace(num, "3");
                    return remake;
                } else {
                    return pronounMap.get(pronoun);
                }
            } catch(Exception e){
                throw new PronounError(pronoun, "pronoun", "not recognized");
            }
        }
    }

    /**
     * Creates a HashMap that is used to map English to Maori pronouns
     */
    private void createPronounMap() {
        pronounMap = new HashMap<>(Map.ofEntries(
                entry("I", "au"),
                entry("He", "ia"),
                entry("She", "ia"),
                entry("They (2 excl)", "rāua"),
                entry("They (3 excl)", "rātou"),
                entry("We (2 incl)", "tāua"),
                entry("We (3 incl)", "tātou"),
                entry("We (2 excl)", "māua"),
                entry("We (3 excl)", "mātou"),
                entry("You (3 incl)", "koutou"),
                entry("You (2 incl)", "kōrua"),
                entry("You (1 incl)", "koe")));
    }

    /**
     * Creates the verb map used for translation from English to Maori
     */
    private void createVerbMap() {
        verbMap = new HashMap<>();
        String present = "Kei te";
        String past = "I";
        String future = "Ka";
        // go
        String go = "haere";
        verbMap.put("are going", new String[] { go, present });
        verbMap.put("am going", new String[] { go, present });
        verbMap.put("is going", new String[] { go, present });
        verbMap.put("will go", new String[] { go, future });
        verbMap.put("went", new String[] { go, past });
        // make
        String make = "hanga";
        verbMap.put("are making", new String[] { make, present });
        verbMap.put("is making", new String[] { make, present });
        verbMap.put("am making", new String[] { make, present });
        verbMap.put("will make", new String[] { make, future });
        verbMap.put("made", new String[] { make, past });
        // see
        String see = "kite";
        verbMap.put("is seeing", new String[] { see, present });
        verbMap.put("are seeing", new String[] { see, present });
        verbMap.put("am seeing", new String[] { see, present });
        verbMap.put("will see", new String[] { see, future });
        verbMap.put("saw", new String[] { see, past });
        // want
        String want = "hiahia";
        verbMap.put("am wanting", new String[] { want, present });
        verbMap.put("are wanting", new String[] { want, present });
        verbMap.put("is wanting", new String[] { want, present });
        verbMap.put("will want", new String[] { want, future });
        verbMap.put("wanted", new String[] { want, past });
        // call
        String call = "karanga";
        verbMap.put("is calling", new String[] { call, present });
        verbMap.put("are calling", new String[] { call, present });
        verbMap.put("am calling", new String[] { call, present });
        verbMap.put("will call", new String[] { call, future });
        verbMap.put("called", new String[] { call, past });
        // ask
        String ask = "pātai";
        verbMap.put("is asking", new String[] { ask, present });
        verbMap.put("am asking", new String[] { ask, present });
        verbMap.put("are asking", new String[] { ask, present });
        verbMap.put("will ask", new String[] { ask, future });
        verbMap.put("asked", new String[] { ask, past });
        // read
        String read = "pānui";
        verbMap.put("is reading", new String[] { read, present });
        verbMap.put("am reading", new String[] { read, present });
        verbMap.put("are reading", new String[] { read, present });
        verbMap.put("will read", new String[] { read, future });
        verbMap.put("read", new String[] { read, past });
        // learn
        String learn = "learn";
        verbMap.put("are learning", new String[] { learn, present });
        verbMap.put("is learning", new String[] { learn, present });
        verbMap.put("am learning", new String[] { learn, present });
        verbMap.put("will learn", new String[] { learn, future });
        verbMap.put("learnt", new String[] { learn, past });
    }

    // Utility Methods

    /**
     * Capitalizes a String, all characters following the first are put into lower
     * case
     *
     * @param str String to be capitalized
     * @return A capitalized String
     */
    private String capitalizeString(String str) {
        if (str.length() == 0) {
            return str;
        }
        return str.substring(0, 1).toUpperCase(Locale.ROOT) + str.substring(1).toLowerCase(Locale.ROOT);
    }
}