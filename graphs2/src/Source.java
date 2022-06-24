import java.util.Stack;
class Edge {
    public int cost;
    public boolean used;

    public Edge(int cost, boolean used) {
        this.cost = cost;
        this.used = false;
    }
}
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
    public LinkStack(LinkStack st) {
        Link we = st.top;
        this.top = we;

        while (we != null) {
            this.pushCP(we.prev);
        }
    }

    public boolean isEmpty() { return (top == null); }

    public void pushCP(Link x) {
        Link prevtop = top;
        top = x;
        top.prev = prevtop;
    }

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
    public queueVertex deleteRetQueueV() {
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
class index_dist{
    public int index;
    public double distance;

    public index_dist(int in, double dis) {
        index = in;
        distance = dis;
    }
}
class prev_dist{
    public int prev;
    public double dist;

    public prev_dist(int pr, double dis) {
        prev = pr;
        dist = dis;
    }
}
class heap{
    private index_dist [] ar;
    int MAX;
    int cur = -1;

    public heap(int m) {
        MAX = m;
        ar = new index_dist[MAX];
    }
    public void add(int id, double dis) {
        if (cur + 1 >= MAX) return;

        ar[++cur] = new index_dist(id, dis);
        upHeap(cur);
    }
    public index_dist delete() {
        index_dist toRet = ar[0];
        ar[0] = ar[cur--];
        return toRet;
    }
    private void upHeap(int id) {
        int i = (id - 1) / 2;
        index_dist tmp = ar[id];

        while (id > 0 && tmp.distance < ar[i].distance) {
            ar[id] = ar[i];
            id = i;
            i = (i - 1) / 2;
        }
        ar[id] = tmp;
    }
    private void downHeap(int k) {
        int j;
        index_dist tmp = ar[k];

        while (k < (cur + 1) / 2) {
            j = 2 * k + 1;
            if (j < cur && ar[j+1].distance < ar[j].distance)   ++j;

            if (tmp.distance <= ar[j].distance) break;

            ar[k] = ar[j];
            k = j;
        }
        ar[k] = tmp;
    }
    public boolean isEmpty() {
        return (cur == -1);
    }

}


class Graph{
    private int MAX_VERTS = 20;
    private Vertex [] vertexAr;
    private Edge adjMat[][];
    private int num_vert;
    private int num_edges;

    public Graph() {
        MAX_VERTS = 20;
        vertexAr = new Vertex[MAX_VERTS];
        adjMat = new Edge[MAX_VERTS][MAX_VERTS];
        num_vert = 0;
        num_edges =0;

        for (int i = 0; i < MAX_VERTS; ++i)
            for(int j = 0; j < MAX_VERTS; ++j)
                adjMat[i][j] = null;

        for (int i = 0; i < MAX_VERTS; ++i)
            vertexAr[i] = null;
    }
    public void addVertex(char label) {
        vertexAr[num_vert++] = new Vertex(label);
    }
    public void addEdge(int start, int end, int weight) {
        adjMat[start][end] = new Edge(weight, false);
        num_edges++;      // dla niezorientowanego

    }
    public void displayVertex(int v) {
        System.out.println(vertexAr[v].label);
    }
    public void displayEdge(int u, int v) {
        System.out.println("( " + vertexAr[u].label + ", " + vertexAr[v].label + " )" );
    }

    public double [] bfAlg(int startingNode) {
        int E = num_edges;
        int V = num_vert;
        int S = startingNode;
        double [] D = new double [V];

        for (int i = 0; i < V; ++i)
            D[i] = Double.POSITIVE_INFINITY;
        D[S] = 0;

        Queue q = new Queue();

        for (int i = 0; i < V - 1; ++i) {
            q.add(startingNode, 0, 0);

            while (!q.isEmpty()) {
                int cur = q.delete();
                for (int j = 0; j < num_vert; ++j) {
                    if (adjMat[cur][j] != null && adjMat[cur][j].used == false) {
                        q.add(j, 0, 0);
                        adjMat[cur][j].used = true;
                        if (D[cur] + adjMat[cur][j].cost < D[j])
                            D[j] = adjMat[cur][j].cost + D[cur];
                    }
                }
             }

            for (int x = 0; x < num_vert; ++x) {
                for (int z = 0; z < num_vert; ++z) {
                    if (adjMat[x][z] != null)
                        adjMat[x][z].used = false;
                }
            }
        }

        for (int i = 0; i < V - 1; ++i) {
            q.add(startingNode, 0, 0);

            while (!q.isEmpty()) {
                int cur = q.delete();
                for (int j = 0; j < num_vert; ++j) {
                    if (adjMat[cur][j] != null && adjMat[cur][j].used == false) {
                        q.add(j, 0, 0);
                        adjMat[cur][j].used = true;
                        if (D[cur] + adjMat[cur][j].cost < D[j])
                            D[j] = Double.NEGATIVE_INFINITY;
                    }
                }
            }

            for (int x = 0; x < num_vert; ++x) {
                for (int z = 0; z < num_vert; ++z) {
                    if (adjMat[x][z] != null)
                        adjMat[x][z].used = false;
                }
            }
        }
        return D;
    }



    public void Dijkstra(int s, int e) {
        prev_dist [] dist = new prev_dist[num_vert];
        heap h = new heap(num_edges);

        h.add(s, 0);
        for (int i = 0; i < num_vert; ++i)
            dist[i] = new prev_dist(-1, Double.POSITIVE_INFINITY);

        dist[s].dist = 0;

        while(!h.isEmpty()) {
            index_dist cur = h.delete();
            for (int i = 0; i < num_vert; ++i) {
                if (adjMat[cur.index][i] != null && adjMat[cur.index][i].used == false) {
                    if (dist[cur.index].dist + adjMat[cur.index][i].cost < dist[i].dist) {
                        dist[i].dist = dist[cur.index].dist + adjMat[cur.index][i].cost;
                        dist[i].prev = cur.index;
                    }
                    h.add(i, adjMat[cur.index][i].cost + cur.distance);
                    adjMat[cur.index][i].used = true;
                }
            }
        }
        int i = e;
        while (i != s) {
            System.out.print(i + ", ");
            i =dist[i].prev;
        }
        System.out.print(i + ", ");


    }


    public double [][] Warshal() {
        double [][] dp = new double[num_vert][num_vert];
        int [][] next = new int[num_vert][num_vert];

        setup(dp, next);

        for (int i = 0; i < num_vert; ++i) {
            for (int j = 0; j < num_vert; ++j) {
                for (int k = 0; k < num_vert; ++k) {
                    if (dp[i][k] + dp[k][j] < dp[i][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];   // -inf
                        next[i][j] = next[i][k];          // -i
                    }
                }
            }
        }// jak cos to jeszcze raz tylko zmieniac
        return dp;
    }

    private void setup(double [][] dp, int [][] next) {
        for (int i = 0; i < num_vert; ++i) {
            for (int j = 0; j < num_vert; ++j) {
                if (adjMat[i][j] == null)
                    dp[i][j] = Double.POSITIVE_INFINITY;
                else
                    dp[i][j] = adjMat[i][j].cost;
                if(adjMat[i][j] != null)
                    next[i][j] = j;
            }
         }
    }
}




public class Source {
    public static void main(String [] args) {
        Graph theGraph = new Graph();

        theGraph.addVertex('A');
        theGraph.addVertex('B');
        theGraph.addVertex('C');
        theGraph.addVertex('D');
        theGraph.addVertex('E');


        theGraph.addEdge(0, 1, 4);
        theGraph.addEdge(0, 2, 1);
        theGraph.addEdge(2, 3, 5);
        theGraph.addEdge(1, 3, 1);
        theGraph.addEdge(2, 1, 2);
        theGraph.addEdge(3, 4, 3);
        theGraph.addEdge(2, 4, 7);
       // theGraph.Dijkstra(0, 4);
        double[][] l = theGraph.Warshal();
        System.out.print(l[0][0]);

    }
}