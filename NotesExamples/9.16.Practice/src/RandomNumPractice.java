import java.util.Random;
public class RandomNumPractice {
    public static void main(String[] args) throws Exception {
        Random rnd = new Random();
        int bound = 25;
        int num = rnd.nextInt(bound);
        System.out.printf("Random number generated, (0 - %d): %d\n",bound - 1,num);

    }
}