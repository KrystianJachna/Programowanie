// QuickSort iteracyjnie
import java.util.Scanner;

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
    public static Scanner sc = new Scanner(System.in);
    //public static int arr[] = {1, 10, 9, 5, 4 ,2 ,3, 7, 6, 8};
   /*public static int arr[] = {65, 108, 48, 36, 247, 234, 241, 270, 140,
            77, 16, 91, 97, 182, 109, 199, 160, 258, 218, 92, 181, 293,
            183, 46, 244, 265, 68, 223, 233, 124, 141, 197, 230, 123, 271,
            191, 257, 299, 205, 90, 26, 55, 300, 85, 238, 115, 20, 172, 40,
            53, 110, 39, 283, 248, 291, 107, 6, 25, 273, 117, 133, 217, 99,
            286, 116, 180, 120, 219, 5, 288, 263, 143, 136, 76, 162, 232, 102,
            10, 96, 18, 122, 269, 221, 296, 281, 121, 82, 260, 28, 32, 245, 80,
            274, 59, 279, 81, 290, 298, 178, 1};*/
   public static int arr[] = {6, 76, 1, 4, 34, 80, 63, 35, 8, 43, 49, 5, 17, 48, 39, 57, 52, 59, 67, 54, 85, 18, 73, 16, 62, 99, 96, 44, 2, 69, 40, 51, 77, 89, 100, 38, 45, 56, 65, 58, 46, 66, 81, 24, 97, 10, 26, 93, 98, 27, 11, 64, 7, 15, 53, 22, 37, 90, 68, 91, 50, 55, 75, 28, 25, 47, 13, 86, 84, 92, 21, 60, 32, 3, 71, 74, 29, 19, 72, 87, 9, 41, 36, 12, 33, 61, 70, 31, 88, 79, 14, 95, 42, 82, 23, 94, 30, 83, 20, 78};
    public static void main(String [] args) {
            displayArray();
            QuickSortWithoutStack(0, arr.length - 1);
            //insertionSort(0, arr.length - 1);
            displayArray();
    }

    public static void QuickSortWithoutStack(int L, int R) {
        int pivNumber = 0;

        while (L < R || pivNumber > 0) {
            if (L < R) {
                int q = PartitionHoare(L, R);
                arr[R] = -arr[R];
                ++pivNumber;
                R = q - 1;
            }
            else {
                L = R + 2;
                R = findPiv();
                arr[R] = -arr[R];
                --pivNumber;
            }
        }
    }

    public static int findPiv() {
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] < 0)
                return i;
        }
        return arr.length - 1;
    }

    public static void QuickSortNoDups(int L, int R) {
        int pivNumber = 0;

        while (L < R || pivNumber > 0) {
            if (L < R) {
                int q = PartitionHoare(L, R);
                markPiv(q, L, R);
                ++pivNumber;
                R = q - 1;
            }
            else {
                L = R + 2;
                R = findPivMark(L);
                arr[R] = -arr[R];
                --pivNumber;
            }
        }
    }

    public static void markPiv(int pivotIndex,int L, int R) {
        int maxIndex = L;
        int maxValue = arr[L];

        for (int i = L; i < pivotIndex; ++i) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
                maxIndex = arr[i];
            }
        }

        int tmp = arr[maxIndex];
        arr[maxIndex] = arr[pivotIndex - 1];
        arr[pivotIndex] = arr[R];
        arr[R] = tmp;
    }

    public static int findPivMark(int Lp) {
        for (int i = Lp + 1; i < arr.length; ++i) {
            if (arr[i] < arr[Lp])
                return i;
        }
        return arr.length - 1;
    }

    public static void QuickSortR(int L, int R) {
        if (R - L + 1 <= 5) {
            //insertionSort(L, R);
            return;
        }

        //int q = PartitionHoare(L, R);
        int q = PartitionLamuto(L, R);
        QuickSortR(L, q-1);
        QuickSortR(q+1, R);
    }

    public static void QuickSortI(int L, int R) {
        Stack st = new Stack();
        int tmpR = R;
        int tmpL = L;

        while (L < R || !st.isEmpty()) {
            if (L < R) {
                int q = PartitionHoare(L, R);
                if (q - L > R - q) {
                    st.push(q+1, R);
                    R = q - 1;
                } else {
                    st.push(L, q - 1);
                    L = q + 1;
                }
                /*int q = PartitionHoare(L, R);
                st.push(q + 1, R);
                R = q -1;*/
            }
            else {
                Boarders tmp = st.pop();
                L = tmp.L;
                R = tmp.R;
            }
        }
    }

    public static int PartitionHoare(int L, int R) {

        swap(R, getRandomNumber(L, R)); // randomizacja
        int pivotValue = arr[R];
        int start = L - 1;
        int stop = R;


        while (true) {
            while (arr[++start] < pivotValue);
            while (stop > L && arr[--stop] > pivotValue); // tutaj sprawidzic

            if (start >= stop)
                break;
            else
                swap(start, stop);
        }
        swap(start ,R);
        return start;
    }

    public static int PartitionLamuto(int L, int R) {
        swap(getMedian(L, R), R); // mediana trzech
        int boarder = L - 1;
        int pivot = arr[R];

        for (int i = L; i < R; ++i) {
            if (arr[i] < pivot)
                swap(++boarder, i);
        }
        swap(++boarder, R);
        return boarder;
    }

    public static void swap(int l, int r) {
        int tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }

    public static void displayArray() {
        for (int i = 0; i < arr.length; ++i) {
            if (i % 20 == 0)
                System.out.print(arr[i] + ", \n");
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    public static void insertionSort(int L, int R) {
        for (int i = L + 1; i <= R; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (arr[j] > key && j >= L) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }

    public static int getRandomNumber(int min, int max) { // do zapamietania
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static int getMedian (int L, int R) {
        int a = arr[L];
        int b = arr[R];
        int c = arr[(L + R) / 2];

        if (a > b && a < c || a > c && a < b)
            return L;
        if (b > a && b < c || b > c && b < a)
            return R;
        return ((L+R) / 2);
    }
}