class Link {
    public int info;
    public Link next;

    public Link (int x, Link n) {
        info = x;
        next = n;
    }
}

class LinkList {
    public Link first;

    public LinkList() { first = null; }

    public void display () {
        Link we = first;

        while (we != null) {
            System.out.println(we.info);
            we = we.next;
        }
    }

    public Link locate(int searchKey) {
        Link we = first;

        while (we != null) {
            if (we.info == searchKey)
                return we;
        }
        return null;
    }

    public void insert(Link p, int x) {
        Link newLink = new Link(x, p.next);
        p.next = newLink;
    }

    public void insertFirst(int x) {
        if (first != null)
            first = new Link(x, first.next);
        else
            first = new Link(x, null);
    }

    public void deleteLink(Link p) {
        p.next = p.next.next;
    }

    public void delete(int sk) {
        Link we = first;
        Link prev = null;

        while (we != null && we.info != sk) {
            prev = we;
            we = we.next;
        }

        if (we != null) {
            if (prev != null)
                prev.next = we.next;
            else
                first = first.next;
        }

    }
}

class headList {
    private Link first;

    public headList() {
        first = new Link(-1, null);
    }
    public boolean isEmpty() {
        return (first.next == null);
    }
    public void insert(int x, Link p) {
        p.next = new Link(x, p.next.next);
    }
    public void delete(Link p) {
        if (p.next != null)
            p.next = p.next.next;
    }
    public Link locate(int sk) {
        Link we = first;

        while (we.next != null && we.next.info != sk) {
            we = we.next;
        }
        return we;
    }
    public void displayList() {
        Link we = first;
        while (we != null) {
            System.out.println(we.info);
            we = we.next;
        }
    }
}



class podWiazLista{
    private Link first;
    private Link last;

    public podWiazLista() {
        first = null;
        last = null;
    }
    public void makeNull() {
        first = null;
        last = null;
    }
    public void displayForward() {
        Link we = first;
        while (we != null) {
            System.out.print(we.info + " ");
            we = we.next;
        }
        System.out.println();
    }
    public void displayBackwards() {
        Link we = last;
        while (we != null) {
            System.out.print(we.info + " ");
            we = we.prev;
        }
        System.out.println();
    }
    public void insertFirst(int x) {
        Link prevFirst = first;
        first = new Link(x, prevFirst, null);
        if (last == null)
            last = first;
        prevFirst.prev = first;
    }
    public void insertLast(int x) {
        Link prevLast = last;
        last = new Link(x, null, prevLast);
        prevLast.next = last;
    }
    public void insertAfter(int x, int key) {
        Link we = first;
        while (we != null && we.info != key) {
            we = we.next;
        }

        if (we == last)
            insertLast(x);
        else {
            we.next = new Link(x, we.next.next, we);
            we.next.next.prev = we.next;
        }
    }
    public void deleteFirst() {
        if (first != null)
            first = first.next;
    }
    public void deleteLast(){
        if (last != null)
            last = last.prev;
    }
    public void deleteKey(int key) {
        Link we = first;

        while (we != null && we.info != key) {
            we = we.next;
        }

        if (we == first)
            deleteFirst();
        else if (we == last)
            deleteLast();
        else {
            we.prev.next = we.next;
            we.next.prev = we.prev;
        }
    }
    public Link Locate(int key) {
        Link we = first;

        while (we != null && we.info != key) {
            we = we.next;
        }
        return we;
    }
}

class LinkStack {
    private Link top;

    public LinkStack() { top = null; }

    public boolean isEmpty() { return (top == null); }

    public void push(int x) {
        top = new Link(x, top);
    }

    public int pop() {
        Link prevTop = top;
        top = top.prev;
        return prevTop.info;
    }
}

class queue {
    Link front;
    Link rear;

    public queue() {
        front = new Link(-1 , null);
        rear = front;
    }
    public boolean isEmpty() {
        return (front == rear || front.next == null);
    }
    public void enQueue(int x) {
        rear.next = new Link(x, null);
        rear = rear.next;
    }
    public int deQueue() {
        int v = front.next.info;
        front = front.next;
        return v;
    }
}

public class Source {
    public static void main (String [] args) {

    }
}
