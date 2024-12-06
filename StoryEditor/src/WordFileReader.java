import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * WordFileReader is responsible for reading the file containing the word list as well as organizing
 * it into a linked hashmap that links words to their types.
 * I literally copied and pasted this entire class from the StoryTeller project from about a month
 * ago and changed all instances of List to ArrayList instead
 */
public class WordFileReader {


    /**
     * This method reads a file containing an Arraylist of words and their types and organizes them into a
     * linked hashmap
     * @param file takes the file the user has specified
     * @return returns a linked hashmap organized by word type
     * @throws IOException in the case the file can not be found, is unreadable or contains unexpected data
     */
    public static LinkedHashMap<String, ArrayList<String>> readFile(File file) throws IOException {
        LinkedHashMap<String, ArrayList<String>> map = new LinkedHashMap<>();
        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            // Splits words by spaces as they are in the wordlist.txt file
            String[] parts = line.split("\t");

            String word = parts[0];
            String type = parts[1];

            // Initialize the list for the type or add to the list of the type (n, v, a)
            map.putIfAbsent(type, new ArrayList<>());
            // gets the word type, and adds the word to the type's list.
            map.get(type).add(word);
        }

        fileScanner.close();
        return map;
    }
}