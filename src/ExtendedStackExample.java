class Stack {
    private int[] elements;
    private int top;

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

    // Getter for `top` to be used in the child class
    protected int getTop() {
        return top;
    }
}

class LimitedStack extends Stack {
    private int limit;

    public LimitedStack(int size, int limit) {
        super(size);
        this.limit = limit;
    }

    @Override
    public void push(int value) {
        if (getTop() < limit - 1) { // Use the getter to check the value of `top`
            super.push(value);
        } else {
            throw new StackOverflowError("Exceeded limit");
        }
    }
}

public class ExtendedStackExample {
    public static void main(String[] args) {
        // Create a limited stack with a limit of 3
        LimitedStack limitedStack = new LimitedStack(10, 3);

        // Push elements within the limit
        limitedStack.push(10);
        limitedStack.push(20);
        limitedStack.push(30);

        // Try to push beyond the limit
        try {
            limitedStack.push(40);
        } catch (StackOverflowError e) {
            System.out.println(e.getMessage());
        }

        // Pop elements
        System.out.println("Popped: " + limitedStack.pop());
        System.out.println("Popped: " + limitedStack.pop());

        // Check if the stack is empty
        System.out.println("Is stack empty? " + limitedStack.isEmpty());
    }
}
