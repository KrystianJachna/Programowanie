import java.lang.reflect.Array;

class ArrayDups{
    private int [] ar;
    private int size;

    ArrayDups(int s) {
        size = s;
        ar = new int [size];
    }

    public void insert(int el) {
        int [] newAr = new int[size + 1];

        for (int i = 0; i < size; ++i)
            newAr[i] = ar[i];

        newAr[size] = el;
        ar = newAr;
        size += 1;
    }

    public int search(int el) {
        for (int i = 0; i < size; ++i)
            if (ar[i] == el)
                return i;
        return -1;
    }

    public void delete(int el) {
        int pos = search(el);

        if (pos != -1)
            for (int i = pos; i < size - 1; ++i)
                ar[i] = ar[i+1];
        size -= 1;
    }

    public void display() {
        for (int i = 0; i < size; ++i)
            System.out.print(ar[i] + " ");
        System.out.println();
    }

    public void overWriteDups() {
        for (int i = 0; i < size; ++i) {
            int k = i +1;
            for (int j = i +1; j < size; ++j)
                if (ar[i] != ar[j])
                    ar[k++] = ar[j];
            size = k;
        }
    }

    public int binSearch(int el) {
        int l = 0;
        int r = size - 1;
        int mid;

        while (l <= r) {
            mid = (l + r) / 2;

            if (el < ar[mid])
                r = mid - 1;
            else if (el > ar[mid])
                l = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    public int interPorSearch(int el) {
        int l = 0;
        int r = size - 1;
        int mid;

        while (l <= r) {
            mid = l + (el - ar[l]) * (r - l) / (ar[r] - ar[l]);

            if (el < ar[mid])
                r = mid - 1;
            else if (el > ar[mid])
                l = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    public int binSearchFirst(int el) {
        int l = 0;
        int r = size - 1;
        int mid;

        while (l <= r) {
            mid = (l + r) / 2;

            if (el > ar[mid])
                l = mid + 1;
            else
                r = mid - 1;

        }
        return r + 1;

    }

    public int binSearchLast(int el) {
        int l = 0;
        int r = size - 1;
        int mid;

        while (l <= r) {
            mid = (l + r) / 2;

            if (el < ar[mid])
                r = mid - 1;
            else
                l = mid + 1;

        }
        return l - 1;

    }

    public void insertSorted(int el) {

        int [] newar = new int [size + 1];
        for (int i = 0; i < size; ++i)
            newar[i] = ar[i];
        newar[size] = 100000;
        ar = newar;
        size += 1;

        int i = size - 1;

        while (el < ar[i] && i > 0) {
            ar[i] = ar[--i];
        }
        ar[i] = el;

    }
}




public class Source {
    public static void main(String args[]) {
        ArrayDups lol = new ArrayDups(0);
        for(int i = 0; i < 10; ++i)
            lol.insert(i);




        lol.display();
        System.out.println(lol.interPorSearch(9));

        //lol.display();


    }
}
