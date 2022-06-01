// Drzewa

class Node {
    public int info;
    public Node left;
    public Node right;

    public Node(int info) {
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

class BST {
    private Node root;

    public BST() {root = null;}

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

        Node root = this.root;

        // do poprawy po na poczatku jest przeciewz pusty

        while(!S.isEmpty()) {
            while(root != null) {
                S.push(root.right);
                S.push(root);
                root = root.left;
            }
            root = S.pop();

            if (root != null && root.right == S.getTop()) {
                S.pop();
                S.push(root);
                root = S.getTop().right;
            }
        }
    }

    public void displayTree(Node p, int h) {
        if (p != null) {
            displayTree(p.right, ++h);

            for (int i = 0; i < h; ++i)
                System.out.print(" ");
            System.out.println(p.info);

            displayTree(p.left, ++h);
        }
    }
}

public class Source {
}
