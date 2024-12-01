class Stack {
    private int[] elements;
    protected int top;

    public Stack(int size) {
        elements = new int[size];
        top = -1;
    }

    public void push(int value) {
        if (top < elements.length - 1) {
            elements[++top] = value;
        } else {
            throw new StackOverflowError("Stack is full");
        }
    }

    public int pop() {
        if (top >= 0) {
            return elements[top--];
        } else {
            throw new IllegalStateException("Stack is empty");
        }
    }

    public int peek() {
        if (top >= 0) {
            return elements[top];
        } else {
            throw new IllegalStateException("Stack is empty");
        }
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public static void main(String[] args) {
        Stack stack = new Stack(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Top element: " + stack.peek());
        System.out.println("Pop element: " + stack.pop());
        System.out.println("Pop element: " + stack.pop());
        System.out.println("Stack is empty: " + stack.isEmpty());
    }
}
