// Algorytm Kadane

public class Source {
    public static void main(String args[]) {
        int arr[] = {-2, 7, -4, 8, -5, 4};
        int arr1[] = {1, -3, 2, 1, -1};
        int n = arr1.length;

        int [][] ar2D = {
                {6, -5, -7, 4, -4},
                {-9, 3, -6, 5, 2},
                {-10, 4, 7, -6, 3},
                {-8, 9, -3, 3, -7}
        };

        //naiveOn3(arr1, n);
        //naiveOn2(arr1, n);
        //KadaneOn1(arr1, n);
        Kadane2d(ar2D, 4, 5);
    }

    public static void naiveOn3(int [] arr, int n) {
        int lb = 0;
        int rb = 0;
        int maxSum = 0;

        int sum = 0;

        for (int i = 0; i <n; ++i) {
            for (int j = i; j < n - 1; ++j) {
                for (int k = j; k < n; ++k) {
                    sum += arr[k];

                    if (sum > maxSum) {
                        maxSum = sum;
                        lb = j;
                        rb = k;
                    }
                }
                sum = 0;
            }
        }

        System.out.println(lb + " " + rb + " " + maxSum);
    }

    public static void naiveOn2(int [] arr, int n) {
        int lb = 0;
        int rb = 0;
        int maxSum = 0;

        int sum = 0;

        for (int i = 0; i < n - 1; ++i) {
            for (int j = i; j < n; ++j) {
                sum += arr[j];

                if (sum > maxSum) {
                    maxSum = sum;
                    lb = i;
                    rb = j;
                }
            }
            sum = 0;
        }



        System.out.println(lb + " " + rb + " " + maxSum);
    }

    public static void KadaneOn1(int [] arr, int n) {
        int lb = 0;
        int rb = 0;
        int maxSum = 0;

        int sum = 0;

        for (int i = 0; i < n; ++i) {
            sum += arr[i];

            if (sum > maxSum) {
                rb = i;
                maxSum = sum;
            }
            if (sum < 0) {
                lb = i + 1;
                sum = 0;
            }
        }

        System.out.println(lb + " " + rb + " " + maxSum);
    }

    public static void Kadane2d(int [][] arr, int r, int c) {
        int top = 0;
        int bot = 0;
        int maxSum = -1;
        int left = 0;
        int right = 0;

        int [] subSum = new int [c];
        int curSum = 0;
        int curLeft = 0;


        for (int i = 0; i < r; ++i) {
            for (int j = i; j < r; ++j) {
                for (int k = 0; k < c; ++k) {
                    subSum[k] += arr[j][k];
                    curSum += subSum[k];


                    if (curSum > maxSum) {
                        top = i;
                        bot = j;
                        right = k;
                        left = curLeft;
                        maxSum = curSum;
                    }
                    if (curSum < 0) {
                        curLeft = k + 1;
                        curSum = 0;
                    }

                }
                curLeft = 0;
                curSum = 0;
            }
            subSum = new int [c];
        }

        if (maxSum > 0)
            System.out.printf(
                    "n = %d, m = %d, s = %d, mst = a[%d..%d][%d..%d]", (bot - top + 1),
                    (right - left + 1), maxSum, top, bot, left, right);
        else
            System.out.println("nie ma rozwiazania");
    }


}
