public class Tree<T extends Comparable<T>>
{
    class Node
    {
        T value;
        Node left;
        Node right;

        Node(T value) {
            this.value = value;
            left = right = null;
        }
    }
    Node root;
    Tree(T root){
        this.root = new Node(root);
    }

    boolean search(T value)
    {
        Node current = root;
        while (current != null)
        {
            if(value.compareTo(current.value) == 0) return true;
            else if (value.compareTo(current.value) < 0) current = current.left;
            else current = current.right;
        }
        return false;
    }


}
