package DataStructure;
// Stack

// Operator:
// PUSH: thêm một phần từ vào stack
// POP: Xoá một phần từ khỏi stack
// PEEK: Lấy ra giá trị của phần từ đầu tiên
// SIZE: Lấy ra kích thước của stack
// ISEMPTY: trả về kết quả stack có rỗng hay không ?

interface IStack {
    public boolean push(int value);

    public int pop();

    public int peek();

    public int size();

    public boolean isEmpty();
}

public class MyStack implements IStack {
    // Nơi lưu trữ dữ liệu (Array - LinkedList)
    private int[] stack;
    // Top là con trỏ trỏ đến phần từ đầu tiên của stack
    private int top;
    private int size;

    // Constructor
    public MyStack(int size) {
        this.size = size;
        stack = new int[size];
        top = -1;
    }

    @Override
    public boolean push(int value) {
        if (isFull()) {
            System.out.println("Stack is full");
            return false;
        } else {
            top++;
            stack[top] = value;
            return true;
        }
    }

    @Override
    public int pop() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int peek() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        // if (top == size) {
        // return true;
        // } else {
        // return false;
        // }
        return top == size;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
        } else {
            for (int i = 0; i <= top; i++) {
                System.out.print(stack[i] + "-");
            }
            System.out.println();
        }
    }
}
