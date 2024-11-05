import java.util.Random;
import java.util.List;
import java.util.LinkedHashMap;

/**
 * The author class is responsible for writing the stories, taking into account the frequency
 * of certain types of words and the linked hashmap containing the words and their types.
 * I did heavily modify this so that I could meet requirements, I do plan on asking about
 * redundancies and what is and isn't necessary here.
 */
public class Author {
    // private data fields
    private LinkedHashMap<String, List<String>> wordMap;
    private final Random random;
    private int adjFreq;
    private int advFreq;
    private int preFreq;


    /**
     * Default Constructor
     */
    public Author() {
        this.wordMap = new LinkedHashMap<>();
        this.random = new Random();
        this.adjFreq = 0;
        this.advFreq = 0;
        this.preFreq = 0;
    }

    /**
     * Non-default constructor
     * @param wordMap takes in the map of words and their types read from the word file reader
     */
    public Author(LinkedHashMap<String, List<String>> wordMap) {
        this.wordMap = wordMap;
        this.random = new Random();
    }

    // get and set methods

    /**
     * Gets the word map the user chooses to use from the App class
     * @return the word map given by the user
     */
    public LinkedHashMap<String, List<String>> getWordMap() {
        return wordMap;
    }

    /**
     * sets the word map from the user input
     * @param wordMap takes in the word map retrieved from getWordMap()
     */
    public void setWordMap(LinkedHashMap<String, List<String>> wordMap) {
        this.wordMap = wordMap;
    }

    /**
     * Gets the adjective frequency the user chooses to use from the App class
     * @return the adjective frequency chosen by the user
     */
    public int getAdjFreq() {
        return adjFreq;
    }

    /**
     * sets the adjective frequency the user chooses
     * @param adjFreq takes in the adjective frequency retrieved from getAdjFreq()
     */
    public void setAdjFreq(int adjFreq) {
        this.adjFreq = adjFreq;
    }

    /**
     * Gets the adverb frequency the user chooses to use from the App class
     * @return the adverb frequency chosen by the user
     */
    public int getAdvFreq() {
        return advFreq;
    }

    /**
     * sets the adverb frequency the user chooses
     * @param advFreq takes in the adjective frequency retrieved from getAdvFreq()
     */
    public void setAdvFreq(int advFreq) {
        this.advFreq = advFreq;
    }

    /**
     * Gets the preposition frequency the user chooses to use from the App class
     * @return the preposition frequency chosen by the user
     */
    public int getPreFreq() {
        return preFreq;
    }

    /**
     * sets the preposition frequency the user chooses
     * @param preFreq takes in the preposition frequency retrieved from getPreFreq()
     */
    public void setPreFreq(int preFreq) {
        this.preFreq = preFreq;
    }
    // Helper function to get a random word of a specified type

    /**
     * Selects a random word from the word map, given its category
     * @param type takes in the type of word (noun, verb, adverb, etc.)
     * @return a random word of a specified type
     */
    private String getRandomWord(String type) {
        List<String> words = getWordMap().get(type);
        if (words != null && !words.isEmpty()) {
            return words.get(random.nextInt(words.size()));
        }
        return "";
    }

    /**
     * The method responsible for generating the sentence; generates one sentence at a time
     * @return a complete sentence that takes into account the frequency if certain words
     */
    public String generateSentence(){
        StringBuilder sentence = new StringBuilder();

        if (random.nextInt(10) < getAdjFreq() && getWordMap().containsKey("adj")) {
            String adjective = getRandomWord("adj");
            sentence.append("The ").append(adjective).append(" ");
        } else if (getWordMap().containsKey("n")) { // If there is no adjective still start with "the" before a noun
            sentence.append("The ");
        }

        // Add noun
        if (getWordMap().containsKey("n")) {
            String noun = getRandomWord("n");
            sentence.append(noun).append(" ");
        }

        // Add verb
        if (getWordMap().containsKey("v")) {
            String verb = getRandomWord("v");
            sentence.append(verb).append(" ");
        }

        // Add adverb based on frequency
        if (random.nextInt(10) < getAdvFreq() && getWordMap().containsKey("adv")) {
            String adverb = getRandomWord("adv");
            sentence.append(adverb).append(" ");
        }

        // Add preposition + "the" + noun, if using a preposition
        if (random.nextInt(10) < getPreFreq() && getWordMap().containsKey("prep") && getWordMap().containsKey("n")) {
            String preposition = getRandomWord("prep");
            String nounInPrep = getRandomWord("n");
            sentence.append(preposition).append(" the ").append(nounInPrep);
        }

        // Capitalize the first letter and add a period at the end
        String output = sentence.toString().trim();
        return output.substring(0, 1).toUpperCase() + output.substring(1) + ".";
        }

}
