import java.util.ArrayList;
import java.util.Random;

public class Fish extends Pet {
    private int swimAround;
    @Override
    public String getType() {
        return "fish";
    }
    @Override
    public void initializeCutOffs() {
        this.needFood = 3;
        this.needSleep = 1;
        this.needAttention = 0;
        this.swimAround = 9;
    }
    @Override
    public ArrayList<String> act(){
        ArrayList<String> actions = new ArrayList<>();
        Random brain = new Random();
        int foodDesire = brain.nextInt(10) + 1;
        int sleepDesire = brain.nextInt(10) + 1;
        int attentionDesire = brain.nextInt(10) + 1;
        int swimDesire = brain.nextInt(10) + 1;

        if (foodDesire <= needFood) {
            actions.add("ate");
        }
        if (sleepDesire <= needSleep) {
            actions.add("slept");
        }
        if (attentionDesire <= needAttention) {
            actions.add("sought your attention");
        }
        if (swimDesire <= swimAround) {
            actions.add("swam around its bowl");
        }
        return actions;

    }
}
