class queueArray{
    private int maxSize;
    private int [] Elem;
    private int front;
    private int rear;

    private int addOne(int i) {
        return (i + 1) % maxSize;
    }

    public queueArray(int size) {
        maxSize = size;
        Elem = new int [size];
        front = 0;
        rear = 0;
    }

    public void enQueue (int x) {
        if (!isFull())
            Elem[rear++] = x;
        else
            System.out.print("Error FULL!");
    }

    public int deQueue() {
        if (!isEmpty()) {
            int tmp = Elem[front];
            front = addOne(front);
            return tmp;
        }
        else
            System.out.println("Error EMPTY!");
        return -1;
    }

    public int getFront() {
        if (!isEmpty())
            return Elem[front];
        else
            System.out.println("Error EMPTY!");
        return -1;
    }

    public boolean isFull() {
        return (addOne(rear) == front);
    }

    public boolean isEmpty() {
        return (rear == front);
    }
}

class priorityQueue{
    private int maxSize;
    private int size;
    private int [] Elem;

    public priorityQueue(int maxSize) {
        this.maxSize = maxSize;
        Elem = new int [maxSize];
        size = -1;
    }
    public void insert(int x) {
        Elem[++size] = x;
    }
    public int max() {
        int max = Elem[size];
        for (int i = 0; i <= size; ++i)
            if(Elem[i] > max)
                max = Elem[i];
        return max;
    }
    public void deleteMax() {
        int maxID = Elem[size];
        for (int i = 0; i <= size; ++i)
            if(Elem[i] > Elem[maxID])
                maxID = i;

        for (int i = maxID; i < size; ++i)
            Elem[i] = Elem[i+1];
        size--;
    }
}

class priorityQueueBetter{
    private int [] Elem;
    int maxSize;
    int size;

    public priorityQueueBetter(int maxSize) {
        this.maxSize = maxSize;
        Elem = new int [maxSize];
        size = 0;
    }
    public int max() {
        if (size > 0)
            return Elem[size - 1];
        return -1;
    }
    public void deleteMax() {
        if (size == 0)
            return;
        int maxID = 0;
        for (int i = 0; i < size; ++i) {
            if (Elem[maxID] < Elem[i])
                maxID = i;
        }
        for (int i = maxID; i < size; ++i)
            Elem[i] = Elem[i+1];
        size--;
    }
    public void insert(int x) {
        int i;
        for (i = 0; i < size; ++i) {
            if (Elem[i] > x)
                break;
        }
        int tmp = Elem[i];
        int tmp2;
        Elem[i] = x;
        size ++;
        for (i = i + 1; i < size; ++i) {
            tmp2 = Elem[i];
            Elem[i] = tmp;
            tmp = tmp2;
        }
    }
}

class sidesQueue {
    private int maxSize;
    private int [] Elem;
    private int front;
    private int rear;

    private int addOne(int i) {
        return (i + 1) % maxSize;
    }
    private int subOne(int i) {
        if (i - 1 < 0)
            return maxSize - 1;
        else
            return i - 1;
    }
    public sidesQueue(int s) {
        maxSize = s;
        Elem = new int [s];
        front = 0;
        rear = 0;
    }
    public boolean isEmpty() {
        return (rear == front);
    }
    public boolean isFull() {
        return (addOne(rear) == front);
    }
    public void insertLeft(int x) {
        if (!isFull()){
            Elem[subOne(front)] = x;
            front = subOne(front);
        }
        else
            System.out.println("er");
    }
    public void insertRight(int x) {
        if (!isFull()){
            Elem[rear] = x;
            rear = addOne(rear);
        }
        else
            System.out.println("er");
    }
    public void removeLeft() {
        if (!isEmpty()) {
            front = addOne(front);
        }
        else
            System.out.println("er");
    }
    public void removeRight() {
        if (!isEmpty()) {
            rear = subOne(rear);
        }
        else
            System.out.println("er");
    }
    public int getFirst() {
        if (!isEmpty()) {
            return Elem[front];
        }
        else
            System.out.println("er");
        return -1;
    }
    public int getLast() {
        if (!isEmpty()) {
            return Elem[subOne(rear)];
        }
        else
            System.out.println("er");
        return -1;
    }
}

public class Source {
    public static void main(String [] args) {
        sidesQueue q = new sidesQueue(10);
        q.insertLeft(1);
        q.insertLeft(2);
        q.insertLeft(3);
        q.insertLeft(4);
        q.insertLeft(5);
        q.insertRight(6);
        q.insertRight(7);
        q.insertRight(8);
        q.insertRight(9);
        System.out.println(q.getFirst());
        System.out.println(q.getLast());
        q.removeLeft();
        q.removeRight();
        System.out.println(q.getFirst());
        System.out.println(q.getLast());


    }
}
