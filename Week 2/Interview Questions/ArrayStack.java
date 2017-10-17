import edu.princeton.cs.algs4.Stopwatch;

import java.util.Iterator;
import java.util.Random;

public class ArrayStack<T> implements Iterable<T> {
    T[] data;
    private int N;
    private int capacity;

    public ArrayStack() {
        N = 0;
        capacity = 10;
        data = (T[]) new Object[capacity];
    }

    public ArrayStack(int capacity) {
        N = 0;
        this.capacity = capacity;
        data = (T[]) new Object[capacity];
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        Stopwatch s = new Stopwatch();
        for (int i = 0; i < 1000000; i++) {
            stack.push(new Random().nextInt(100));
        }
        for (int i : stack) i = 10;
        System.out.println(s.elapsedTime());
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return N > 0;
            }

            @Override
            public T next() {
                return pop();
            }
        };
    }

    private void resize(int capacity) {
        this.capacity = capacity;
        T[] newArray = (T[]) new Object[capacity];
        System.arraycopy(data, 0, newArray, 0, N + 1);
        data = newArray;
    }

    public void push(T newItem) {
        if (N == capacity - 1) {
            resize(capacity * 2);
        }
        data[N++] = newItem;
    }

    public void push(T... itemArray) {
        for (T newItem : itemArray) push(newItem);
    }

    public T pop() {
        if (N < capacity / 4) {
            resize(capacity / 2 + 1);
        }
        if (N < 0) throw new IllegalStateException("Stack Empty");
        T toReturn = data[--N];
        data[N] = null;
        return toReturn;
    }
}
