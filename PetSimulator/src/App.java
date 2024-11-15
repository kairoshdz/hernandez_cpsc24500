import java.util.Scanner;
import java.util.ArrayList;

public class App {
    public static int hours;
    public static String dogName;
    public static String catName;
    public static String fishName;
    public static void main(String[] args) {
        printBanner();
        // collect names of pets
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the dog's name: ");
        dogName = scan.nextLine();
        Dog dog = new Dog();
        dog.setName(dogName);
        dog.initializeCutOffs();

        System.out.println("Enter the cat's name: ");
        catName = scan.nextLine();
        Cat cat = new Cat();
        cat.setName(catName);
        cat.initializeCutOffs();

        System.out.println("Enter the fish's name: ");
        fishName = scan.nextLine();
        Fish fish = new Fish();
        fish.setName(fishName);
        fish.initializeCutOffs();

        ArrayList<Pet> pets = new ArrayList<>();
        pets.add(dog);
        pets.add(cat);
        pets.add(fish);

        System.out.println("Now, let's simulate.");
        System.out.println("How many hours of their lives would you like to play out?: ");
        hours = scan.nextInt();
        while (hours != 0){
            ArrayList<ArrayList<String>> allActivities = new ArrayList<>();
            for (int i = 0; i < hours; i++) {
                ArrayList<String> hourActivities = new ArrayList<>();
                for (Pet pet : pets) {
                    // Simulate 1 hour for each pet
                    ArrayList<String> petActivities = pet.simulate(1);
                    hourActivities.addAll(petActivities);
                }
                allActivities.add(hourActivities);
            }

            System.out.println("Here's what the rascals did...");
            ActivityPrinter.printActivities(allActivities);
            System.out.println("\nHow many hours of their lives would you like to play out?");
            hours = scan.nextInt();
        }
        System.out.println("Thank you for using Pet Simulator.");

    }

    private static void printBanner(){
        System.out.println("""
                ******************************************************************************
                Welcome to Pet Simulator. This tool simulates the activities of three kinds of
                pets: dogs, cats, and fish. Each pet may sleep, eat, and seek your attention
                for some part of the day. And each pet will do things specific to what kind of
                pet they are.
                ******************************************************************************
                """);
    }

}

