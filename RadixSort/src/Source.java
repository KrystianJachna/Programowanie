import java.util.Scanner;

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
    //public static int arr[] = {6, 76, 1, 4, 34, 80, 63, 35, 8, 43, 49, 5, 17, 48, 39, 57, 52, 59, 67, 54, 85, 18, 73, 16, 62, 99, 96, 44, 2, 69, 40, 51, 77, 89, 100, 38, 45, 56, 65, 58, 46, 66, 81, 24, 97, 10, 26, 93, 98, 27, 11, 64, 7, 15, 53, 22, 37, 90, 68, 91, 50, 55, 75, 28, 25, 47, 13, 86, 84, 92, 21, 60, 32, 3, 71, 74, 29, 19, 72, 87, 9, 41, 36, 12, 33, 61, 70, 31, 88, 79, 14, 95, 42, 82, 23, 94, 30, 83, 20, 78};
    // public static int arr[] = {45, 6, 54, 22, 58, 37, 32, 15, 23, 4, 94, 27, 33, 2, 26, 54};

    public static void main(String[] args) {
        //int [] arr = {2, 1, 2, 0, 4, 0, 2, 2, 4, 0};
        //int []arr = {102, 220, 120, 012, 210, 011, 100, 121, 021, 202, 022, 200, 201};
        int []arr = {170, 45, 75, 90, 802, 24, 2, 66};

        arr = RadixSort(arr, 3);
        displayArray(arr);
    }

    public static int [] CountSort(int [] arr,  int byteNumber) {
        int [] tab = new int [10];
        for (int i = 0; i < tab.length; ++i)
            tab[i] = 0;

        for (int i = 0; i < arr.length; ++i)
            tab[getByte(arr[i], byteNumber)]++;

        for (int i = 1; i < tab.length; ++i)
            tab[i] += tab[i-1];

        int [] sortedArr = new int [arr.length];

        for (int i = arr.length - 1; i >= 0; --i)       // od konca dla zachowania stabilnosci !!!
            sortedArr[--tab[getByte(arr[i], byteNumber)]] = arr[i];

        return sortedArr;

    }

    public static int getByte (int number, int byteNumber) {
        String tmp = Integer.toString(number);
        if ((tmp.length() - 1 - byteNumber) < 0)
            return 0;
        else
            return Character.getNumericValue(tmp.charAt(tmp.length() - 1 - byteNumber));
    }

    public static int [] RadixSort(int [] arr, int byteMax) {

        int curByte = 0;
        while (curByte < byteMax) {
            arr = CountSort(arr, curByte);
            curByte++;
        }
        return arr;
    }








    public static void displayArray(int []arr) {
        System.out.println("-------------------------------------");
        for (int i = 0; i < arr.length; ++i) {
            if (i % 20 == 0) {
                System.out.print(arr[i] + ", \n");
            }
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
        System.out.println("-------------------------------------");
    }
}