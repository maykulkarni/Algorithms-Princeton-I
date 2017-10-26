import java.util.Random;

/**
 * @author Mayur Kulkarni <mayurkulkarni012@gmail.com>
 */
public class ShuffleLinkedList {

    public static Node shuffle(Node list) {
        if (list == null || list.next == null) return list;
        Node middle = getMiddle(list);
        Node secondHalf = middle.next;
        middle.setNext(null);
        list = shuffle(list);
        secondHalf = shuffle(secondHalf);
        return merge(list, secondHalf);
    }

    private static Node merge(Node firstHalf, Node secondHalf) {
        Node dummy = new Node(-1);
        Node answerHead = dummy;
        Random random = new Random();
        while (true) {
            if (firstHalf == null && secondHalf == null) break;
            else if (firstHalf == null) {
                dummy.next = secondHalf;
                dummy = dummy.next;
                secondHalf = secondHalf.next;
            } else if (secondHalf == null) {
                dummy.next = firstHalf;
                dummy = dummy.next;
                firstHalf = firstHalf.next;
            } else if (random.nextInt(2) == 0) {
                dummy.next = firstHalf;
                firstHalf = firstHalf.next;
                dummy = dummy.next;
            } else {
                dummy.next = secondHalf;
                secondHalf = secondHalf.next;
                dummy = dummy.next;
            }
        }
//        printList(answerHead.next);
        return answerHead.next;
    }

    private static Node getMiddle(Node list) {
        Node slow = list;
        Node fast = list;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void printList(Node LinkedList) {
        Node LL = LinkedList;
        while (LL.next != null) {
            System.out.print(LL + " -> ");
            LL = LL.next;
        }
        System.out.println(LL);
    }

    public static void main(String[] args) {
        Node<Integer> linkedList = new Node<>(5);
        linkedList.next = new Node(1);
        linkedList.next.next = new Node(14);
        linkedList.next.next.next = new Node(21);
        linkedList.next.next.next.next = new Node(61);
        System.out.println("Original: ");
        printList(linkedList);
        Node shuffled = shuffle(linkedList);
        System.out.println("Answer: ");
        printList(shuffled);
    }

    static class Node<T> {
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return this.data.toString();
        }
    }
}