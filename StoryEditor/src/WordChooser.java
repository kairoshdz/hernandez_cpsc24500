import java.util.*;

/**
 * Word chooser is responsible for returning a random word of a specified type
 */
public class WordChooser {
    /**
     * returns nothing without an opened word list, returns a random word of any type given an opened word list
     * @param words takes in the ArrayList that was created from WordFileReader from the user's opened file
     * @return a random word of a specific type
     */
    public static String chooseWord(ArrayList<String> words) {
        if (words == null || words.isEmpty()) return null;
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }
}