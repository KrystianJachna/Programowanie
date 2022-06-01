import java.util.Random;
import java.util.Scanner;

class HeapMax {
    private int [] arr;
    private int last;
    private int Size;

    public HeapMax() {
        arr = new int[20];
        Size = 20;
        last = -1;
    }

    private void upHeap(int k) {
        int tmp = arr[k];
        int i = (k-1) / 2; // poprzednik

        while (k > 0 && arr[i] < tmp) {
            arr[k] = arr[i];
            k = i;
            i = (i - 1) / 2;
        }
        arr[k] = tmp;
    }

    private void downHeap(int k) {
        int tmp = arr[k];
        int n = last + 1;
        int j;

        while (k < n / 2){
            j = 2*k + 1;
            if (j < n - 1 && arr[j] < arr[j+1])
                ++j;

            if (tmp >= arr[j]) break;

            arr[k] = arr[j];
            k = j;
        }
        arr[k] = tmp;
    }

    public void Insert(int x) {
        if (last == Size - 1) {
            doubleSize();;
            Insert(x);
        }
        else {
            arr[++last] = x;
            upHeap(last);
        }
    }

    public int Max() {
        if (last < 0)
            return -1;
        return arr[0];
    }

    public int DeleteMax() {
        int root = arr[0];
        arr[0] = arr[last--];
        downHeap(0);
        return root;
    }

    public int [] Construct(int [] a) {
        for (int i = 0; i < a.length; ++i)
            Insert(a[i]);
        for (int i = 0; i < a.length; ++i)
            a[i] = DeleteMax();
        return a;
    }

    private void doubleSize() {
        int [] newArr = new int [2 * Size];
        for (int i = 0; i < Size; ++i)
            newArr[i] = arr[i];

        Size *= 2;
        arr = newArr;
    }

}

public class Source {
    public static Scanner sc = new Scanner(System.in);
    //public static int arr[] = {13, 21, 52, 123, 7, 2 , 1 , 0, 232, 21, 12, 3, 4, 21, 5, 7, 3, 90, 21};
    public static int arr[] = {10, 8, 4, 15, 13, 2, 18, 17, 6, 9, 3, 14};
    public static void main(String[] args) {
        HeapMax heap = new HeapMax();

       heapSort();


       System.out.println("xd");




    }

    public static void  heapSort() {
        for (int k = (arr.length -1) / 2; k>=0; k--)
            downHeap(k, arr.length);

        int n = arr.length;

        while (n > 0) {
            swap(0, n-1);
            n--;
            downHeap(0, n);
        }
    }

    public static void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static  void downHeap(int k, int n) {
        int tmp = arr[k];
        int j;

        while (k < n / 2){
            j = 2*k + 1;
            if (j < n - 1 && arr[j] < arr[j+1])
                ++j;

            if (tmp >= arr[j]) break;

            arr[k] = arr[j];
            k = j;
        }
        arr[k] = tmp;
    }
}