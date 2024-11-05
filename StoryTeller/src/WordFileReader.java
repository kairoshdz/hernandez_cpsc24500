import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * WordFileReader is responsible for reading the file containing the word list as well as organizing
 * it into a linked hashmap that links words to their types.
 */
public class WordFileReader {
    // Reusing most of a function from my previous assignment, TextAnalyzer

    /**
     * This method reads a file containing a list of words and their types and organizes them into a
     * linked hashmap
     * @param file takes the file the user has specified
     * @return returns a linked hashmap organized by word type
     * @throws IOException in the case the file can not be found, is unreadable or contains unexpected data
     */
    public static LinkedHashMap<String, List<String>> readFile(File file) throws IOException {
        LinkedHashMap<String, List<String>> map = new LinkedHashMap<>();
        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            // Splits words by spaces as they are in the wordlist.txt file
            String[] parts = line.split(" ");

            String word = parts[0];
            String type = parts[1];

            // Initialize the list for the type or add to the list of the type (adj, n, v, prep, adv)
            map.putIfAbsent(type, new ArrayList<>());
            // gets the word type, and adds the word to the type's list.
            map.get(type).add(word);
        }

        fileScanner.close();
        return map;
    }
}