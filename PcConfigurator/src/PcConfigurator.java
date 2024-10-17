import java.util.Scanner;
import java.io.*;
public class PcConfigurator {
    /**
     * The main method includes all of the user input and menus. I did take a couple of
     * shortcuts because of time constraints with midterms week.
     * @param args returns menus
     */
    public static void main(String[] args) {
        printHeading();
        Scanner scan = new Scanner(System.in);

        // Set up number of PCs and receipt file
        System.out.print("\nHow many PCs would you like to purchase?: ");
        int numberOfPCs = scan.nextInt();
        scan.nextLine();

        // Credit to Kevin Dacanay for helping me with the file reading code, midterms week is really rough :_(
        String receiptFileName = null;
        while (receiptFileName == null) {
            try {
                System.out.print("\nEnter a name for your receipt file: ");
                receiptFileName = scan.nextLine();
                new PrintWriter(new BufferedWriter(new FileWriter(receiptFileName, true))).close();
            } catch (IOException e) {
                System.out.println("Invalid file name. Please try again.");
                // Loop again if no valid file is given:
                receiptFileName = null;
            }
        }



        // set base price of PC

        int totalPrice = 1750 * numberOfPCs;

        // initialize menu choice
        int choice;
        for (int i = 1; i <= numberOfPCs; i++) {
            // initialize variables for hardware choices
            int processor = 0;
            int graphicsCard = 0;
            int memory = 0;
            int  monitor = 0;
            //reset to menu
            choice = 0;
            while (choice != 5) {
                System.out.println("""
            \nPC #""" + i + """
            \nWhat would you like to upgrade?
            1. Processor
            2. Graphics card
            3. Memory
            4. Monitor
            5. Nothing else - I'm done.""");
                choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Upgrade the processor to [1] Intel i7 ($200), [2] Intel i9 ($300), [3] AMD 9 5950 ($500):");
                        processor = scan.nextInt();
                        choice = 0;
                        break;
                    case 2:
                        System.out.println("Update the graphics card to [1] NVidia 3060 ($150), [2] NVidia 4060 ($250), [3] NVidia 4080 ($350)");
                        graphicsCard = scan.nextInt();
                        choice = 0;
                        break;
                    case 3:
                        System.out.println("Increase the memory to [1] 16GB ($150), [2] 32GB ($250)");
                        memory = scan.nextInt();
                        choice = 0;
                        break;
                    case 4:
                        System.out.println("Add a monitor of size [1] 24 inches ($200), [2] 27 inches ($250), [3] 32 inches ($350):");
                        monitor = scan.nextInt();
                        choice = 0;
                        break;
                }
            }
            int upgradePrice = calculatePrices(processor, graphicsCard, memory, monitor);
            totalPrice += upgradePrice;
            String appendToReceipt = ReceiptFormat(i, processor, graphicsCard, memory, monitor, upgradePrice);
            WriteToFile(receiptFileName, appendToReceipt);
        }

        System.out.printf("""
                \nThe total cost of your order of %d PCs is $%d.00
                Your order has been saved to %s
                Thank you for shopping with us.
                """, numberOfPCs, totalPrice, receiptFileName);
        String appendEnding = String.format("""
                \nThe total cost of your order of %d PCs is $%d.00
                \nThank you for shopping with us.
                """, numberOfPCs,totalPrice);
        WriteToFile(receiptFileName, appendEnding);

    }

    /**
     * Returns a simple heading to the start of the program
     */
    public static void printHeading() {
        System.out.println("******************************************************");
        System.out.println("**          Welcome to the PC Configurator          **");
        System.out.println("******************************************************\n");
    }

    /**
     * Returns the price of an upgrade. The result from the calculatePrices method is added
     * on to the base price of $1750
     * @param processor collects user's processor choice
     * @param graphicsCard collects user's graphics card choice
     * @param memory collects user's memory choice
     * @param monitor collects user's monitor choice
     * @return returns the cost of the user's chosen upgrades
     */
    public static int calculatePrices(int processor, int graphicsCard, int memory, int monitor ) {
        int upgradeCost = 0;
        switch (processor) {
            case 1:
                upgradeCost += 200;
                break;
            case 2:
                upgradeCost += 300;
                break;
            case 3:
                upgradeCost += 500;
                break;
        }
        switch (graphicsCard) {
            case 1:
                upgradeCost += 150;
                break;
            case 2:
                upgradeCost += 250;
                break;
            case 3:
                upgradeCost += 350;
                break;
        }
        switch (memory) {
            case 1:
                upgradeCost += 150;
                break;
            case 2:
                upgradeCost += 250;
                break;
        }

        switch (monitor) {
            case 1:
                upgradeCost += 200;
                break;
            case 2:
                upgradeCost += 250;
                break;
            case 3:
                upgradeCost += 350;
        }
        return upgradeCost;
    }

    /**
     * The ReceiptFormat method essentially builds a string that will be appended to the receipt.
     * It shows the user's hardware choices and the total price of their PC
     * @param pcNumber collects the PC number that the user customized
     * @param processor collects processor choice
     * @param graphicsCard collects GC choice
     * @param memory collects memory choice
     * @param monitor collects monitor choice
     * @param indivPrice collects the price for an individual PC
     * @return returns a string that will be appended to the receipt file
     */
    public static String ReceiptFormat(int pcNumber, int processor, int graphicsCard, int memory, int monitor, int indivPrice){
        String[] processors = {"Stock Processor","Intel i7 (+$200)", "Intel i9 (+$300)", "AMD 9 5950 (+$500)"};
        String[] graphicsCards = {"Stock Graphics Card","NVidia 3060 (+$150)", "NVidia 4060 (+$250)", "NVidia 4080 (+$350)"};
        String[] memoryChoices = {"Stock Memory","16GB (+$150)",  "32GB (+$250)"};
        String[] monitors = {"Stock Monitor","24 inches (+$200)", "27 inches (+$250)","32 inches (+$350)"};

        // I decided not to use string format for this just because this helped me visualize it a little better
        String receipt = "\nPC #" + pcNumber + ": $" +(indivPrice + 1750) +

                ".00\nProcessor: " + processors[processor] +

                "\nGraphics Card: " + graphicsCards[graphicsCard] +

                "\nMemory: " + memoryChoices[memory] +

                "\nMonitor: " + monitors[monitor];

        return receipt;
    }

    /**
     * Writes the string created in the ReceiptFormat method to the user's file
     * @param receiptFileName collects the name of the file the user selects.
     * @param content collects the string created in ReceiptFormat method
     */
    public static void WriteToFile(String receiptFileName, String content) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(receiptFileName, true)))) {
            out.println(content);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }

}

