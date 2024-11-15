import java.util.ArrayList;
public class ActivityPrinter {
    /**
     * Prints all of a pet's activities for an hour by iterating through lists of the pet's activities.
     * These are formatted by the hour
     * @param activities an array list of a pet's activities for the hour in the format
     * "The <pet type>, <pet name>, <action>."
     */
    public static void printActivities(ArrayList<ArrayList<String>> activities){
        for (int i = 0; i < activities.size(); i++) {
            System.out.println("          *** Hour " + (i+1) + " ***");
            for (String activity : activities.get(i)) {
                System.out.println(activity);
            }
        }

    }
}
