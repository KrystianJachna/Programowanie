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

    public int getMin() {
        Node walkingElem = root;

        while (walkingElem.left != null)
            walkingElem = walkingElem.left;

        return walkingElem.info;
    }

    public int getMax() {
        Node walkingElem = root;

        while (walkingElem.right != null)
            walkingElem = walkingElem.right;

        return walkingElem.info;
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

    public Node searchh(int x) {
        Node walkingElem = root;

        while (walkingElem != null && walkingElem.info != x) {
            if (walkingElem.info > x)
                walkingElem = walkingElem.left;
            else
                walkingElem = walkingElem.right;
        }
        return walkingElem;
    }

    public void insert(int x) {
        if (root == null) {
            root = new Node(x);
            return;
        }
        Node walkingElem = root;
        Node prev = null;

        while (walkingElem != null) {
            prev = walkingElem;
            if (x > walkingElem.info)
                walkingElem = walkingElem.right;
            else
                walkingElem = walkingElem.left;
        }

        if (prev.info > x)
            prev.left = new Node(x);
        else
            prev.right = new Node(x);

    }

    public void delete(int x, Node toDel, Node parent) {

        if (root.info == x)
            root = null;

        while (toDel != null && toDel.info != x) {
            parent = toDel;

            if (x > toDel.info)
                toDel = toDel.right;
            else
                toDel = toDel.left;
        }

        if (toDel == null)
            return;

        if (toDel.right == null && toDel.left == null) {
            if (parent.left == toDel)
                parent.left = null;
            else
                parent.right = null;
        }
        else if (toDel.right == null || toDel.left == null) {
            if (parent.left == toDel)
                parent.left = (toDel.left == null ? toDel.right : toDel.left);
            else
                parent.right = (toDel.left == null ? toDel.right : toDel.left);
        }
        else { // losowanie czy isc w prawy czy w lewo aby zachowac srednia oczekiwana dlugosc logratytmiczna
            Random rand = new Random();
            if (rand.nextInt(2) == 0) {
                Node min = toDel.right;
                while (min.left != null)
                    min = min.left;
                toDel.info = min.info;
                delete(min.info, toDel.right, toDel);
            }
            else {
                Node max = toDel.left;
                while (max.right != null)
                    max = max.right;
                toDel.info = max.info;
                delete(max.info, toDel.left, toDel); // potrzebny jest paren jakby trzeba to byl 1 przypadek
            }
        }
    }
}

    public class Source {
        public static Scanner sc = new Scanner(System.in);

        public static void main(String[] args) {

            BinaryTree bst = new BinaryTree();
            bst.root = new Node(14);
            bst.root.right = new Node(16);
            bst.root.left = new Node(4);
            bst.root.left.right = new Node(12);
            bst.root.left.right.left = new Node(10);
            bst.root.left.right.left.left = new Node(6);
            bst.root.left.right.left.left.right = new Node(8);
            bst.root.left.left = new Node(2);

            //bst.insert(15);
            bst.delete(4, bst.root, null);
            bst.displayTree(bst.root, 1);


        }
    }

