import java.util.Random;
import java.util.Scanner;

class Node {
    public int info;
    public Node left;
    public Node right;

    public Node(int info) {
        this.info = info;
        this.left = this.right = null;
    }
}



class BinaryTree {
    public Node root;

    public BinaryTree() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }


    public void displayTree(Node p, int h) {
        if (p != null) {
            displayTree(p.right, ++h);

            for (int i = 0; i < h; ++i)
                System.out.print("  ");
            System.out.println(p.info);

            displayTree(p.left, h);
        }
    }


    public class Source {
        public static Scanner sc = new Scanner(System.in);

        public static void main(String[] args) {

            BinaryTree bst = new BinaryTree();
            bst.root = new Node(12);
            bst.root.right = new Node(17);
            bst.root.right.right = new Node(19);
            bst.root.right.left = new Node(15);
            bst.root.right.left.right = new Node(16);
            bst.root.left = new Node(7);
            bst.root.left.left = new Node(4);


        }
    }
}
