import java.util.Arrays;
import java.util.Random;

/**
 * @author Mayur Kulkarni <mayurkulkarni012@gmail.com>
 */
public class Inversions {
    public static int sort(Comparable[] a, Comparable[] aux, int low, int high) {
        if (low >= high) return 0;
        int inversions = 0;
        int mid = low + (high - low) / 2;
        inversions += sort(a, aux, low, mid);
        inversions += sort(a, aux, mid + 1, high);
        if (less(a[mid], a[mid + 1])) return 0;
        return inversions + countInversions(a, aux, low, mid, high);
    }

    public static int countInversions(Comparable[] a, Comparable[] aux, int low, int mid, int high) {
        assert isSorted(a, low, mid);
        assert isSorted(a, mid + 1, high);
        System.arraycopy(a, low, aux, low, high - low + 1);
        int i = low;
        int j = mid + 1;
        int inversions = 0;
        for (int k = low; k <= high; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > high) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
                inversions += mid - k + 1;
            } else a[k] = aux[i++];

        }
        return inversions;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static boolean isSorted(Comparable[] a, int low, int mid) {
        for (int i = 0; i < a.length - 1; i++) {
            if (less(a[i + 1], a[i])) return false;
        }
        return true;
    }

    public static int sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        return sort(a, aux, 0, a.length - 1);
    }

    private static void testSort() {
        Random random = new Random();
        int size = 100000;
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
//        Stopwatch stopwatch = new Stopwatch();
//        for (int i = 0; i < 20; i++) {
//            testSort();
//        }
//        System.out.println(stopwatch.elapsedTime());
        Integer[] a = {5, 4, 3, 2, 1};
        System.out.println(sort(a));
    }
}