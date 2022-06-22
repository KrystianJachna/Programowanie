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
        adjMat[end][start] = 1;
        // dla grafu niezorientowanego
    }

    public void displayVertex(int v) {
        System.out.println(vertexAr[v].label);
    }

    public void displayEdge(int u, int v) {
        System.out.println("( " + vertexAr[u].label + ", " + vertexAr[v].label + " )" );
        // moze jeszcze sprawdzenie czy taka krawedz jest?
    }

    public void Bfs(int s) {
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

    private void visitReset() {
        for (int i = 0; i < num_vert; ++i)
            vertexAr[i].wasVisited = false;
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

        theGraph.addVertex('A');
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
        theGraph.addEdge(3,6);
        theGraph.addEdge(6,7);
        theGraph.addEdge(4,7);
        theGraph.addEdge(5,7);





        System.out.print("Odwiedzone wierzcholki: ");
        theGraph.findingShortestPathPtoP(1, 7);
    }
}
