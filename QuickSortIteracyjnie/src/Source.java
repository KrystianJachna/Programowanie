// QuickSort iteracyjnie
import java.util.Scanner;

 class Boarders {
     int leftBoarder;
     int rightBoarder;
     Boarders lower;

     Boarders(int lb, int rb){
         this.leftBoarder = lb;
         this.rightBoarder = rb;
     }
 }

 class stack {
     Boarders firstBoarder;

 }


public class Source {
    public static Scanner sc = new Scanner(System.in);
    public static int arr[] = {65, 108, 48, 36, 247, 234, 241, 270, 140,
            77, 16, 91, 97, 182, 109, 199, 160, 258, 218, 92, 181, 293,
            183, 46, 244, 265, 68, 223, 233, 124, 141, 197, 230, 123, 271,
            191, 257, 299, 205, 90, 26, 55, 300, 85, 238, 115, 20, 172, 40,
            53, 110, 39, 283, 248, 291, 107, 6, 25, 273, 117, 133, 217, 99,
            286, 116, 180, 120, 219, 5, 288, 263, 143, 136, 76, 162, 232, 102,
            10, 96, 18, 122, 269, 221, 296, 281, 121, 82, 260, 28, 32, 245, 80,
            274, 59, 279, 81, 290, 298, 178, 1};

    public static void main(String [] args) {
        displayArray();
        QuickSort(0, arr.length - 1);
        //InsertionSort();
        displayArray();
    }

    public static void QuickSort(int l, int r) {
        if (r - l <= 5) {
            InsertionSort();
            return;
        }

        //int q = PartitionHoare(l, r);
        int q = PartitionLomuto(l, r);

        QuickSort(l, q - 1);
        QuickSort(q + 1, r);
    }

    public static int PartitionHoare(int l, int r) {
        int randIndex = getRandomNumber(l, r);          // randomizacja
        swap(randIndex, r);
        int pivotValue = arr[r];
        int start = l - 1;
        int stop = r;

        while(true) {
            while (arr[++start] < pivotValue);
            while (arr[--stop] > pivotValue);

            if (stop < start)   break;
            else {
                swap(start, stop);
            }
        }
        swap(start, r);

        return start;
    }

    public static int PartitionLomuto(int l, int r) {
        int pivotValue = arr[r];
        int boarder = l - 1;

        for (int i = l; i < r; ++i) {
            if (arr[i] <= pivotValue)
                swap(++boarder, i);

        }
        swap(++boarder, r);
        return boarder;

    }

    public static void displayArray() {
        for (int i = 0; i < arr.length; ++i) {
            if (i % 20 == 0)
                System.out.print(arr[i] + ", \n");
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    public static void swap(int fsI, int scI) {
        int tmp = arr[fsI];
        arr[fsI] = arr[scI];
        arr[scI] = tmp;
    }

    public static void InsertionSort() {
        int j;

        for (int i = 1;  i < arr.length; ++i) {
            j = i - 1;
            int key = arr[i];
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                --j;
            }
            arr[j + 1] = key;
        }
    }
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}