// ShellSort

import java.util.Scanner;

public class Source {
    public static Scanner sc = new Scanner(System.in);
    public static int arr[] = {1, 10, 9, 5, 4 ,2 ,3, 7, 6, 8};
    /*public static int arr[] = {65, 108, 48, 36, 247, 234, 241, 270, 140,
            77, 16, 91, 97, 182, 109, 199, 160, 258, 218, 92, 181, 293,
            183, 46, 244, 265, 68, 223, 233, 124, 141, 197, 230, 123, 271,
            191, 257, 299, 205, 90, 26, 55, 300, 85, 238, 115, 20, 172, 40,
            53, 110, 39, 283, 248, 291, 107, 6, 25, 273, 117, 133, 217, 99,
            286, 116, 180, 120, 219, 5, 288, 263, 143, 136, 76, 162, 232, 102,
            10, 96, 18, 122, 269, 221, 296, 281, 121, 82, 260, 28, 32, 245, 80,
            274, 59, 279, 81, 290, 298, 178, 1};*/
   // public static int arr[] = {6, 76, 1, 4, 34, 80, 63, 35, 8, 43, 49, 5, 17, 48, 39, 57, 52, 59, 67, 54, 85, 18, 73, 16, 62, 99, 96, 44, 2, 69, 40, 51, 77, 89, 100, 38, 45, 56, 65, 58, 46, 66, 81, 24, 97, 10, 26, 93, 98, 27, 11, 64, 7, 15, 53, 22, 37, 90, 68, 91, 50, 55, 75, 28, 25, 47, 13, 86, 84, 92, 21, 60, 32, 3, 71, 74, 29, 19, 72, 87, 9, 41, 36, 12, 33, 61, 70, 31, 88, 79, 14, 95, 42, 82, 23, 94, 30, 83, 20, 78};

    public static void main(String [] args) {
        displayArray();
        shellSortWyklad();
        displayArray();
    }

    public static void shellSort() {
        int start = 0;

        int distance = 1;           // przyrost
        while (distance <= arr.length / 3)
            distance = 3 * distance + 1;

        while (distance > 0) {
            while (start < distance) {
                for (int i = start; i < arr.length; i += distance) {
                    int key = arr[i];
                    int j = i - distance;

                    while (j >= start && arr[j] > key) {
                        arr[j + distance] = arr[j];
                        j -= distance;
                    }

                    arr[j + distance] = key;
                }
                ++start;
            }
            start = 0;
            distance = (distance - 1) / 3;
        }
    }

    public static void shellSortWyklad() {
        int j, k, n;
        n = arr.length;
        int tmp;
        int h = 1;
        while(h <= n/3)
            h = h*3 + 1;

        while(h>0) {

            for(k = h; k < n; k++){
                tmp = arr[k];
                j = k;

                while(j>h-1 && tmp <= arr[j-h]) {
                    arr[j] = arr[j-h];
                    j=j-h;
                }
                arr[j] = tmp;
            }
            h = (h-1) / 3;
        }
    }



















    public static void displayArray() {
        for (int i = 0; i < arr.length; ++i) {
            if (i % 20 == 0)
                System.out.print(arr[i] + ", \n");
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

}