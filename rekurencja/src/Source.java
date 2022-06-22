class Link {
    public int info;
    public Link prev;

    public Link (int x, Link n) {
        info = x;
        prev = n;
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

public class Source {
    public static LinkStack st = new LinkStack();
    public static int [] ar = {11, 8, 7 ,6, 5};
    public static int [] ar1 = {53, 58, 37, 64, 22, 75, 51, 68, 40, 6, 77, 43, 54, 78, 45, 31, 5, 86, 49, 72, 76, 1, 55, 4, 65, 36, 59, 96, 80, 7, 21, 70, 18, 48, 8, 25, 42, 93, 47, 73, 19, 95, 52, 9, 30, 12, 66, 34, 71, 20, 2, 82, 28, 99, 60, 17, 56, 29, 23, 81, 38, 41, 69, 62, 89, 92, 11, 32, 50, 90, 61, 97, 44, 27, 57, 94, 46, 15, 100, 3, 16, 74, 39, 26, 14, 63, 79, 67, 88, 35, 87, 24, 13, 83, 91, 33, 98, 10, 84, 85};
    public static void main(String [] args) {
        pack(0, 20);
        System.out.println();
    }

    public static int Fib(int n) {
        if (n == 0) return 0;
        else if (n == 1) return 1;
        else return (Fib(n-1) + Fib(n - 2));
    }
    public static int FibI(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int s;
        int bb = 0;
        int b = 1;

        for (int i = 2; i <= n; ++i) {
            s = bb + b;
            bb = b;
            b = s;
        }
        return b;
    }
    public static int binSearch(int key, int l, int r) {
        if (l <= r) {
            int mid = (l + r) / 2;
            if (key > ar[mid])
                return binSearch(key, mid + 1, r);
            else if (key < ar[mid])
                return binSearch(key, l, r - 1);
            else
                return mid;
        }
        return -1;



    }

    public static void MergeSort(int L, int R) {
        if (L >= R) return;

        int M = (L + R) / 2;
        MergeSort(L, M);
        MergeSort(M + 1, R);
        Merge(L, M, R);
    }
    public static void Merge(int L, int M, int R) {
        int [] tmpAr = new int [ar.length];

        for (int i = L; i <= R; ++i) tmpAr[i] = ar[i];

        int i = L;
        int j = M + 1;
        int k = L;

        while (i <= M && j <= R) {
            if (tmpAr[i] <= tmpAr[j]) ar[k++] = tmpAr[i++];
            else                      ar[k++] = tmpAr[j++];
        }
        while (i <= M)                ar[k++] = tmpAr[i++];
        while (j <= R)                ar[k++] = tmpAr[j++];
    }

    public static boolean pack(int i, int sizeLeft) {
        if (sizeLeft == 0) return true;
        if (sizeLeft < 0) return false;

        if (i  < ar.length) {
            st.push(ar[i]);
            if (pack(i + 1, sizeLeft - ar[i]))
                return true;
            st.pop();
            if (pack(i + 1, sizeLeft))
                return true;
        }
        return false;
    }
}
