class Vertex {
    public char label;
    public boolean wasVisited;

    public Vertex(char label) {
        this.label = label;
        wasVisited = false;
    }
    /*
        Wierzcholek grafu
     */
}
class queueVertex {
    public int VERTEX;
    public queueVertex next;
    public queueVertex prev;
    public int fromVERTEX;
    public int cost;

    public queueVertex(int VERTEX) {
        fromVERTEX = 0;
        this.VERTEX = VERTEX;
        this.prev = null;
        this.next = null;
        cost = 0;
    }
}
class Link {
    public int VERTEX;
    public Link prev;

    public Link(int VERTEX, Link prev) {
        this.VERTEX = VERTEX;
        this.prev = prev;
    }
}
class LinkStack {
    private Link top;

    public LinkStack() { top = null; }

    public boolean isEmpty() { return (top == null); }

    public void push(int x) {
        top = new Link(x, top);
    }

    public int pop() {
        Link prevTop = top;
        top = top.prev;
        return prevTop.VERTEX;
    }

    public int top() {
        return top.VERTEX;
    }

    public boolean isIn(int x) {
        Link we = top;
        while (we != null) {
            if (we.VERTEX == x)
                return true;
            we = we.prev;
        }
        return false;
    }
}
class Queue {
    private queueVertex front;
    private queueVertex rear;

    public Queue() {
        front = null;
        rear = null;

    }
    public boolean  isEmpty() {
        return (front == null);
    }
    public void add (int v, int from, int cost) {
        if (!isEmpty()) {
            queueVertex prevFront = front;
            front = new queueVertex(v);
            front.fromVERTEX = from;
            front.cost = cost;

            front.next = prevFront;
            if (prevFront != null)
                prevFront.prev = front;
        } else {
            queueVertex n =  new queueVertex(v);
            n.fromVERTEX = from;
            n.cost = cost;
            front = rear = n;
        }
    }
    public int delete() {
        if (isEmpty()) return -1;

        int toRet = rear.VERTEX;
        rear = rear.prev;
        if (rear != null)
            rear.next = null;
        else
            front = null;
        return toRet;
    }

    public queueVertex deleteQueueVer() {
        if (isEmpty()) return null;

        queueVertex toRet = rear;
        rear = rear.prev;
        if (rear != null)
            rear.next = null;
        else
            front = null;
        return toRet;
    }
}
class Graph{
    private int MAX_VERTS = 20;
    private Vertex [] vertexAr;
    private int adjMat[][];
    private int num_vert;

