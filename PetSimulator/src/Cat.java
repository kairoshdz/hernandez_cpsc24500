import java.util.ArrayList;
import java.util.Random;

public class Cat extends Pet {
    private int needScratch;
    @Override
    public String getType() {
        return "cat";
    }
    @Override

    public void initializeCutOffs() {
        this.needFood = 6;
        this.needSleep = 8;
        this.needAttention = 2;
        this.needScratch = 4;
    }
    @Override
    public ArrayList<String> act(){
        ArrayList<String> actions = new ArrayList<>();
        Random brain = new Random();
        int foodDesire = brain.nextInt(10) + 1;
        int sleepDesire = brain.nextInt(10) + 1;
        int attentionDesire = brain.nextInt(10) + 1;
        int scratchDesire = brain.nextInt(10) + 1;

        if (foodDesire <= needFood) {
            actions.add("ate");
        }
        if (sleepDesire <= needSleep) {
            actions.add("slept");
        }
        if (attentionDesire <= needAttention) {
            actions.add("sought your attention");
        }
        if (scratchDesire <= needScratch) {
            actions.add("scratched the kitty post");
        }
        return actions;

    }
}
