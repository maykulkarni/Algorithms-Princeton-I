import java.util.Arrays;
import java.util.Random;

/**
 * @author Mayur Kulkarni <mayurkulkarni012@gmail.com>
 */
public class GermanFlag {
    /**
     * Get randomly colored array
     *
     * @param length length of the array
     * @return random array
     */
    private static char[] getRandomArray(int length) {
        char[] array = new char[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randInt = random.nextInt(3);
            if (randInt == 0) array[i] = 'R';
            else if (randInt == 1) array[i] = 'W';
            else array[i] = 'B';
        }
        return array;
    }

    /**
     * Swaps two elements in array
     *
     * @param array array to perform swap
     * @param one   first index
     * @param two   second index
     */
    private static void swap(char[] array, int one, int two) {
        char temp = array[one];
        array[one] = array[two];
        array[two] = temp;
    }

    /**
     * Sorts the array and makes at the most N swaps and
     * at the most N color() operation. Since the color
     * is directly stored in array consider an array
     * access as color() operation.
     * <p>
     * Sort order is [Red, White, Blue]
     * <p>
     * Logic is to maintain 3 pointers, red, blue and a
     * pointer to iterate. All elements till red pointer
     * will be red, similarly, all elements after blue
     * pointer will be blue. Iterate a pointer from red + 1
     * till blue - 1. While iteration 3 cases may occur:
     * 1. Red
     * If current is red, swap with redPointer + 1
     * Increment the redPointer
     * 2. White
     * Since White will be in middle, do nothing.
     * 4. Blue
     * If current is blue, swap with bluePointer - 1
     * Decrement the bluePointer
     *
     * @param array array to sort
     */
    private static void sortArray(char[] array) {
        int redPointer = -1;
        int bluePointer = array.length;
        while (redPointer < array.length
                && array[redPointer + 1] == 'R') redPointer++;
        while (bluePointer >= 0
                && array[bluePointer - 1] == 'B') bluePointer--;

        for (int currPointer = redPointer + 1;
             currPointer < bluePointer; currPointer++) {
            if (array[currPointer] == 'W') continue;

            if (array[currPointer] == 'R') {
                swap(array, redPointer + 1, currPointer);
                redPointer++;
            } else if (array[currPointer] == 'B') {
                swap(array, bluePointer - 1, currPointer);
                bluePointer--;
            }
        }
    }

    /**
     * Random testing
     *
     * @param args command args
     */
    public static void main(String[] args) {
        char[] array = getRandomArray(5);
        System.out.println(Arrays.toString(array));
        sortArray(array);
        System.out.println(Arrays.toString(array));
    }
}