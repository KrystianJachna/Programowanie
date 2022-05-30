import java.util.Scanner;

public class Source {

    public static Scanner sc = new Scanner(System.in);
    //static int arr[] = {1, 10, 9, 5, 4 ,2 ,3, 7, 6, 8};
    //public static int arr[] = {6, 76, 1, 4, 34, 80, 63, 35, 8, 43, 49, 5, 17, 48, 39, 57, 52, 59, 67, 54, 85, 18, 73, 16, 62, 99, 96, 44, 2, 69, 40, 51, 77, 89, 100, 38, 45, 56, 65, 58, 46, 66, 81, 24, 97, 10, 26, 93, 98, 27, 11, 64, 7, 15, 53, 22, 37, 90, 68, 91, 50, 55, 75, 28, 25, 47, 13, 86, 84, 92, 21, 60, 32, 3, 71, 74, 29, 19, 72, 87, 9, 41, 36, 12, 33, 61, 70, 31, 88, 79, 14, 95, 42, 82, 23, 94, 30, 83, 20, 78};
    //public static int arr[] = {45, 6, 54, 22, 58, 37, 32, 15, 23, 4, 94, 27, 33, 2, 26, 54};


    public static void main(String [] args) {
        //int arr[] = {1, 10, 9, 5, 4 ,2 ,3, 7, 6, 8, 1, 10, 9, 5, 4 ,2 ,3, 7, 6, 81, 10, 9, 5, 4 ,2 ,3, 7, 6, 8};
        int arr[] = {6, 76, 1, 4, 34, 80, 63, 35, 8, 43, 49, 5, 17, 48, 39, 57, 52, 59, 67, 54, 85, 18, 73, 16, 62, 99, 96, 44, 2, 69, 40, 51, 77, 89, 100, 38, 45, 56, 65, 58, 46, 66, 81, 24, 97, 10, 26, 93, 98, 27, 11, 64, 7, 15, 53, 22, 37, 90, 68, 91, 50, 55, 75, 28, 25, 47, 13, 86, 84, 92, 21, 60, 32, 3, 71, 74, 29, 19, 72, 87, 9, 41, 36, 12, 33, 61, 70, 31, 88, 79, 14, 95, 42, 82, 23, 94, 30, 83, 20, 78};
        displayArray(arr);
        for (int i = 1; i <= arr.length; ++i) {

            System.out.println(Select(arr, i, 10));
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(insertionSort(arr, 0, arr.length - 1,  5));
        displayArray(arr);


    }

    public static int Select(int [] arr, int k, int p) {
        if (arr.length < p)
            return insertionSort(arr, 0, arr.length -1 ,k);

        int [] tmpArr = new int [arr.length % 5 == 0 ? arr.length / 5 : arr.length / 5 + 1];
        for (int i = 0, j = 0; i < arr.length; i+=5, ++j) {
            if (i + 4 >= arr.length)
                tmpArr[j] = insertionSort(arr, i, arr.length - 1, (arr.length - i)/2);
            else
                tmpArr[j] = insertionSort(arr, i, i+4,  3);
        }
        int M = Select(tmpArr, tmpArr.length/2, p);



        int [] S1 = new int [arr.length]; int s1Size = 0;
        int [] S2 = new int [arr.length]; int s2Size = 0;
        int [] S3 = new int [arr.length]; int s3Size = 0;
        //int pivot = arr[arr.length - 1];


        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] < M)
                S1[s1Size++] = arr[i];
            if (arr[i] == M)
                S2[s2Size++] = arr[i];
            if (arr[i] > M)
                S3[s3Size++] = arr[i];
        }


        if (k <= s1Size) {
            int [] newArr = new int [s1Size];
            for (int i = 0; i < s1Size; ++i) {
                newArr[i] = S1[i];
            }
            return Select(newArr, k, p);
        }
        if (k <= s1Size + s2Size)
            return M;

        int [] newArr = new int [s3Size];
        for (int i = 0; i < s3Size; ++i) {
            newArr[i] = S3[i];
        }
        return Select(newArr, k - s1Size - s2Size, p);
    }



    public static int insertionSort(int arr[], int L, int R, int k) {
        for (int i = L; i <=  R; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= L && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
        return arr[L + k - 1];
    }













    public static void displayArray(int [] arr) {
        for (int i = 0; i < arr.length; ++i) {
            if (i % 20 == 0 && i > 10)
                System.out.print(arr[i] + ", \n");
            else
                System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }
}