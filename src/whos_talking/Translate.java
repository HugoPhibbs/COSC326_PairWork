package whos_talking;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class to translate input of simple English Sentences into Maori
 */
public class Translate{

    /**
     * HashMap mapping English tenses to Maori tense markers
     */
    HashMap<String, String> tenseMap = createTenseMap();

    /**
     * HashMap mapping base english verbs to Maori verbs
     */
    HashMap<String, String> verbMap = createVerbMap();

    public static void main(String[] args) throws IOException {
        new Translate().start();
    }

    /**
     * Starts the Who'  s Talking program
     */
    public void start() {
        ArrayList<String> phrases = getInput();
        printTranslatedPhrases(phrases);
    }

    /**
     * Translates an English phrase to a Maori one
     *
     * @param phrase String for an English phrase
     * @return String for a Maori phrase as described
     */
    private String translatePhrase(String phrase) {
        return "";
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
     * Creates a hashMap that maps English verbs to maori ones
     *
     * @return HashMap mapping String to String as described
     */
    private HashMap<String, String> createVerbMap() {
        HashMap<String, String> verbMap = new HashMap<>();
        return null; // TODO
    }

    /**
     * Creates a HashMap that maps English tenses to Maori tense markers
     *
     * @return HashMap mapping String to String as described
     */
    private HashMap<String, String> createTenseMap() {
        HashMap<String, String> tenseMap = new HashMap<>();
        tenseMap.put("Past", "I");
        tenseMap.put("Present", "Kei te");
        tenseMap.put("Future", "Ka");
        return tenseMap;
    }
}