    public Graph() {
        MAX_VERTS = 20;
        vertexAr = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        num_vert = 0;

        for (int i = 0; i < MAX_VERTS; ++i)
            for(int j = 0; j < MAX_VERTS; ++j)
                adjMat[i][j] = 0;

        for (int i = 0; i < MAX_VERTS; ++i)
            vertexAr[i] = null;
    }
    public void addVertex(char label) {
        vertexAr[num_vert++] = new Vertex(label);
    }
    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        //adjMat[end][start] = 1;
        // dla grafu niezorientowanego
    }
    public void displayVertex(int v) {
        System.out.println(vertexAr[v].label);
    }
    public void displayEdge(int u, int v) {
        System.out.println("( " + vertexAr[u].label + ", " + vertexAr[v].label + " )" );
        // moze jeszcze sprawdzenie czy taka krawedz jest?
    }
    public void BFS(int s) {
        Queue Q = new Queue();

        vertexAr[s].wasVisited = true;
        Q.add(s, -1, -1);

        while (!Q.isEmpty()) {
            int cur = Q.delete();
            System.out.print(vertexAr[cur].label + ", ");

            for (int i = 0; i < num_vert; ++i) {
                if (adjMat[cur][i] == 1 && vertexAr[i].wasVisited == false) {
                    Q.add(i, -1, -1);
                    vertexAr[i].wasVisited = true;
                }
            }
        }
        System.out.println();
        visitReset();
    }
    public void findingShortestPathPtoP(int start, int dest) {
        Queue Q = new Queue();

        vertexAr[start].wasVisited = true;
        Q.add(start, -1, 0);


        int pathAndCost [][] = new int[2][num_vert]; // 23          // id [to][cost]
        int pathAndCostElem = 0;

        while (!Q.isEmpty() && pathAndCostElem < num_vert) {
            queueVertex cur = Q.deleteQueueVer();

            if (pathAndCost[0][cur.VERTEX] == 0) {
                pathAndCost[0][cur.VERTEX] = cur.fromVERTEX;
                pathAndCost[1][cur.VERTEX] = cur.cost;
                pathAndCostElem++;
            }

            for (int i = 0; i < num_vert; ++i) {
                if (adjMat[cur.VERTEX][i] == 1 && vertexAr[i].wasVisited == false) {
                    Q.add(i, cur.VERTEX, cur.cost + 1);
                    vertexAr[i].wasVisited = true;
                }
            }
        }
        int to   = dest;

        while (to != start) {
            System.out.print(vertexAr[to].label + ", ");
            to = pathAndCost[0][to];
        }
        System.out.print(vertexAr[to].label + " cost = " + pathAndCost[1][dest]);
        System.out.println();
        visitReset();
    }
    public LinkStack DFSfind(int cur, int end, LinkStack st) {
        st.push(cur);
        if (cur == end) return st;
        vertexAr[cur].wasVisited = true;
        for (int i = 0; i < num_vert; ++i) {
            if (adjMat[cur][i] == 1 && vertexAr[i].wasVisited == false) {
                if (DFSfind(i, end, st) != null)
                    return st;

            }
        }
        st.pop();
        return null;
    }
    public void DFS(int cur, int starting) {
        System.out.print(vertexAr[cur].label + ", ");
        vertexAr[cur].wasVisited = true;
        for (int i = 0; i < num_vert; ++i) {
            if (adjMat[cur][i] == 1 && vertexAr[i].wasVisited == false) {
                DFS(i, starting);
            }
        }
    }
    public void DFSiter(int start) {
        LinkStack st = new LinkStack();
        st.push(start);
        vertexAr[start].wasVisited = true;
        //System.out.print(vertexAr[start].label + ", ");//

        while (!st.isEmpty()) {
            int cur = st.pop();
            System.out.print(vertexAr[cur].label + ", ");

            for (int i = 0; i <num_vert; ++i) {
                if (adjMat[cur][i] == 1 && vertexAr[i].wasVisited == false) {
                    st.push(i);
                    //System.out.print(vertexAr[i].label + ", ");
                    vertexAr[i].wasVisited = true;
                    break;
                }
            }
        }
        visitReset();
    }
    public void DFSminTree(int start) {
        LinkStack st = new LinkStack();
        st.push(start);
        vertexAr[start].wasVisited = true;
        boolean find = false;

        while(!st.isEmpty()) {
            int cur = st.top();
            find = false;

            for (int i = 0; i < num_vert; ++i) {
                if (adjMat[cur][i] == 1 && vertexAr[i].wasVisited == false) {
                    displayEdge(cur, i);
                    vertexAr[i].wasVisited = true;
                    st.push(i);
                    find = true;
                    break;
                }
            }
            if (!find)
                st.pop();
        }

    }
    public void visitReset() {
        for (int i = 0; i < num_vert; ++i)
            vertexAr[i].wasVisited = false;
    }
    public boolean reachabilityDFS(int start, int end) {
        LinkStack st = new LinkStack();
        st.push(start);
        vertexAr[start].wasVisited = true;
        boolean found = false;

        while (!st.isEmpty()) {
            int cur = st.pop();
            for (int i = 0; i <num_vert; ++i) {
                if (adjMat[cur][i] == 1 && vertexAr[i].wasVisited == false) {
                    st.push(i);
                    vertexAr[i].wasVisited = true;
                    if (i == end) return true;
                }
            }
        }
        visitReset();
        return false;
    }
    public boolean reachabilityBFS(int s, int end) {
        Queue Q = new Queue();

        vertexAr[s].wasVisited = true;
        Q.add(s, -1, -1);

        while (!Q.isEmpty()) {
            int cur = Q.delete();
            //System.out.print(vertexAr[cur].label + ", ");

            for (int i = 0; i < num_vert; ++i) {
                if (adjMat[cur][i] == 1 && vertexAr[i].wasVisited == false) {
                    Q.add(i, -1, -1);
                    vertexAr[i].wasVisited = true;
                    if (i == end) return true;
                }
            }
        }
        System.out.println();
        visitReset();
        return false;
    }
    public int [] coherent_component() {
        int [] ar = new int[num_vert];
        int freePlaces = num_vert;
        int id = 1;

        while(freePlaces > 0) {
            // szukanie wolnego miejsca
            int cur = 0;
            for (cur = 0; cur < num_vert - 1; ++cur)
                if (ar[cur] == 0)
                    break;

            LinkStack st = new LinkStack();
            vertexAr[cur].wasVisited = true;
            ar[cur] = id;
            freePlaces--;
            st.push(cur);

            while (!st.isEmpty()) {
                cur = st.pop();

                for (int i = 0; i < num_vert; ++i) {
                    if (adjMat[cur][i] == 1 && vertexAr[i].wasVisited == false) {
                        vertexAr[i].wasVisited = true;
                        ar[i] = id;
                        st.push(i);
                        freePlaces--;
                        break;
                    }
                }
            }

            id++;
        }

        return ar;

    }
    public boolean hasCycle() {

        int l = 0;


        while (l < num_vert) {
            int cur = l;
            LinkStack st = new LinkStack();
            st.push(cur);
            visitReset();
            vertexAr[cur].wasVisited = true;
            boolean found = false;


            while (!st.isEmpty()) {
                cur = st.top();
                found = false;

                for (int i = 0; i < num_vert; ++i) {
                    if (vertexAr[i].wasVisited == true && adjMat[cur][i] == 1 && st.isIn(i))
                        return true;
                    else if (adjMat[cur][i] == 1 && vertexAr[i].wasVisited == false ) {
                        st.push(i);
                        vertexAr[i].wasVisited = true;
                        found = true;
                        break;
                    }

                }
                if (!found)
                    st.pop();
            }
            l++;
        }
        return false;
    }
    public char [] topologicalForm() {
        char [] ordering = new char[num_vert];
        int n = num_vert;
        //LinkStack st = new LinkStack();


        while (true) {
            int i = findUnvisited();
            if (i == -1) break;

            LinkStack st = new LinkStack();
            vertexAr[i].wasVisited = true;
            st.push(i);

            while (!st.isEmpty()) {
                int cur = st.top();

                int next = pickUnvisited(cur);
                if (next == -1)
                    ordering[--n] = vertexAr[st.pop()].label;
                else {
                    st.push(next);
                    vertexAr[next].wasVisited = true;
                }
            }

        }
        return ordering;
    }
    private int findUnvisited() {
        for (int i = 0; i < num_vert; ++i)
            if(vertexAr[i].wasVisited == false)
                return i;
        return -1;
    }
    private int pickUnvisited(int v) {
        for (int i = 0; i < num_vert; ++i)
            if (adjMat[v][i] == 1 && vertexAr[i].wasVisited == false)
                return i;
        return -1;
    }

}




