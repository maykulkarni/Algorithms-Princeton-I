/**
 * Performs Bitonic Search in 2*ln(N) comparisons
 *
 * @author Mayur Kulkarni <mayurkulkarni012@gmail.com>
 */
public class BitonicArraySearch {
    private static boolean bitonicSearch(int[] array, int key) {
        int mid = array.length / 2;
        // middle element is smaller than the key, and max
        // is on the left, key is definitely on the left
        if (array[mid] < key && array[mid - 1] > array[mid]) {
            return binarySearch(array, 0, mid - 1, key);
        }
        // middle element is smaller than the key, and max
        // is on the right, key is definitely on the right
        else if (array[mid] < key && array[mid + 1] > array[mid]) {
            return binarySearch(array, mid + 1, array.length - 1, key);
        }
        // middle element is bigger than the key, then the key
        // can be anywhere, so binary search both sides
        else {
            return binarySearch(array, 0, mid - 1, key)
                    || binarySearch(array, mid + 1, array.length - 1, key);
        }

    }

    private static boolean binarySearch(int[] array, int low, int high, int key) {
        int mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (array[mid] < key) low = mid + 1;
            else if (array[mid] > key) high = mid - 1;
            else if (array[mid] == key) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] array = {-1, 0, 1, 2, 3, 4, 5, 3, 1, -3, -4};
        System.out.println(bitonicSearch(array, 30));
    }
}