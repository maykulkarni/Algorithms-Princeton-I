import edu.princeton.cs.algs4.StdIn;

/**
 * Randomly return k objects from the array
 * supplied by user.
 */
public class Permutation {

    /**
     * Test.
     *
     * @param args command args
     */
    public static void main(String[] args) {
        RandomizedQueue<String> randQueue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            randQueue.enqueue(StdIn.readString());
        }
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            System.out.println(randQueue.dequeue());
        }
    }
}