public class Source {
    public static void main(String [] args) {
        Graph theGraph = new Graph();
        /*
        theGraph.addVertex('A');
        theGraph.addVertex('B');
        theGraph.addVertex('C');
        theGraph.addVertex('D');
        theGraph.addVertex('E');
        theGraph.addEdge(0,1);
        theGraph.addEdge(1,2);
        theGraph.addEdge(0,3);
        theGraph.addEdge(3,4);
        */



    /*theGraph.addVertex('A');
    theGraph.addVertex('B');
    theGraph.addVertex('C');
    theGraph.addVertex('D');
    theGraph.addVertex('F');
    theGraph.addVertex('G');
    theGraph.addVertex('H');
    theGraph.addVertex('J');


        theGraph.addEdge(0,1);
        theGraph.addEdge(0,2);
        theGraph.addEdge(0,5);
        theGraph.addEdge(2,5);
        theGraph.addEdge(2,4);
        theGraph.addEdge(3,5);
        theGraph.addEdge(3,1);
        theGraph.addEdge(3,6);
        theGraph.addEdge(4,7);
        theGraph.addEdge(5,7);
        theGraph.addEdge(6,7);


        theGraph.DFSiter(0);


        //theGraph.DFSminTree(0);
        //for (int i = 0; i <= 7; ++i) {
            //System.out.println(theGraph.reachabilityBFS(0, 3));
           // theGraph.visitReset();
        //}
*/
        theGraph.addVertex('A');
        theGraph.addVertex('B');
        theGraph.addVertex('C');
        theGraph.addVertex('D');
        theGraph.addVertex('E');
        theGraph.addVertex('F');
        theGraph.addVertex('G');
        theGraph.addVertex('H');

/*
        theGraph.addEdge(0,4);
        theGraph.addEdge(8,4);
        theGraph.addEdge(8,7);
        theGraph.addEdge(4,7);
        theGraph.addEdge(1,6);
        theGraph.addEdge(5,6);
        theGraph.addEdge(3,2);
        theGraph.addEdge(9,2);
        theGraph.addEdge(9,3);
*/
        theGraph.addEdge(0,2);
        theGraph.addEdge(0,1);
        theGraph.addEdge(1,2);
        theGraph.addEdge(1,3);
        theGraph.addEdge(1,3);
        theGraph.addEdge(4,3);
        theGraph.addEdge(5,6);
        theGraph.addEdge(5,7);
        theGraph.addEdge(6,3);
        theGraph.addEdge(7,6);



        //theGraph.addEdge(5,3);
        char [] ar = theGraph.topologicalForm();
        for (int i = 0; i < ar.length; ++i) {
            System.out.print(ar[i] + ", ");
        }
    }
}
