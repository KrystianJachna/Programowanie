import java.util.Scanner;

class ListArray{
    private int maxSize;
    int size;
    int [] Elem;

    public ListArray(int maxSize) {
        this.maxSize = maxSize;
        Elem = new int [maxSize];
        size = 0;
    }

    public int end() { return size; };

    public int first() { return 0; }

    public int locate(int searchElem) {
        for (int i = 0; i < size; ++i)
            if (Elem[i] == searchElem)
                return i;
        return size;
    }

    public void insert(int pos, int el) {
        if (pos > end())
            return;
        size ++;
        int tmp = Elem[pos];
        Elem[pos] = el;
        int tmp2;

        for (int i = pos + 1; i < size; i++) {
            tmp2 = Elem[i];
            Elem[i] = tmp;
            tmp = Elem[i];
        }
    }

    public void delete(int pos) {
        for (int i = pos; i < size - 1; ++i)
            Elem[i] = Elem[i+1];
        size--;
    }

    public void display(){
        for (int i = 0; i < size; ++i)
            System.out.print(Elem[i] + " ");
        System.out.println();
    }

    public int retrieve(int pos) {
        if (pos > end() - 1)
            return -1;

        return Elem[pos];
    }

    public int next(int pos) {
        if (pos + 1 > end() - 1)
            return -1;
        return pos + 1;
    }

    public int previous(int pos) {
        if (pos - 1 < 0)
            return -1;
        return pos - 1;
    }

}



class stackArray{
    private int maxSize;
    private int top;
    private char [] Elem;

    public stackArray(int size) {
        maxSize = size;
        Elem = new char [size];
        top = -1;
    }

    public void push (char el) {
        Elem[++top] = el;
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public char pop() {
        if (!isEmpty())
            return Elem[top--];
        return '0';
    }

    public char top() {
        if (!isEmpty())
            return Elem[top];
        return '0';
    }
}


public class Source {
    public static char[] symbols =
            {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                    'o', 'p', 'q', 'r', 's', 't', 'u' ,'v', 'w', 'x', 'y', 'z'};
    public static char[] leftOperators =
            {'*', '/', '%', '+', '-', '<', '>', '?', '&', '|'}; // operatory lewostronne
    public static char[] rightOperators =
            {'!', '~', '^', '='};
    public static char[] operatorsINF =
            { '!', '~', '^', '*', '/', '%', '+',
                    '-', '<', '>', '?', '&', '|', '='};
    public static Scanner sc = new Scanner(System.in);

    public static void main(String []args) {
        String ONP = sc.next();

        System.out.print(getValueONP(ONP));


    }

    public static void INFtoONP(String ONP) {
        stackArray st = new stackArray(ONP.length());

        for (int i = 0; i < ONP.length(); ++i) {
            if (inSet(ONP.charAt(i), symbols))
                System.out.print(ONP.charAt(i));
            else if (ONP.charAt(i) == '(')
                st.push(ONP.charAt(i));
            else if (inSet(ONP.charAt(i), operatorsINF)) {
                if (inSet(ONP.charAt(i), leftOperators))
                    while (st.top() != '(' && getPriority(st.top()) >= getPriority(ONP.charAt(i)) )
                        System.out.print(st.pop());
                else {
                    while (getPriority(st.top()) > getPriority(ONP.charAt(i)))
                        System.out.print(st.pop());
                }
                st.push(ONP.charAt(i));
            }
            else if (ONP.charAt(i) == ')') {
                while (st.top() != '(')
                    System.out.print(st.pop());
                st.pop();
            }
        }
        while (!st.isEmpty())
            System.out.print(st.pop());
    }

    public static boolean inSet (char toCheck, char [] set) {       // sprawdza czy jest operatorem lewostronnym
        for (char ch : set)
            if (toCheck == ch)
                return true;
        return false;
        /*
            Sprawdza czy dany znak jest lewostronnie laczny, przechodzac przez wszystkie lewostronnie laczne operatory.
         */
    }


    public static int getPriority(char ch) {            // zwraca liczbe tym wyzsza im wyzszy jest priorytet operatora
        if (ch == '(' || ch == ')')
            return 10;
        else if (ch ==  '!' || ch == '~')
            return 9;
        else if (ch == '^')
            return 8;
        else if (ch == '*' || ch == '/' || ch == '%')
            return 7;
        else if (ch == '+' || ch == '-')
            return 6;
        else if (ch == '<' || ch == '>')
            return 5;
        else if (ch == '?')
            return 4;
        else if (ch == '&')
            return 3;
        else if (ch == '|')
            return 2;
        else if (ch == '=')
            return 1;
        else return 0;

        /*
            Ustala priorytet operatorow wedle wytycznych do zadania
         */
    }

    public static int getValueONP(String ONP) {
        stackArray st = new stackArray(ONP.length());

        for (int i = 0; i < ONP.length(); ++i) {
            if (isDigit(ONP.charAt(i)))
                st.push(ONP.charAt(i));
            else {
                char b = st.pop();
                char a =  st.pop();
                st.push((char)count(a, b, ONP.charAt(i)));
            }

        }
        return st.pop() - '0';
    }

    public static int count (char ca, char cb, char oper) {
        int a = ca - '0';
        int b = cb - '0';

        switch(oper)
        {
            case '+':
                return a + b + '0';
            case '-':
                return a - b + '0';
            case '*':
                return a * b + '0';
            case '/':
                return a / b + '0';//dzielenie całkowite
        }
        return 0;
    }

    public static boolean isDigit(char znak)
    {
        return znak >= '0' && znak <= '9';
    }
    //sprawdzam, czy dany znak jest jednym z czterech operatorów
    public static boolean isOper(char znak)
    {
        return znak == '+' || znak == '-' || znak == '*' || znak == '/';
    }
 }
