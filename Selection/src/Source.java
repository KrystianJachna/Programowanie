import java.util.Scanner;

public class Source {

    public static Scanner sc = new Scanner(System.in);
    //static int arr[] = {1, 10, 9, 5, 4 ,2 ,3, 7, 6, 8};
    /*public static int arr[] = {65, 108, 48, 36, 247, 234, 241, 270, 140,
            77, 16, 91, 97, 182, 109, 199, 160, 258, 218, 92, 181, 293,
            183, 46, 244, 265, 68, 223, 233, 124, 141, 197, 230, 123, 271,
            191, 257, 299, 205, 90, 26, 55, 300, 85, 238, 115, 20, 172, 40,
            53, 110, 39, 283, 248, 291, 107, 6, 25, 273, 117, 133, 217, 99,
            286, 116, 180, 120, 219, 5, 288, 263, 143, 136, 76, 162, 232, 102,
            10, 96, 18, 122, 269, 221, 296, 281, 121, 82, 260, 28, 32, 245, 80,
            274, 59, 279, 81, 290, 298, 178, 1};*/
    public static int arr[] = {6, 76, 1, 4, 34, 80, 63, 35, 8, 43, 49, 5, 17, 48, 39, 57, 52, 59, 67, 54, 85, 18, 73, 16, 62, 99, 96, 44, 2, 69, 40, 51, 77, 89, 100, 38, 45, 56, 65, 58, 46, 66, 81, 24, 97, 10, 26, 93, 98, 27, 11, 64, 7, 15, 53, 22, 37, 90, 68, 91, 50, 55, 75, 28, 25, 47, 13, 86, 84, 92, 21, 60, 32, 3, 71, 74, 29, 19, 72, 87, 9, 41, 36, 12, 33, 61, 70, 31, 88, 79, 14, 95, 42, 82, 23, 94, 30, 83, 20, 78};
    //public static int arr[] = {45, 6, 54, 22, 58, 37, 32, 15, 23, 4, 94, 27, 33, 2, 26, 54};


    public static void main(String [] args) {
        displayArray();
       // System.out.println(SelectNaiv(4));
        //for (int i = 99; i >= 1; --i) {
        //   System.out.println("wartosc: " + SelectImproved(0, arr.length - 1, i));
        //}
        System.out.println("wartosc: " + SelectImproved(0, arr.length - 1, 0));
        QuickSortR(0, arr.length - 1);
        displayArray();
    }

    public static int SelectNaiv(int k) {       // algorytm naiwny
        QuickSortR(0, arr.length - 1);
        return arr[k - 1];
    }

    public static void QuickSortR(int L, int R) {
        if (R <= L ) return;

        int q = PartitionHoare(L, R);
        QuickSortR(L, q-1);
        QuickSortR(q+1, R);
    }

    public static int PartitionHoare(int L, int R) {
        int pivotValue = arr[R];
        int start = L - 1;
        int stop = R;


        while (true) {
            while (arr[++start] < pivotValue);
            while (stop > L && arr[--stop] >= pivotValue); // tutaj sprawidzic

            if (start >= stop)
                break;
            else
                swap(start, stop);
        }
        swap(start ,R);
        return start;
    }

    public static void swap(int l, int r) {
        int tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }

    public static int Select(int L, int R, int k) {
        int smallerLast = L-1;
        int equalLast = L-1;
        int pivot = arr[R];


        for (int i = L; i < R; ++i) {
            if (arr[i] < pivot) {
                swap(++smallerLast, i);
                equalLast++;
            }
            if (arr[i] == pivot)
                swap(++equalLast, i);
        }
        swap(++equalLast, R);

        int s1Size = (smallerLast < L ? 0 : smallerLast - L + 1);
        int s2Size = (equalLast - smallerLast);

        if (k <= s1Size)
            return Select(L, smallerLast, k);
        if (k <= s1Size + s2Size)
            return pivot;
        return Select(equalLast+1, R, k - s1Size - s2Size);

        /*
            Dzielimy tablice pivotem, ktorym jest dowolny element tablicy
            Podzial wykonujemy na trzy czesci, jedna mniejsza od pivota,
            druga rowna pivotowi i trzecia wieksza od pivota. Wzorowany n
            a Lamuto
         */
    }

    public static int SelectImproved(int L, int R, int k) {
        int pos = PartitionHoare(L, R);

        if (pos - L == k )
            return arr[pos];

        if (k  < pos - L)        // jest w lewej
            return SelectImproved(L, pos - 1, k);
        else                    // jest w prawej
            return SelectImproved(pos + 1, R, k - pos + L - 1);
    }















    public static void displayArray() {
        for (int i = 0; i < arr.length; ++i) {
            if (i % 20 == 0 && i > 10)
                System.out.print(arr[i] + ", \n");
            else
                System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }
}
























