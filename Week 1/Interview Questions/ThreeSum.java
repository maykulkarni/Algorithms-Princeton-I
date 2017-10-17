import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mayur Kulkarni <mayurkulkarni012@gmail.com>
 */
public class ThreeSum {
    /**
     * Iterate over every pair of elements and check if -(i + j) exists in the
     * HashSet.
     * O(N2) to iterate, O(1) to search
     *
     * @param array input array
     */
    private static void listThreeSums(int[] array) {
        Set<Integer> arraySet = new HashSet<>(new ArrayList<Integer>() {{
            for (int i : array) add(i);
        }});
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (arraySet.contains(-(array[i] + array[j]))) {
                    System.out.println(array[i] + " " + array[j] + " " + -(array[i] + array[j]));
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {-10, 5, 5, 4, -12, -29, 122};
        listThreeSums(array);
    }
}