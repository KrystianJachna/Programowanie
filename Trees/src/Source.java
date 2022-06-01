// Drzewa
import java.util.Random;
import java.util.Scanner;
class Node {
    public char info;
    public Node left;
    public Node right;

    public Node(char info) {
        this.info = info;
        this.left = this.right = null;
    }
}

class stackNode {
    public Node node;
    public stackNode prev;

    public stackNode(Node node) {this.node = node;}
}

class Stack {
    private stackNode top;

    public Stack() {this.top = null;}

    public void push(Node n) {
        stackNode tmp = top;
        top = new stackNode(n);
        top.prev = tmp;
    }

    public Node pop() {
        if (isEmpty()) return null;

        stackNode tmp = top;
        top = top.prev;
        return tmp.node;
    }

    public boolean isEmpty() {return (top == null);}

    public Node getTop() {
        if (isEmpty())
            return null;
        return top.node;
    }
}

class BinaryTree {
    public Node root;

    public BinaryTree() {root = null;}

    public Node getRoot(){ return root;}

    public void preorder(Node p) {
        if (p != null) {
            System.out.print(p.info + " ");
            preorder(p.left);
            preorder(p.right);
        }
    }

    public void inorder(Node p) {
        if (p != null) {
            inorder(p.left);
            System.out.print(p.info + " ");
            inorder(p.right);
        }
    }

    public void postorder(Node p) {
        if (p != null) {
            postorder(p.left);
            postorder(p.right);
            System.out.print(p.info + " ");
        }
    }

    public void inorderStack() {
        Stack S = new Stack();

        Node walkingElem = this.root;

        while (walkingElem != null || !S.isEmpty()) {
            if (walkingElem != null) {
                S.push(walkingElem);
                walkingElem = walkingElem.left;
            } else {
                walkingElem = S.pop();
                System.out.print(walkingElem.info + " ");
                walkingElem = walkingElem.right;
            }
        }
    }

    public void preoderStack() {
        Stack S = new Stack();

        Node walkingElem = this.root;

        while (walkingElem != null || !S.isEmpty()) {
            if (walkingElem != null) {
                System.out.print(walkingElem.info + " ");
                S.push(walkingElem);
                walkingElem = walkingElem.left;
            } else
                walkingElem = S.pop().right;
        }
    }

    public void postorderStack() {
        if (this.root == null)
            return;
        Stack S = new Stack();

        Node current = this.root;
        Node prev = null;
        S.push(root);

        while(!S.isEmpty()) {
            current = S.getTop();

            if (prev == null || prev.left == current || prev.right == current) {
                if (current.left != null)
                    S.push(current.left);
                else if (current.right != null)
                    S.push(current.right);
                else {
                    S.pop();
                    System.out.print(current.info + " ");
                }
            }
            else if (current.left == prev) {
                if (current.right != null)
                    S.push(current.right);
                else {
                    S.pop();
                    System.out.print(current.info + " ");
                }
            }
            else if (current.right == prev) {
                S.pop();
                System.out.print(current.info + " ");
            }
            prev = current;
        }
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

    public void insert(char ch, Node p, Node parent, boolean right) {
        if (root == null) {
            root = new Node(ch);
            return;
        }

        if (p == null) {
            p = new Node(ch);
            if (right)
                parent.right = p;
            else
                parent.left = p;
        }
        else {
            Random rand = new Random();
            int randInt = rand.nextInt(2);
            if (randInt == 0)
                insert(ch, p.right, p, true);
            else
                insert(ch, p.left, p, false);
        }
    }
}

public class Source {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String [] args) {
        /*
        BinaryTree tree = new BinaryTree();
        tree.root = new Node('a');
        tree.root.left = new Node('b');
        tree.root.left.left = new Node('d');
        tree.root.right = new Node('c');
        tree.root.right.right = new Node('f');
        tree.root.right.left = new Node('e');
        tree.root.right.left.right = new Node('g');
        tree.root.right.right.right = new Node('i');
        tree.root.right.right.left = new Node('h');

        tree.displayTree(tree.root, 1);
        System.out.println();
        System.out.println();
        System.out.println();

        tree.preorder(tree.root);
        System.out.println();
        tree.preoderStack();
        System.out.println();
        System.out.println();

        tree.inorder(tree.root);
        System.out.println();
        tree.inorderStack();
        System.out.println();
        System.out.println();

        tree.postorder(tree.root);
        System.out.println();
        tree.postorderStack();
        System.out.println();
        System.out.println();

         */
        BinaryTree tree = new BinaryTree();
        for (int i = 0; i < 26; ++i) {
            //System.out.println((char) ('a' +i));
            tree.insert((char) ('a' +i), tree.getRoot(), null, true);
        }
        tree.displayTree(tree.getRoot(), 0);

    }
}
