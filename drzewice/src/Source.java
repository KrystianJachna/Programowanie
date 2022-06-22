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
    private int Index;


    public BinaryTree() {
        root = null;
        Index = 0;
    }

    public Node getRoot() {
        return root;
    }

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
        Node we = root;
        Stack st = new Stack();


        while (we != null ||  !st.isEmpty()) {
            if (we != null) {
                st.push(we);
                we = we.left;
            }
            else {
                we = st.pop();
                System.out.println(we.info);
                we = we.right;
            }
        }

    }

    public void preorderStack() {
        Node we = root;
        Stack st = new Stack();


        while (we != null || !st.isEmpty()) {
            if (we != null) {
                System.out.print(we.info);
                st.push(we.right);
                we = we.left;
            }
            else {
                we = st.pop();
            }
        }
    }

    public void postorderStack() {
        if (this.root == null)
            return;
        Stack S = new Stack();

        Node current = this.root;
        Node prev = null;
        S.push(root);

        while (!S.isEmpty()) {
            current = S.getTop();

            if (prev == null || prev.left == current || prev.right == current) {
                if (current.left != null)
                    S.push(current.left);
                else if (current.right != null)
                    S.push(current.right);
                else  {
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

    void displayTree (Node p, int h) {
        if (p != null) {
            displayTree(p.right, h+1);
            for(int i = 0; i < h; ++i) System.out.print(" ");
            System.out.println(p.info);
            displayTree(p.left, h + 1);
        }
    }

    public void constructTreeInPost(char [] postorderArr, char [] inorderArr) {
        Index = postorderArr.length - 1;
        this.root =cnostructTreeInPost(postorderArr, inorderArr, 0, postorderArr.length - 1);
        Index = 0;
    }

    private Node cnostructTreeInPost(char [] postOrderArr, char [] inorderArr, int inStart, int inEnd) {
        if (inStart > inEnd)        return null;

        Node n = new Node(postOrderArr[Index]);
        Index--;

        if (inStart == inEnd) return n;

        int i = searchForIndex(inorderArr, n.info);
        n.right = cnostructTreeInPost(postOrderArr, inorderArr, i + 1, inEnd);
        n.left = cnostructTreeInPost(postOrderArr,inorderArr, inStart, i - 1);
        return n;
    }

    public void constructTreeInPre(char [] preorderArr, char [] inorderArr) {
        Index = 0;
        this.root =cnostructTreeInPre(preorderArr, inorderArr, 0, preorderArr.length - 1);
        Index = 0;
    }
    private Node cnostructTreeInPre(char [] preeorderArr, char [] inorderArr, int inStart, int inEnd) {
        if (inStart > inEnd) return null;

        Node n = new Node(preeorderArr[Index]);
        ++Index;

        if (inStart == inEnd)
            return n;

        int i = searchForIndex(preeorderArr, n.info);
        n.left = cnostructTreeInPre(preeorderArr, inorderArr, inStart, i - 1);
        n.right = cnostructTreeInPre(preeorderArr, inorderArr, i + 1, inEnd);
        return n;
    }

    public int searchForIndex(char [] postorderArr, char toSearch) {
        for (int i = 0; i < postorderArr.length; ++i) {
            if (postorderArr[i] == toSearch)
                return i;
        }
        return -1;
    }

    public void constrctTreePP(char [] preorderArr, char [] postorderArr) {
        Index = 0;
        this.root = constructTreePostPree(preorderArr, postorderArr, 0, postorderArr.length - 1);
        Index = 0;
    }

    private Node constructTreePostPree(char [] preorderArr, char [] postorderArr, int postStart, int postEnd) {
        if (postStart > postEnd)
            return null;

        Node n = new Node(preorderArr[Index]);
        Index++;

        if (postEnd == postStart)
            return n;

        int i = searchForIndex(postorderArr, preorderArr[Index]);
        n.left = constructTreePostPree(preorderArr,postorderArr, postStart,  i);
        n.right = constructTreePostPree(preorderArr, postorderArr, i + 1, postEnd - 1);
        return n;
    }

}




public class Source {
    public static void main (String [] args) {
        BinaryTree tree = new BinaryTree();
//char [] preorder = {'A', 'D', 'H', 'L', 'Z', 'P', 'C'};
        char [] postorder = {'d', 'b', 'g', 'e', 'h', 'i', 'f', 'c', 'a'};
        char [] preorder = {'a', 'b', 'd', 'c', 'e', 'g', 'f', 'h', 'i'};
        char [] inorder = {'d', 'b', 'a', 'e', 'g', 'c', 'h', 'f', 'i'};
        tree.constrctTreePP(preorder, postorder);
        tree.displayTree(tree.getRoot(), 1);


        tree.root = new Node('a');
        tree.root.left = new Node('b');
        tree.root.left.left = new Node('d');
        tree.root.right = new Node('c');
        tree.root.right.right = new Node('f');
        tree.root.right.left = new Node('e');
        tree.root.right.left.right = new Node('g');
        tree.root.right.right.right = new Node('i');
        tree.root.right.right.left = new Node('h');

        tree.postorderStack();
    }
}
