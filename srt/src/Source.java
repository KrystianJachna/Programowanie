public class Source {
    public static void main(String [] args) {
        int [] arr = {2, 1, 2, 0, 4, 0, 2, 2, 4, 0};
        arr = countSort(arr, 5);
        System.out.println(
        );

    }

    public static int[] countSort(int [] arr, int m, int i) {
        int [] tab = new int [10];

        for (int i = 0; i < m; ++i)
            tab[i] = 0;

        for (int i = 0; i < arr.length; ++i)
            tab[getiByte(arr[i])] ++;

        for (int i = 1; i < m; ++i)
            tab[i] += tab[i-1];

        int [] sorted = new int[arr.length];

        for (int i = arr.length - 1; i >= 0; --i)
            sorted[--tab[getiByte(arr[i]])] = arr[i];

        return sorted;
    }
}

public static void radixSort() {
    for (int i = 0; i < byte; ++i)
        countSort(arr, i)
}