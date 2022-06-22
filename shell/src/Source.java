public class Source {
    public static int [] ar = {22, 29, 77, 81, 17, 71, 10, 23, 39, 63, 60, 35, 62, 46, 15, 94, 55, 26, 70, 82, 21, 89, 32, 14, 58, 65, 61, 51, 18, 5, 99, 20, 40, 16, 76, 66, 73, 3, 95, 88, 98, 83, 53, 97, 42, 100, 85, 9, 86, 84, 79, 72, 50, 74, 36, 27, 87, 6, 31, 19, 48, 75, 92, 4, 80, 67, 68, 96, 37, 90, 38, 78, 28, 1, 45, 59, 56, 93, 30, 44, 64, 91, 33, 24, 57, 25, 43, 41, 7, 2, 52, 54, 11, 69, 13, 49, 34, 47, 12, 8};
    public static void main (String [] args) {
        combSort();
        System.out.println();
    }

    public static void ShellSort() {
        int size = ar.length;
        int h = 1;

        while (h <= size / 3) h = 3 * h + 1;

        while (h > 0) {
            for (int i = h; i < size; ++i) {
                int tmp = ar[i];
                int j = i;

                while (j >= h && ar[j-h] > tmp) {
                    ar[j] = ar[j - h];
                    j -= h;
                }

                ar[j] = tmp;
            }

            h = (h - 1 ) / 3;
        }
    }

    public static void combSort() {
        int size = ar.length;
        int h = size;
        boolean swapped = true;

        while (h > 1 || swapped) {
            h = h * 10/13;
            if (h == 0) h = 1;
            swapped = false;

            for (int i = 0; i < size - h; ++i) {
                if (ar[i] > ar[i + h]) {
                    swap(i, i+h);
                    swapped = true;
                }

            }
        }
    }

    public static void swap (int i, int j) {
        int tmp = ar[i];
        ar[i] = ar[j];
        ar[j] = tmp;
    }
}
