import java.io.*;
import java.util.*;
public class TextAnalyzer {
    /**
     * The main method sets the framework for the program, taking user input and calling
     * my other methods
     * @param args
     */
    public static void main(String[] args) {
        PrintBanner();
        Scanner scan = new Scanner(System.in);
        String fileTBR;
        boolean validFile = false;
        while (!validFile) {
            try {
                System.out.print("\nEnter the name of your file: ");
                fileTBR = scan.nextLine();
                File file = new File(fileTBR);
                LinkedHashMap<String, Integer> map = FileReader(file);
                PrintStats(map);
                validFile = true;


            } catch (Exception e) {
                System.out.println("Invalid file name. Please try again.");
                // Loop again if no valid file is given:
            }
            System.out.println("Thank you for using Text Analyzer.");
        }

    }

    /**
     * Prints a simple banner at the start of the program.
     */
    public static void PrintBanner(){
        System.out.println("******************************************************");
        System.out.println("**             Welcome to Text Analyzer             **");
        System.out.println("******************************************************\n");
    }

    /**
     * This method sets the map for the program, going through it and looking for unique words and counting them.
     * A word is defined as a set of characters (or one) and is split but non-word punctuation such as spaces and
     * punctuation, however, I did allow for single ' to be read so that compound words are counted.
     * @param file takes in the file that the user wishes to inspect, given that it is a valid file.
     * @return The map of unique words and their counts
     * @throws IOException
     */
    public static LinkedHashMap<String, Integer> FileReader(File file) throws IOException {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            // splits words by punctuation and spaces, but this escape code allows for compound words
            String[] words = line.toLowerCase().split("[^\\w'-]+");

            for (String word : words) {
                if (!word.isEmpty()) {
                    // creates pairs (word, count) and updates them accordingly, otherwise if the word
                    // doesn't occur again, it returns 0 and keeps the current count
                    map.put(word, map.getOrDefault(word, 0) + 1);
                }
            }
        }
        fileScanner.close();
        return map;
    }

    /**
     * This method sorts words by alphabetical order
     * @param map is the map of words and their counts created earlier in the program.
     * @return returns word map sorted alphabetically
     */
    public static ArrayList<String> WordSort(LinkedHashMap<String, Integer> map) {
        ArrayList<String> sortedWords = new ArrayList<>(map.keySet());
        // sort words alphabetically
        Collections.sort(sortedWords);
        return sortedWords;
}

    /**
     * This method counts the total words, calculates the average word length and prints the map of sorted words.
     * @param map takes the linked hashmap of words and their counts created earlier in the program
     */
    public static void PrintStats(LinkedHashMap<String, Integer> map){
    int totalCount = 0;
    int totalLength = 0;

    ArrayList<String> wordSort = WordSort(map);

    // updates counts for individual word counts, total word counts and total word length
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
        String word = entry.getKey();
        int count = entry.getValue();
        totalCount += count;
        totalLength += word.length() * count;
    }
    // calculates the average length of the words in a file
    double avgLength = (double) totalLength / totalCount;

    System.out.printf("\nTotal number of words: %d \nTotal number of unique words: " +
            "%d \nAverage Length: %.2f \n", totalCount, map.size(), avgLength);
    System.out.println("\nWord list and count: ");
    for (String word : wordSort) {
        System.out.println(word + ": " + map.get(word));
    }
    }

}
