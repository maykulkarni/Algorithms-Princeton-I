import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Randomized implementation using circular array.
 * Supports constant amortized time worst case
 * queue operations.
 * <p>
 * Optimized Iterator does not use linear memory
 * while iterating over elements
 *
 * @author Mayur Kulkarni <mayurkulkarni012@gmail.com>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] data;            // Array for storing data
    private int numberOfElements;   // current number of elements
    private int capacity;           // capacity of the queue

    public RandomizedQueue() {
        numberOfElements = 0;
        capacity = 10;
        data = (Item[]) new Object[capacity];
    }

    /**
     * Test.
     *
     * @param args arg
     */
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        System.out.println(rq.sample());
    }

    /**
     * Check if the queue is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    /**
     * Size of the current queue.
     *
     * @return size in integer
     */
    public int size() {
        return numberOfElements;
    }

    /**
     * Uses Fisher-Yates algorithm to
     * generate random next() object.
     * Does not use extra space.
     *
     * @return iterator
     */
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            @Override
            public boolean hasNext() {
                return numberOfElements > 0;
            }

            @Override
            public Item next() {
                if (numberOfElements == 0) throw new NoSuchElementException();
                int index = StdRandom.uniform(numberOfElements);
                Item temp = data[index];
                data[index] = data[--numberOfElements];
                data[numberOfElements] = null;
                return temp;
            }
        };
    }

    /**
     * Always keeps the array from being nearly full
     * or nearly empty.
     * Resize policy:
     * 1. If array is full, resize to capacity * 2.
     * 2. If less than 25% full, then resize to capacity/2.
     *
     * @param newCapacity new capacity
     */
    private void ensureCapacity(int newCapacity) {
        this.capacity = newCapacity;
        Item[] newData = (Item[]) new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, numberOfElements);
        data = newData;
    }

    /**
     * Adds element to the queue.
     *
     * @param item item to add
     */
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (numberOfElements == capacity - 1) {
            ensureCapacity(capacity << 1);
        }
        data[numberOfElements++] = item;
    }

    /**
     * Removes random element in the queue
     *
     * @return random element from queue.
     * Works in O(1) time by replacing the
     * current random element, which is to be
     * removed, by the last element in the
     * array, in turn decreasing the array size.
     */
    public Item dequeue() {
        if (numberOfElements < capacity >> 2) {
            ensureCapacity(capacity >> 1);
        }
        Item toReturn = data[--numberOfElements];
        data[numberOfElements] = null;
        return toReturn;
    }

    /**
     * Return a random element from the
     * array without dequeuing it.
     *
     * @return random element from array
     */
    public Item sample() {
        if (numberOfElements == 0) {
            throw new NoSuchElementException();
        }
        return data[StdRandom.uniform(numberOfElements)];
    }
}
