// used chatGPT to figure out what libraries I would need to import
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * The StoryComposer class contains the GUI and the complete functionality of the program's buttons. Calls methods from
 * other classes to create ArrayList, choose random words and save the file
 */
public class StoryComposer {
    // note for future reference: 'JXyz' classes come from the swing library
    private JFrame frame;
    private JTextArea textArea;
    private JTextField inputField;
    private LinkedHashMap<String, ArrayList<String>> wordMap;

    /**
     * This constructor creates a StoryComposer instance and initializes the GUI
     */
    public StoryComposer() {
        initializeGUI();
    }

    /**
     * This method creates the UI for the program and adds functionality to the buttons and Menus
     * Used GFG tutorials to guide me through the creation of the GUI
     * <a href="https://www.geeksforgeeks.org/introduction-to-java-swing/">...</a>
     * <a href="https://www.geeksforgeeks.org/javafx-menubar-and-menu/">...</a>
     * <a href="https://www.geeksforgeeks.org/java-awt-menuitem-menu/">...</a>
     */
    private void initializeGUI() {
        frame = new JFrame("Story Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        //create menu bar with 'File' 'Help', add submenu to File
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem clearItem = new JMenuItem("Clear");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(clearItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);
        menuBar.add(helpMenu);
        frame.setJMenuBar(menuBar);
        // setting the text area properties, text wraps, adds a scroller for longer texts
        textArea = new JTextArea();
        textArea.setWrapStyleWord(true); //if a word doesn't fit, put on the next line instead of broken
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // creating an input panel and add button at the bottom of the screen for the user to input any word
        // also creates a label to the left of the input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        JLabel inputLabel = new JLabel("Enter Word:");
        inputField = new JTextField();
        JButton addButton = new JButton("Add");
        addButton.addActionListener(_ -> {
            String text = inputField.getText().trim();
            if (!text.isEmpty()) {
                textArea.append(text + " ");
                inputField.setText("");
            }
        });
        // Add label, text field, and button to the input panel
        // Left: Label, Center: Text field, Right: Button
        inputPanel.add(inputLabel, BorderLayout.WEST);
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        // create 3 word type buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));
        buttonPanel.setPreferredSize(new Dimension(100, 300)); // Adjust size to fit content

        JButton nounButton = new JButton("Noun");
        JButton verbButton = new JButton("Verb");
        JButton adjButton = new JButton("Adjective");

        nounButton.setPreferredSize(new Dimension(100, 100));
        verbButton.setPreferredSize(new Dimension(100, 100));
        adjButton.setPreferredSize(new Dimension(100, 100));

        nounButton.addActionListener(_ -> addRandomWord("n"));
        verbButton.addActionListener(_ -> addRandomWord("v"));
        adjButton.addActionListener(_ -> addRandomWord("a"));

        buttonPanel.add(nounButton);
        buttonPanel.add(verbButton);
        buttonPanel.add(adjButton);

        // Set the frame layout
        // Center: Scroll pane, Bottom: Input panel, Left: Button panel
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);
        frame.add(buttonPanel, BorderLayout.WEST);

        // Adds functions from other methods in this class to menu buttons
        openItem.addActionListener(_ -> openFile());
        saveItem.addActionListener(_ -> saveFile());
        clearItem.addActionListener(_ -> textArea.setText(""));
        exitItem.addActionListener(_ -> System.exit(0));
        aboutItem.addActionListener(_ -> JOptionPane.showMessageDialog(frame, "Story Editor by Erick Hernandez, December 2024"));

        frame.setVisible(true);
    }

    /**
     * Opens a file with a proper word list and loads it to be used
     * Attempts to read a file through the WordFileReader class and returns a message on the status of opening the file
     */
    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile(); // Create a File object
            try {
                wordMap = WordFileReader.readFile(file); // Pass the File object
                JOptionPane.showMessageDialog(frame, "File loaded successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error loading file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Saves content in text area to a file to a specified location
     * Gives message on the save status of the file
     */
    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            String filepath = fileChooser.getSelectedFile().getAbsolutePath();
            boolean success = StoryWriter.saveStory(textArea.getText(), filepath);
            JOptionPane.showMessageDialog(frame, success ? "File saved." : "Failed to save file.");
        }
    }

    /**
     * Adds random word of specified type by retrieving a word selected by the chooseWord method in the WordChooser
     * class. If a proper word list isn't loaded, an error is given
     * @param type the type of word to add (n, v or a)
     */
    private void addRandomWord(String type) {
        if (wordMap == null || !wordMap.containsKey(type)) {
            JOptionPane.showMessageDialog(frame, "No words loaded for " + type);
            return;
        }
        String word = WordChooser.chooseWord(wordMap.get(type));
        textArea.append(word + " ");
    }

    /**
     * The main method, as the entry point of the app, launches the GUI by creating a StoryComposer instance
     */
    public static void main(String[] args) {
        new StoryComposer();
    }
}