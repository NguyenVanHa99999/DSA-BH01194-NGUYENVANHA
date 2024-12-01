interface Queue {
    void enqueue(int value);
    int dequeue();
    boolean isEmpty();
}

class ArrayQueue implements Queue {
    private int[] elements;
    private int front, rear, size;

    public ArrayQueue(int capacity) {
        elements = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    @Override
    public void enqueue(int value) {
        if (size == elements.length) {
            throw new IllegalStateException("Queue is full");
        }
        rear = (rear + 1) % elements.length;
        elements[rear] = value;
        size++;
    }

    @Override
    public int dequeue() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty");
        }
        int value = elements[front];
        front = (front + 1) % elements.length;
        size--;
        return value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}

public class QueuelExampe {
    public static void main(String[] args) {
        Queue queue = new ArrayQueue(5);

        // Enqueue elements
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        // Dequeue elements
        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("Dequeued: " + queue.dequeue());

        // Check if queue is empty
        System.out.println("Is queue empty? " + queue.isEmpty());
    }
}
