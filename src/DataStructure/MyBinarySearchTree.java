package DataStructure;

class NodeTree {
    int value;
    NodeTree left;
    NodeTree right;

    public NodeTree(int value) {
        this.value = value;
        left = null;
        right = null;
    }
}

public class MyBinarySearchTree {
    NodeTree root;

    public MyBinarySearchTree() {
        root = null;
    }

    public void insert(int value) {
        NodeTree newNode = new NodeTree(value);
        if (root == null) {
            root = newNode;
        } else {
            NodeTree current = root;
            while (true) {
                if (value < current.value) {
                    if (current.left == null) {
                        current.left = newNode;
                        break;
                    } else {
                        current = current.left;
                    }
                } else {
                    if (current.right == null) {
                        current.right = newNode;
                        break;
                    } else {
                        current = current.right;
                    }
                }
            }
        }
    }

}