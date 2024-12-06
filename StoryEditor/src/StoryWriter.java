import java.io.*;

/**
 * The StoryWriter class is responsible for saving the file created within the GUI
 */
public class StoryWriter {
    /**
     * The contents of the text area in the GUI will be saved to a file specified by the user
     * by clicking the File --> Save.
     * @param content the contents of the text area
     * @param filename the filename chosen by the user
     * @return true if successful, false otherwise
     */
    public static boolean saveStory(String content, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
            return true;
        } catch (IOException e) {
            //e.printStackTrace();
            return false;
        }
    }
}