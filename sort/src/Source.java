import java.time.Duration;
import java.time.Instant;


public class Source {
    static  int [] ar = {
            87, 32, 39, 27, 33, 35, 67, 57, 48, 22, 3, 98, 7, 88, 23, 62, 37, 56, 68, 51, 82, 70, 49, 28, 6, 60, 99, 47, 30, 100, 53, 75, 63, 31, 64, 54, 76, 86, 25, 91, 9, 85, 80, 15, 13, 21, 14, 44, 4, 61, 12, 93, 89, 55, 78, 45, 36, 29, 66, 74, 8, 40, 42, 38, 26, 16, 2, 77, 46, 73, 52, 24, 95, 69, 10, 96, 65, 1, 59, 11, 18, 50, 83, 19, 5, 41, 90, 79, 34, 71, 20, 58, 72, 81, 17, 84, 94, 97, 92, 43
    };

    static int [] ar2 = {10, 3, 3, 3 , 2, 1, 6, 5, 8, 7, 9};
    static int [] ar1 = {1, 2 , 3, 3, 3, 3, 5, 6, 7, 8, 9};

    public static void main (String [] args) {
        display();
        insertWithBin();
        display();

    }

    // jest stabilny!
    public static void bubbleSort() {
        int size = ar.length;

        for (int i = 0; i < size; ++i)
            for (int j = 1; j < size - 1; ++j)
                if (ar[j - 1] > ar[j])
                    swap(j - 1,j);
    }

    public static void selectionSort() {
        int size = ar.length;

        for (int i = 0; i < size - 1; ++i) {
            int minID = i;
            for ( int j = i + 1; j < size; ++j ) {
                if ( ar[minID] > ar[j] )
                    minID = j;
            }
            swap(minID,i);
        }
    }

    // tutaj bez swapow
    public static void insertionSort() {
        int size = ar.length;

        for (int i = 1; i < size; ++i) {
            int j = i - 1;
            int tmp = ar[i];

            while (j >= 0 && tmp < ar[j] ) {
                ar[j+1] = ar[j];
                --j;
            }
            ar[j + 1] = tmp;

        }
    }

    public static int binSearch(int l, int r, int max) {
        int mid = 0;

        while (l <= r) {
            mid = (l + r) / 2;

            if (ar[mid] < max)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return r + 1;
    }


    public static void coctailSort() {
        int size        = ar.length;
        int start       = 0;
        int end         = size - 1;
        boolean swapped = true;
        int tmpEnd = 0;
        int tmpStart = 0;

        while (swapped) {
            swapped = false;

            for (int i = start; i < end; ++i) {
                if (ar[i] > ar[i + 1]) {
                    swapped = true;
                    tmpEnd = i;
                    swap(i, i + 1);
                }
            }
            end = tmpEnd;

            if (!swapped) break;
            swapped = false;

            for (int i = end; i > start; --i) {
                if (ar[i - 1] > ar[i]) {
                    swapped = true;
                    tmpStart = i;
                    swap(i, i - 1);
                }
            }
            start = tmpStart;
        }
    }

    public static void insertWithBin() {
        int size = ar.length;

        for (int i = 0; i < size; ++i) {
            int tmp = ar[i];
            int pos = binSearch(0, i, ar[i]);
            int j = i;

            while (j > pos)
                ar[j] = ar[--j];

            ar[j] = tmp;
        }

    }

    public static void stableSelectionSort() {
        int minID = 0;
        int size = ar.length;

        for (int i = 0; i < size - 1; ++i) {
            minID = i;
            for (int j = i + 1; j < size; ++j)
                if (ar[minID] > ar[j])
                    minID = j;

            int tmp = ar[minID];
            while (minID > i)
                ar[minID] = ar[--minID];
            ar[minID] = tmp;
        }
    }


    public static void display() {
        for (int i = 0; i < ar.length; ++i)
            System.out.print(ar[i] + " ");

        System.out.println();
    }
    public static void swap(int i, int j) {
        int tmp = ar[i];
        ar[i] = ar[j];
        ar[j] = tmp;
    }

}
