import java.util.Arrays;
import java.util.Random;

public class Selection {

    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exchange(a, i, min);
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

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            testSort();
        }
    }
}
