import java.util.Scanner;
import java.util.Random;
class FortuneTeller {
    public static void main(String[] args) {
        System.out.println("*************************************");
        System.out.println("            FORTUNE TELLER           ");
        System.out.println("*************************************");

        // initialize scanner and rng
        Scanner scan = new Scanner(System.in);
        Random rnd = new Random();
        // get lucky number
        int luckyNumber = rnd.nextInt(10 ) + 1;

        // get user input
        System.out.println("Enter your full name:");
         String name = scan.nextLine();
        System.out.println("How old are you? ");
        int age = scan.nextInt();
        // collect enter so that color can be read
        scan.nextLine();
        System.out.println("What is your favorite color? ");
        String color = scan.nextLine();

        // get percentage of age
        double percentage = ((double) luckyNumber / age) * 100 ;

        // print results
        System.out.printf("\nWelcome, %s.\n", name);
        System.out.printf("I see you are %d years old.\n", age);
        System.out.printf("Your favorite color is %s\n", color);
        System.out.printf("Your lucky number is %d\n", luckyNumber);
        System.out.printf("Your lucky number is %.3f%% of your age.\n", percentage);
        System.out.println("\nThank you for using Fortune Teller.");

    }
}
