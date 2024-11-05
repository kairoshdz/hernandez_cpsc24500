import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
/**
 * The App class brings together the other two classes and takes user input in order
 * to write stories with user input
 */
public class App {
    /**
     * takes in user input and calls other classes to create stories.
     */
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        // initialize author class
        Author author = new Author();

        System.out.println("""
              Welcome to StoryTeller, a sophisticated electronic
              author. This program reads from a list of words of
              various parts of speech and creates a story from
              them. You can tune the richness of the writing by
              changing how frequently adjectives, adverbs, and
              prepositions should be included.
              \nEnter the name of the file containing the word list:""");
        String wordListFile = scan.nextLine();
        LinkedHashMap<String, List<String>> wordMap = WordFileReader.readFile(new File(wordListFile));

        /* these lines were used to check if the words were categorized correctly
        for (String type : wordMap.keySet()) {
            System.out.println(type + ": " + wordMap.get(type));
        }*/
        char writeStory = 'y';
        while (writeStory == 'y'){
            System.out.println("How many sentences would you like in your story?: ");
            int numSentences = scan.nextInt();
            System.out.println("""
               On a scale of 0 to 10...
               How frequently should adjectives be used?:""");
            int adjFreq = scan.nextInt();
            System.out.println("How frequently should adverbs be used?: ");
            int advFreq = scan.nextInt();
            System.out.println("How frequently should prepositions be used?: ");
            int preFreq = scan.nextInt();

            author = new Author(wordMap);
            author.setWordMap(wordMap);
            // Set frequency of certain types of words
            author.setAdjFreq(adjFreq);
            author.setAdvFreq(advFreq);
            author.setPreFreq(preFreq);

            System.out.println("Here it is:\n");

            // generate sentences using the Author class
            for (int i = 0; i < numSentences; i++) {
                System.out.println(author.generateSentence());
            }

            System.out.println("Would you like another story (y or n)?: ");
            writeStory = scan.next().charAt(0);
        }
        System.out.println("Thank you for using StoryTeller!");


    }


}
