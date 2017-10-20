public class QueueUsingStacks {
    ArrayStack<Integer> inbox;
    ArrayStack<Integer> outbox;

    public QueueUsingStacks() {
        inbox = new ArrayStack<>();
        outbox = new ArrayStack<>();
    }

    public static void main(String[] args) {
        QueueUsingStacks queue = new QueueUsingStacks();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }

    public void enqueue(int item) {
        inbox.push(item);
    }

    public int dequeue() {
        for (int i : inbox) outbox.push(i);
        int popped = outbox.pop();
        for (int i : outbox) inbox.push(i);
        return popped;
    }
}
