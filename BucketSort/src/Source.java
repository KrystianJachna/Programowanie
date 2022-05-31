import java.util.Scanner;

class number {
    double n;
    number next;

    public number(double n) {
        this.n = n;
        next = null;
    }
    public void setNext(number next) {
        this.next = next;
    }
}

class List {
    number first;

    public List() {
        first = null;
    }
    public void insert(double n) {
        number toInsert = new number(n);
        if (first == null) {
            first = toInsert;
            return;
        }
        if (first.n > n) {
            number tmp = first;
            first = toInsert;
            toInsert.setNext(tmp);
            return;
        }

        number walkingElem = first;

        while(walkingElem.next != null && walkingElem.next.n < n)
            walkingElem = walkingElem.next;

        number tmp = walkingElem.next;
        walkingElem.setNext(toInsert);
        walkingElem.next.setNext(tmp);
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public double delFirst() {
        if (!isEmpty()) {
        number tmp = first;
        first = first.next;
        return tmp.n;
        }
        return -1;
    }
}


public class Source {
    public static Scanner sc = new Scanner(System.in);
    //public static int arr[] = {1, 10, 9, 5, 4 ,2 ,3, 7, 6, 8};
    //public static int arr[] = {6, 76, 1, 4, 34, 80, 63, 35, 8, 43, 49, 5, 17, 48, 39, 57, 52, 59, 67, 54, 85, 18, 73, 16, 62, 99, 96, 44, 2, 69, 40, 51, 77, 89, 100, 38, 45, 56, 65, 58, 46, 66, 81, 24, 97, 10, 26, 93, 98, 27, 11, 64, 7, 15, 53, 22, 37, 90, 68, 91, 50, 55, 75, 28, 25, 47, 13, 86, 84, 92, 21, 60, 32, 3, 71, 74, 29, 19, 72, 87, 9, 41, 36, 12, 33, 61, 70, 31, 88, 79, 14, 95, 42, 82, 23, 94, 30, 83, 20, 78};
    public static double arr[] = {0.78, 0.17, 0.39, 0.26, 0.72, 0.94, 0.21, 0.12, 0.23, 0.68};

    public static void main(String[] args) {
        displayArray();

        arr = BucketSort();

        displayArray();

    }

    public static double[] BucketSort() {
        List[] l = new List[10];
        for (int i = 0; i < 10; ++i) {
            l[i] = new List();
        }

        for (int i = 0; i < arr.length; ++i) {
            l[getKey(arr[i])].insert(arr[i]);
        }
        int j = 0;
        for (int i = 0; i < arr.length; ++i) {
            if(!l[j].isEmpty())
                arr[i] = l[j].delFirst();
            else {
                j++;
                i--;
            }
        }
        return arr;
    }

    public static int getKey(double number) {
        String tmp = number + "";
        for (int i = 0; i < tmp.length(); ++i) {
            if (tmp.charAt(i) == '.')
                return tmp.charAt(i+1) - '0';
        }
        return 0;
    }


    public static void displayArray() {
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