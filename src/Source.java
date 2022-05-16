// Sortowanie Zaawansowane
import java.util.Scanner;


public class Source {
    public static Scanner sc = new Scanner(System.in);
    public static int [] tab = {
            58,	20,	17,	46,	40,
            35,	87,	49,	71,	41,
            87,	45,	10,	11,	16,
            76,	31,	26,	12,	75,
            79,	46,	10,	33,	57,
            72,	34,	84,	56,	96,
            62,	10,	91,	46,	99,
            41,	66,	96,	3,  1,
            77,	56,	34,	98,	49,
            15,	70,	85,	85,	64,
            84,	80,	87,	84,	69,
            67,	98,	80,	55,	94,
            92,	28,	14,	67, 76,
            12,	99,	44,	75,	74,
            21,	71,	19,	99,	77,
            57,	53,	94,	60,	58,
            10,	34,	27,	38,	42,
            43,	76,	31,	87,	49,
            89,	34,	26,	84,	34,
            36,	37,	56,	17,	56
    };

    public static void main(String[] args) {
        QuickSort(0, tab.length - 1);
        displayTab();
    }

    public static void  QuickSort(int L, int R) {
        if (L >= R)
            return;
        else if (R - L + 1 <= 10)
            InsertionSort(L, R);
        else {
            int q = PartitionHORE(L, R);
            QuickSort(L, q - 1);
            QuickSort(q + 1, R);
        }
    }

    public static int PartitionHORE (int L, int R) {
        int i = L - 1;
        int j = R;
        int pivot = tab[R];

        while (true) {
            while (tab[++i] < pivot);
            while (j > L && tab[--j] > pivot);

            if (i >= j)
                break;
            else {
                int tmp = tab[i];
                tab[i] = tab[j];
                tab[j] = tmp;
            }
        }
        int tmp = tab[i];
        tab[i] = tab[R];
        tab[R] = tmp;
        return i;
    }


    public static void InsertionSort(int L, int R) {
        int tmp;
        int j;

        for (int i = L + 1; i <= R; ++i) {
            tmp = tab[i];
            j = i - 1;

            while (j >= L && tmp < tab[j]) {
                tab[j+1] = tab[j];
                --j;
            }
            tab[j+1] = tmp;
        }
    }

    public static void displayTab() {
        for (int i = 0; i < tab.length; ++i) {
            System.out.printf("%4d",  tab[i]);
            if ((i +1)% 25 == 0)
                System.out.println("");
        }
    }
}
