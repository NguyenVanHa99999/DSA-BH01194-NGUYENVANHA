// Define the Stack interface
interface IStack {
    void push(int value);
    int pop();
    boolean isEmpty();
}
class ArrayStack implements IStack {
    private int[] elements;
    private int top;

    public ArrayStack(int size) {
        elements = new int[size];
        top = -1;
    }
    @Override
    public void push(int value) {
        if (top < elements.length - 1) {
            elements[++top] = value;
        } else {
            throw new StackOverflowError("Stack is full");
        }
    }
    @Override
    public int pop() {
        if (top >= 0) {
            return elements[top--];
        } else {
            throw new IllegalStateException("Stack is empty");
        }
    }
    @Override
    public boolean isEmpty() {
        return top == -1;
    }
}
class LinkedStack implements IStack {
    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }
    private Node top;
    @Override
    public void push(int value) {
        Node newNode = new Node(value);
        newNode.next = top;
        top = newNode;
    }
    @Override
    public int pop() {
        if (top == null) {
            throw new IllegalStateException("Stack is empty");
        }
        int value = top.value;
        top = top.next;
        return value;
    }
    @Override
    public boolean isEmpty() {
        return top == null;
    }
}

// Main class to test polymorphic behavior
public class PolymorphicStackExample {
    public static void main(String[] args) {
        IStack stack;

        // Using ArrayStack
        stack = new ArrayStack(5);
        stack.push(10);
        stack.push(20);
        System.out.println("ArrayStack Popped: " + stack.pop());

        // Using LinkedStack
        stack = new LinkedStack();
        stack.push(30);
        stack.push(40);
        System.out.println("LinkedStack Popped: " + stack.pop());
    }
}
