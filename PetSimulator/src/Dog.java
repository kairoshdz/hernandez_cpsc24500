import java.util.ArrayList;
import java.util.Random;

public class Dog extends Pet {
    private int needFetch;

    @Override
    public String getType() {
        return "dog";
    }

    @Override
    public void initializeCutOffs() {
        this.needFood = 1;
        this.needSleep = 7;
        this.needAttention = 9;
        this.needFetch = 6;
    }
    @Override
    public ArrayList<String> act(){
        ArrayList<String> actions = new ArrayList<>();
        Random brain = new Random();
        int foodDesire = brain.nextInt(10) + 1;
        int sleepDesire = brain.nextInt(10) + 1;
        int attentionDesire = brain.nextInt(10) + 1;
        int fetchDesire = brain.nextInt(10) + 1;

       if (foodDesire <= needFood) {
           actions.add("ate");
       }
       if (sleepDesire <= needSleep) {
           actions.add("slept");
       }
       if (attentionDesire <= needAttention) {
           actions.add("sought your attention");
       }
       if (fetchDesire <= needFetch) {
           actions.add("played fetch");
       }
       return actions;

    }
}
