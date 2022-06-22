class Boarders {
    int L;
    int R;

    private Boarders prev;

    public Boarders(int L, int R) {
        this.L = L;
        this.R = R;

        prev = null;
    }

    public void setPrev(Boarders prev) {
        this.prev = prev;
    }

    public Boarders getPrev() {return prev;}
}

class Stack {

    private Boarders top;

    public Stack() {top = null;}

    public boolean isEmpty() {
        return (top == null);
    }

    public Boarders pop() {
        if (this.isEmpty())
            return null;

        Boarders tmp = top;
        top = top.getPrev();
        return tmp;
    }

    public Boarders top(){return top;}

    public void push(int L, int R) {
        Boarders prevTop = top;
        top = new Boarders(L, R);
        top.setPrev(prevTop);
    }

}


public class Source {
    public static int [] ar1 = {22, 29, 77, 81, 17, 71, 10, 23, 39, 63, 60, 35, 62, 46, 15, 94, 55, 26, 70, 82, 21, 89, 32, 14, 58, 65, 61, 51, 18, 5, 99, 20, 40, 16, 76, 66, 73, 3, 95, 88, 98, 83, 53, 97, 42, 100, 85, 9, 86, 84, 79, 72, 50, 74, 36, 27, 87, 6, 31, 19, 48, 75, 92, 4, 80, 67, 68, 96, 37, 90, 38, 78, 28, 1, 45, 59, 56, 93, 30, 44, 64, 91, 33, 24, 57, 25, 43, 41, 7, 2, 52, 54, 11, 69, 13, 49, 34, 47, 12, 8};
    public static int ar[] = {6, 76, 1, 4, 34, 80, 63, 35, 8, 43, 49, 5, 17, 48, 39, 57, 52, 59, 67, 54, 85, 18, 73, 16, 62, 99, 96, 44, 2, 69, 40, 51, 77, 89, 100, 38, 45, 56, 65, 58, 46, 66, 81, 24, 97, 10, 26, 93, 98, 27, 11, 64, 7, 15, 53, 22, 37, 90, 68, 91, 50, 55, 75, 28, 25, 47, 13, 86, 84, 92, 21, 60, 32, 3, 71, 74, 29, 19, 72, 87, 9, 41, 36, 12, 33, 61, 70, 31, 88, 79, 14, 95, 42, 82, 23, 94, 30, 83, 20, 78};

    public static void main(String [] args) {
        QuickSortNoDups(0, ar.length - 1);
        System .out.println();
    }

    public static void QuickSortRec(int L, int R) {
        if (R - L <= 20) {
         //insertionSort(L, R);
         return;
        }

        int q = partitionLamuto(L, R);
        QuickSortRec(L, q - 1);
        QuickSortRec(q + 1, R);
    }

    public static void QuickSortIter() {
        int L = 0;
        int R = ar.length - 1;

        Stack st = new Stack();

        while (L < R || !st.isEmpty()) {
            if (L < R) {
                int q = partitionLamuto(L, R);
                st.push(q + 1, R);
                R = q - 1;
            } else {
                Boarders brds = st.pop();
                L = brds.L;
                R = brds.R;
            }
        }


    }

    public static int partitionHoare(int L, int R) {
        int pivValue = ar[R];
        int lb = L - 1;
        int rb = R;

        while (true) {
            while (ar[++lb] < pivValue);
            while (rb > L && ar[--rb] > pivValue);

            if (lb >= rb) break;

            swap(lb, rb);
        }
        swap(lb, R);
        return lb;
    }

    public static int partitionLamuto(int L, int R) {
        int pivVal = ar[R];
        int b = L - 1;

        for (int i = L; i < R; ++i) {
            if (ar[i] < pivVal) {
                swap (++b, i);
            }
        }
        swap(++b,R);
        return b;
    }

    public static void insertionSort(int L, int R) {
        for (int i = L; i <= R; ++i) {
            int tmp = ar[i];
            int j = i - 1;
            while (j >= L && ar[j] > tmp) {
                ar[j + 1] = ar[j];
                j--;
            }
            ar[j+1] = tmp;
        }
    }



    public static void swap(int i, int j) {
        int tmp = ar[i];
        ar[i] = ar[j];
        ar[j] = tmp;
    }



    public static void QuickSortNoDups(int L, int R) {
        int pivNumber = 0;

        while (L < R || pivNumber > 0) {
            if (L < R) {
                int q = partitionHoare(L, R);
                markPiv(q, L, R);
                ++pivNumber;
                R = q - 1;
            }
            else {
                L = R + 2;
                R = findPivMark(L);
                ar[R] = -ar[R];
                --pivNumber;
            }
        }
    }

    public static void markPiv(int pivotIndex,int L, int R) {
        int maxIndex = L;
        int maxValue = ar[L];

        for (int i = L; i < pivotIndex; ++i) {
            if (ar[i] > maxValue) {
                maxValue = ar[i];
                maxIndex = ar[i];
            }
        }

        int tmp = ar[maxIndex];
        ar[maxIndex] = ar[pivotIndex - 1];
        ar[pivotIndex] = ar[R];
        ar[R] = tmp;
    }

    public static int findPivMark(int Lp) {
        for (int i = Lp + 1; i < ar.length; ++i) {
            if (ar[i] < ar[Lp])
                return i;
        }
        return ar.length - 1;
    }
}
