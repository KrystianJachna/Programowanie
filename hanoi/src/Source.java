class Source {
    static int nDisks = 5;
    public static void main(String[] args) {
        // Przenosi nDisks z 'A' na 'B' wykorzystując 'C'
        Towers2(nDisks, 'A', 'C', 'B');
    }
    //-----------------------------------------------------------
    public static void Towers2(int n, char src, char inter, char dest) {
        // src - zrodlowa, inter - pomocnicza, dest - docelowa

        char tmp;
        while(n>0){
            Towers2(n-1, src, dest, inter); // z zrodlowej na pomocnicza…
            System.out.println("Dysk: "+n+" z "+src+" na "+dest);
            // przesuwamy ostatni
            n--;
            tmp = inter ; inter = src; src = tmp; // swap(inter,src)

        }
    }
    //-------------------------------------------------------------
}