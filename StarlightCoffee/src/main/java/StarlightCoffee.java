
import java.util.Scanner;


public class StarlightCoffee {
    public static void main (String[] args) {
        // print heading
        printHeading();
        // initialize scanner
        Scanner scan = new Scanner(System.in);

        // collect information
        System.out.println("Enter a name for your order:");
        String name = scan.nextLine();

        // I organized the coffee by price (least expensive to most to easily calculate cost
        System.out.println("""
                What kind of coffee would you like?
                 1. Espresso
                 2. Americano
                 3. Italiano
                 4. Cappuccino
                 Enter the number of your choice:
                """);
        int coffee = scan.nextInt();

        System.out.println("""
                What size would you like?
                1. Tall
                2. Grande
                3. Venti
                Enter the number of your choice:
                """);
        int size = scan.nextInt();

        System.out.println("How many extra shots of espresso would you like?");
        int shots = scan.nextInt();

        double initCost = calculateDrinkCost(coffee, size, shots);

        System.out.println("Are you a member of Starlight Stars? (y/n)");
        String memberAnswer = scan.next();

        System.out.println("""
                Would you like to leave a tip?
                 1. Good Service - 10%
                 2. Great Service - 20%
                 3. Outstanding Service - 30%
                 4. No Tip""");
        int tipChoice = scan.nextInt();
        double tip = calculateTip(initCost, tipChoice);
        printBill(name, initCost, tip, memberAnswer);
    }

    /**
     * Prints a heading for the program when it starts up
     */
    public static void printHeading() {
        System.out.println("******************************************************");
        System.out.println("**         STARLIGHT COFFEE POINT OF SALE           **");
        System.out.println("******************************************************\n");
    }

    /**
     * Calculates customer's initial drink cost before discounts, tips and taxes
     * @param coffee takes coffee type customer ordered
     * @param size takes size customer ordered
     * @param shots extra espresso shots ordered by customer
     * @return returns initial drink cost
     */
    public static double calculateDrinkCost(int coffee, int size, int shots) {
        // calculating a reasonable price for coffee
        return (((double) coffee * size + (shots * 2)) * 0.65) + 3;

    }

    /**
     * Calculates customer's tip
     * @param initCost takes initial cost to calculate tip
     * @param tipAmount takes user's answer of the tip percentage they wanted to give
     * @return returns calculated tip
     */
    public static double calculateTip(double initCost, int tipAmount) {
        if (tipAmount == 4){
            return 0;
        } else {
            return ((double)tipAmount/10) * initCost;
        }


    }

    /**
     * Prints customer's bill including their name, cost before other charges, tips and taxes
     * @param name uses customer's name
     * @param initCost uses previously calculated initial cost before fees, tips and discounts
     * @param tip uses previously calculated tip
     * @param member checks if customer is Starlight Stars member
     */
    public static void printBill(String name, double initCost, double tip, String member) {
        double discount = 0;
        if (member.equalsIgnoreCase("y")) {
            discount = initCost * 0.1;
        }

        double tax = 0.175 * initCost;
        double total = initCost - discount + tip + tax ;

        System.out.printf("""
                Here is your bill, %s:
                Beverage         $ %.2f
                Club Discount    $ %.2f
                Tip Amount       $ %.2f
                Taxes            $ %.2f
                Total            $ %.2f
                """, name, initCost, discount, tip, tax, total);

    }
}




