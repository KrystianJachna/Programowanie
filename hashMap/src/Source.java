// tablice haszuajace

import javax.xml.crypto.Data;

class DataItem {
    private int key;                // element danych (klucz)
    public DataItem(int info) {     // konstruktor
        key = info;
    }
    public int getKey() {
        return key;
    }
}

class HashTable {
    private DataItem[] hashArray; // tablica rozproszona
    private int m; // rozmiar tablicy rozproszonej
    private int n; // liczba elementów zajętych w tablicy
    private DataItem nonItem; // do usuwania elementów

    public HashTable(int size) { // konstruktor
        m = size;
        n = 0;
        hashArray = new DataItem[m]; // tworzy tablicę rozproszoną
        nonItem = new DataItem(-1); // klucz elementu usuniętego to -1
    }

    public int h(int key) {
        return key % m; // funkcja rozpraszająca
    }

    public DataItem search (DataItem item) {
        int key = item.getKey();
        int id = h(key);

        while(hashArray[id] != null) {
            if (hashArray[id].getKey() == item.getKey())
                return hashArray[id];
            id = (id + 1) % m;
        }
        return null;
    }

    public void insert (DataItem item) {
        int key = item.getKey();
        int idn = h(key);

        while (hashArray[idn] != null && hashArray[idn].getKey() != nonItem.getKey())
            idn = (idn + 1) % m;

        hashArray[idn] = item;
        n++;
    }

    public DataItem delete(DataItem item) {
        int key = item.getKey();
        int idn = h(key);

        while(hashArray[idn] != null && hashArray[idn].getKey() != nonItem.getKey()) {
            if (hashArray[idn].getKey() == item.getKey()) {
                DataItem tmp = hashArray[idn];
                hashArray[idn] = nonItem;
                n--;
                return tmp;
            }
            idn = (1 + idn) % n;
        }
        return null;
    }

    public HashTable realocate() {
        HashTable tmp = new HashTable(m*2);
        for (int i = 0; i < m; ++i) {
            if (hashArray[i] != null)
                tmp.insert(hashArray[i]);
        }
        return tmp;
    }
}



    public class Source {
    public static void main (String [] args) {
        HashTable ht = new HashTable(100);
        ht.insert(new DataItem(10));
        ht.insert(new DataItem(1));
        ht.insert(new DataItem(13));
        ht.insert(new DataItem(58));
        ht.insert(new DataItem(310));
        ht.insert(new DataItem(230));
        ht.insert(new DataItem(310));
        ht.insert(new DataItem(910));
        ht.insert(new DataItem(1321));

        ht = ht.realocate();
        System.out.print("");
    }
}
