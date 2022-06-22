public class Source {
    public static int arr1[] = {6, 76, 1, 4, 34, 80, 63, 35, 8, 43, 49, 5, 17, 48, 39, 57, 52, 59, 67, 54, 85, 18, 73, 16, 62, 99, 96, 44, 2, 69, 40, 51, 77, 89, 100, 38, 45, 56, 65, 58, 46, 66, 81, 24, 97, 10, 26, 93, 98, 27, 11, 64, 7, 15, 53, 22, 37, 90, 68, 91, 50, 55, 75, 28, 25, 47, 13, 86, 84, 92, 21, 60, 32, 3, 71, 74, 29, 19, 72, 87, 9, 41, 36, 12, 33, 61, 70, 31, 88, 79, 14, 95, 42, 82, 23, 94, 30, 83, 20, 78};
    public static int arr[] = {8, 12, 3, 7, 15, 1, 18, 9, 8, 4, 17, 14, 11};
    public static void main (String [] args) {

        System.out.println(SelectImproved(0,arr.length - 1, 7));
    }





    public static int SelectImproved(int L, int R, int k) {
        int pos = PartitionHoare(L, R);

        if(k == pos - L + 1)
            return arr[pos];
        else if (k < pos - L + 1)
            return SelectImproved(L, pos -1, k);
        else
            return SelectImproved(pos + 1, R, k - (pos - L + 1));

    }

    public static int PartitionHoare(int L, int R) {
        int pivotValue = arr[R];
        int start = L - 1;
        int stop = R;


        while (true) {
            while (arr[++start] <= pivotValue);
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
}
