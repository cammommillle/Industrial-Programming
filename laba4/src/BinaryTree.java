import java.util.Comparator;

public class BinaryTree<T extends Comparator<T>>
{
    class Node
    {
        T value;
        Node left;
        Node right;
        Node(T value) {
            this.value = value;
            right = null;
            left = null;
        }
    }
    Node root;

    private Node addRecursive(Node current, T value)
    {
        if (current == null) {
            return new Node(value);
        }

        if (value.compare(value, current.value) < 0) {
            current.left = addRecursive(current.left, value);
        } else if (value.compare(value, current.value) > 0) {
            current.right = addRecursive(current.right, value);
        } else {
            // value already exists
            return current;
        }

        return current;
    }
    public void add(T value) throws NullPointerException
    {

        root = addRecursive(root, value);
    }

    private boolean containsNodeRecursive(Node current, T value) {
        if (current == null) {
            return false;
        }
        if (value.compare(value, current.value) == 0) {
            return true;
        }
        return value.compare(value, current.value) < 0
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }
    public boolean containsNode(T value) {
        return containsNodeRecursive(root, value);
    }

    private T findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }
    private Node deleteRecursive(Node current, T value) {
        if (current == null) {
            return null;
        }

        if (value.compare(value, current.value) == 0) {
            //no child
            if (current.left == null && current.right == null) {
                return null;
            }
            //right or left child
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }
            //both childs
            T smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;

        }
        if (value.compare(value, current.value) < 0) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }
    public void delete(T value) {
        root = deleteRecursive(root, value);
    }

    //«Левый- Вершина -Правый»
    public void traverseLRootR() {
        System.out.println("Left-Root-Right");
        traverseLRootR(root);
    }
    private void traverseLRootR(Node node) {
        if (node != null) {
            traverseLRootR(node.left);
            System.out.print(" " + node.value);
            traverseLRootR(node.right);
        }
    }
    //«Вершина-Левый-Правый»
    public void traverseRootLR() {
        System.out.println("Root-Left-Right");
        traverseRootLR(root);
    }
    private void traverseRootLR(Node node) {
        if (node != null) {
            System.out.print(" " + node.value);
            traverseRootLR(node.left);
            traverseRootLR(node.right);
        }
    }
    //«Левый-Правый- Вершина»
    public void traverseLRRoot() {
        System.out.println("Left-Right-Root");
        traverseLRRoot(root);
    }
    private void traverseLRRoot(Node node) {
        if (node != null) {
            traverseLRRoot(node.left);
            traverseLRRoot(node.right);
            System.out.print(" " + node.value);
        }
    }

}


