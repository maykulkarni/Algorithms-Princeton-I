import java.util.Arrays;
import java.util.Random;

public class Shell {

    public static void sort(Comparable[] a) {
        int h = 0;
        while (h * 3 + 1 < a.length) h = h * 3 + 1;

        for (int gap = h; gap > 0; gap /= 3) {
            for (int i = gap; i < a.length; i++) {
                int j = i;
                while (j >= 0 && (j - gap) >= 0 && less(a[j], a[j - gap])) {
                    exchange(a, j, j - gap);
                    j -= gap;
                }
            }
        }
    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void exchange(Comparable a[], int indexA, int indexB) {
        Comparable temp = a[indexA];
        a[indexA] = a[indexB];
        a[indexB] = temp;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            testSort();
        }
    }

    private static void testSort() {
        Random random = new Random();
        int size = random.nextInt(10000);
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
        sort(array);
        for (int i = 1; i < size; i++) {
            if (less(array[i], array[i - 1])) {
                System.out.println(Arrays.toString(array));
                break;
            }
        }
    }
}
