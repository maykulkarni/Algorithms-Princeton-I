/**
 * @author Mayur Kulkarni <mayurkulkarni012@gmail.com>
 */
public class MinStack<T extends Number> extends ArrayStack<T> {
    private T minValue;

    public static void main(String[] args) {
        MinStack<Integer> minStack = new MinStack<>();
        minStack.push(1);
        System.out.println(minStack.getMinValue());
        minStack.push(4);
        System.out.println(minStack.getMinValue());
        minStack.push(-1);
        System.out.println(minStack.getMinValue());
    }

    @Override
    public T pop() {
        T popped = super.pop();
        if (popped.doubleValue() < minValue.doubleValue()) {
            T oldMinValue = minValue;
            minValue = (T) (Double) (2 * oldMinValue.doubleValue() - popped.doubleValue());
            return oldMinValue;
        } else {
            return popped;
        }
    }

    @Override
    public void push(T item) {
        if (isEmpty()) minValue = item;
        if (item.doubleValue() < minValue.doubleValue()) {
            T toPush = (T) (Double) (2 * item.doubleValue() - minValue.doubleValue());
            minValue = item;
            super.push(toPush);
        } else {
            super.push(item);
        }
    }

    public T getMinValue() {
        return minValue;
    }
}