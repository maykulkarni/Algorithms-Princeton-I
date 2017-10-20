import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Deque implementation using circular array.
 * Supports constant amortized time worst case
 * enqueue and dequeue operations.
 *
 * @param <Item> Generic
 * @author Mayur Kulkarni <mayurkulkarni012@gmail.com>
 */
public class Deque<Item> implements Iterable<Item> {
    private Item[] data;        // Array for storing data
    private int numerOfElements;// current number of elements
    private int capacity;       // capacity of the queue
    private int front;          // front pointer addFirst, removeFirst
    private int rear;           // rear pointer addLast, removeLast

    public Deque() {
        numerOfElements = 0;
        capacity = 10;      // Initial capacity is 10
        front = 0;
        rear = 1;
        data = (Item[]) new Object[capacity];
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        for (int i : deque) System.out.println(i);
    }

    /**
     * Check if the queue is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return numerOfElements == 0;
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
        Item[] newData = (Item[]) new Object[newCapacity];
        front = (front + 1) % capacity;
        for (int i = 0; i < numerOfElements; i++, front = (front + 1) % capacity) {
            newData[i] = data[front];
        }
        front = newCapacity - 1;
        rear = numerOfElements;
        data = newData;
        this.capacity = newCapacity;
    }

    /**
     * Iterator for traversing from front end to rear end.
     *
     * @return iterator
     */
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            int tempFront = front;

            @Override
            public boolean hasNext() {
                return (tempFront + 1) % capacity != rear;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public Item next() {
                if ((tempFront + 1) % capacity == rear) {
                    throw new NoSuchElementException();
                }
                tempFront = (tempFront + 1) % capacity;
                return data[tempFront];
            }
        };
    }

    /**
     * Size of the current queue.
     *
     * @return size in integer
     */
    public int size() {
        return numerOfElements;
    }

    /**
     * Add to the front of the queue.
     * After addition the front pointer <b>decrements</b>
     * and points to the next free location
     *
     * @param item item to add
     */
    public void addFirst(Item item) {
        if (numerOfElements == capacity - 1) {
            // queue full
            ensureCapacity(capacity * 2 + 1);
        }
        if (item == null) {
            throw new IllegalArgumentException();
        }
        data[front] = item;
        front = (front - 1 + capacity) % capacity;
        numerOfElements++;
    }

    /**
     * Add to the end of the queue.
     * After adding the rear pointer <b>increments</b>
     * and points to the next free location
     *
     * @param item item to add
     */
    public void addLast(Item item) {
        if (numerOfElements == capacity - 1) {
            ensureCapacity(capacity * 2 + 1);
        }
        if (item == null) {
            throw new IllegalArgumentException();
        }
        data[rear] = item;
        rear = (rear + 1) % capacity;
        numerOfElements++;
    }

    /**
     * Removes from the front of the queue
     * After deletion the front pointer increments,
     * deletes the current element and waits for
     * new element to be added.
     */
    public Item removeFirst() {
        if (numerOfElements == 0) {
            throw new NoSuchElementException();
        }
        if (numerOfElements == capacity / 4) {
            ensureCapacity(capacity / 2);
        }
        front = (front + 1) % capacity;
        Item toReturn = data[front];
        data[front] = null;
        numerOfElements--;
        return toReturn;
    }

    /**
     * Removes from the end of the queue
     * After deletion the rear pointer decrements,
     * deletes the current element and waits for
     * new element to be added.
     */
    public Item removeLast() {
        if (numerOfElements == 0) {
            throw new NoSuchElementException();
        }
        if (numerOfElements == capacity / 4) {
            ensureCapacity(capacity / 2);
        }
        rear = (rear - 1 + capacity) % capacity;
        Item toReturn = data[rear];
        data[rear] = null;
        numerOfElements--;
        return toReturn;
    }
}
