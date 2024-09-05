import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Orange {
    int i;
    int j;
    int time;

    Orange(int i, int j, int time) {
        this.i = i;
        this.j = j;
        this.time = time;
    }
}

class Pair {
    int node;
    int distance;

    Pair(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}

class Bridge{
    int node;
    int weight;
    Bridge(int node, int weight){
        this.node = node;
        this.weight = weight;
    }
}


public class Graphs {
    public static int cycle = 0;

    public static void dfsCycle(int[] visited, int[] path, ArrayList<ArrayList<Integer>> adjList, int node) {
        if (adjList.get(node) == null)
            return;

        visited[node] = 1;
        path[node] = 1;

        for (int i = 0; i < adjList.get(node).size(); i++) {
            int currNode = adjList.get(node).get(i);
            if (visited[currNode] == 0) {
                dfsCycle(visited, path, adjList, currNode);
            } else {
                if (path[currNode] == 1) {
                    cycle = 1;
                }
            }
        }
        path[node] = 0;
    }

    public static int detectCycle(int[][] graph, int B) {
        int N = graph.length;

        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= B; i++) {
            adjList.add(i, new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            adjList.get(graph[i][0]).add(graph[i][1]);
        }

        int[] visited = new int[B + 1];
        int[] path = new int[B + 1];
        for (int i = 0; i < adjList.size(); i++) {
            dfsCycle(visited, path, adjList, i);
        }
        return cycle;
    }

    public static void dfsIsland(int row, int col, int[][] graph) {
        graph[row][col] = 2;

        int[] dx = { 0, 1, -1, 0, 1, -1, -1, 1 };
        int[] dy = { 1, 0, 0, -1, 1, -1, 1, -1 };

        for (int d = 0; d < 8; d++) {
            int i = row + dx[d];
            int j = col + dy[d];
            if (i < graph.length && j < graph[0].length && i >= 0 && j >= 0 && graph[i][j] == 1) {
                dfsIsland(i, j, graph);
            }
        }
    }

    public static int countIslands(int[][] graph) {
        int count = 0;
        int N = graph.length;
        int M = graph[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == 1) {
                    dfsIsland(i, j, graph);
                    count++;
                }
            }
        }
        return count;
    }

    public static int rottenOranges(int[][] A) {
        int N = A.length;
        int M = A[0].length;

        Queue<Orange> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (A[i][j] == 2) {
                    queue.add(new Orange(i, j, 0));
                }
            }
        }

        if (queue.isEmpty())
            return -1;

        int[] dx = { 0, 1, -1, 0 };
        int[] dy = { 1, 0, 0, -1 };
        int max = 0;
        while (!queue.isEmpty()) {
            Orange curr = queue.remove();
            max = Math.max(max, curr.time);
            for (int d = 0; d < 4; d++) {
                int x = curr.i + dx[d];
                int y = curr.j + dy[d];
                if (x >= 0 && y >= 0 && x < N && y < M && A[x][y] == 1) {
                    A[x][y] = 2;
                    queue.add(new Orange(x, y, curr.time + 1));
                }
            }

        }

        return max;
    }

    public static int[] dijkstra(int[][] graph, int V, int source) {
        int N = graph.length;
        ArrayList<ArrayList<Pair>> adjList = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            adjList.get(graph[i][0]).add(new Pair(graph[i][1], graph[i][2]));
            adjList.get(graph[i][1]).add(new Pair(graph[i][0], graph[i][2]));
        }

        int[] minDist = new int[V];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[source] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> p1.distance - p2.distance);
        pq.add(new Pair(source, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            int node = curr.node;
            for (int i = 0; i < adjList.get(node).size(); i++) {
                int neighbour = adjList.get(node).get(i).node;
                int neighbourDistance = adjList.get(node).get(i).distance;
                if (minDist[node] + neighbourDistance < minDist[neighbour]) {
                    minDist[neighbour] = minDist[node] + neighbourDistance;
                    pq.add(new Pair(neighbour, minDist[neighbour]));
                }
            }
        }

        for (int i = 0; i < minDist.length; i++) {
            if (minDist[i] == Integer.MAX_VALUE) {
                minDist[i] = -1;
            }
        }

        return minDist;

    }

    public static int primsAlgo(int[][] A, int B){
        int N = A.length;
        ArrayList<ArrayList<Bridge>> adjList = new ArrayList<>();

        for(int i=0; i<=B; i++){
            adjList.add(new ArrayList<>());
        }

        for(int i=0; i<N; i++){
            adjList.get(A[i][0]).add(new Bridge(A[i][1], A[i][2]));
            adjList.get(A[i][1]).add(new Bridge(A[i][0], A[i][2]));
        }

        boolean[] visited = new boolean[B+1];
        
        PriorityQueue<Bridge> pq = new PriorityQueue<>((b1,b2)->b1.weight - b2.weight);

        visited[1]= true;
        for(int i=0; i<adjList.get(1).size(); i++){
            pq.add(adjList.get(1).get(i));
        }
        int ans =0;
        while(!pq.isEmpty()){
            Bridge curr = pq.remove();
            int node = curr.node;
            int weight = curr.weight;
            

            if(!visited[node]){
                visited[node]=true;
                ans+= weight;
                for(int i=0; i<adjList.get(node).size(); i++){
                    Bridge neighbour = adjList.get(node).get(i);
                    int dest = neighbour.node;
                    if(!visited[dest]){
                        pq.add(neighbour);
                    }  
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        /*
         * 1. Cycle in Graph:
         * Given an directed graph having A nodes. A matrix B of size M x 2 is given
         * which represents the M edges such that there is a edge directed from node
         * B{i}{0} to node B{i}{1}.
         * 
         * Find whether the graph contains a cycle or not, return 1 if cycle is present
         * else return 0.
         */

        int[][] graph1 = { { 1, 2 }, { 4, 1 }, { 2, 4 }, { 3, 4 }, { 5, 2 }, { 1, 3 } };
        // int[][] graph1={{1,2},{2,3},{3,4},{4,5}};
        int noOfPoints = 5;
        int isCycle = detectCycle(graph1, noOfPoints);
        if (isCycle == 1) {
            System.out.println("Cycle is present in graph");
        } else {
            System.out.println("Cycle is not present in graph");
        }

        /*
         * 2. Given a matrix of integers A of size N x M consisting of 0 and 1. A group
         * of
         * connected 1's forms an island. From a cell (i, j) such that A[i][j] = 1 you
         * can visit any cell that shares a corner with (i, j) and value in that cell is
         * 1.
         * 
         * More formally, from any cell (i, j) if A[i][j] = 1 you can visit:
         * (i-1, j) if (i-1, j) is inside the matrix and A[i-1][j] = 1.
         * (i, j-1) if (i, j-1) is inside the matrix and A[i][j-1] = 1.
         * (i+1, j) if (i+1, j) is inside the matrix and A[i+1][j] = 1.
         * (i, j+1) if (i, j+1) is inside the matrix and A[i][j+1] = 1.
         * (i-1, j-1) if (i-1, j-1) is inside the matrix and A[i-1][j-1] = 1.
         * (i+1, j+1) if (i+1, j+1) is inside the matrix and A[i+1][j+1] = 1.
         * (i-1, j+1) if (i-1, j+1) is inside the matrix and A[i-1][j+1] = 1.
         * (i+1, j-1) if (i+1, j-1) is inside the matrix and A[i+1][j-1] = 1.
         * Return the number of islands.
         * 
         * NOTE: Rows are numbered from top to bottom and columns are numbered from left
         * to right.
         */
        int[][] islands = { { 1, 1, 0, 0, 0 }, { 0, 1, 0, 0, 0 }, { 1, 0, 0, 1, 1 }, { 0, 0, 0, 0, 0 },
                { 1, 0, 1, 0, 1 } };
        int nosOfIsland = countIslands(islands);
        System.out.println("Number of islands are: " + nosOfIsland);

        /*
         * 3. Given a matrix of integers A of size N x M consisting of 0, 1 or 2.
         * Each cell can have three values:
         * The value 0 representing an empty cell.
         * The value 1 representing a fresh orange.
         * The value 2 representing a rotten orange.
         * Every minute, any fresh orange that is adjacent (Left, Right, Top, or Bottom)
         * to a rotten orange becomes rotten. Return the minimum number of minutes that
         * must elapse until no cell has a fresh orange. If this is impossible, return
         * -1 instead.
         * Note: Your solution will run on multiple test cases. If you are using global
         * variables, make sure to clear them.
         */

        int[][] basket = { { 2, 1, 1 }, { 1, 1, 0 }, { 0, 1, 1 } };
        int time = rottenOranges(basket);
        System.out
                .println("The minimum number of minutes that must elapse until no cell has a fresh orange is:" + time);

        /*
         * 4. Dijkstra's Algo: Given a weighted undirected graph having A nodes and M
         * weighted edges, and a
         * source node C.
         * 
         * You have to find an integer array D of size A such that:
         * 
         * D[i]: Shortest distance from the C node to node i.
         * If node i is not reachable from C then -1.
         * Note:
         * 
         * There are no self-loops in the graph.
         * There are no multiple edges between two pairs of vertices.
         * The graph may or may not be connected.
         * Nodes are numbered from 0 to A-1.
         * Your solution will run on multiple test cases. If you are using global
         * variables, make sure to clear them.
         */

        int[][] graph = { { 0, 4, 9 }, { 3, 4, 6 }, { 1, 2, 1 }, { 2, 5, 1 }, { 2, 4, 5 }, { 0, 3, 7 }, { 0, 1, 1 },
                { 4, 5, 7 }, { 0, 5, 1 } };
        int noOfNodes = 6;
        int source = 4;
        int[] shortestPath = dijkstra(graph, noOfNodes, source);
        System.out.println("Shorted path to reach from source is: " + Arrays.toString(shortestPath));

        /*
         * 5. MST/Prim's Algo:There are A islands and there are M bridges connecting
         * them. Each bridge has some cost attached to it.
         * We need to find bridges with minimal cost such that all islands are
         * connected.
         * It is guaranteed that input data will contain at least one possible scenario
         * in which all islands are connected with each other.
         */
        int[][] graph2 = { { 1, 2, 1 },
                { 2, 3, 4 },
                { 1, 4, 3 },
                { 4, 3, 2 },
                { 1, 3, 10 }
        };

        int mst = primsAlgo(graph2, 4);
        System.out.println("Bridges with minimal cost such that all islands are connected is: "+ mst);
    }
}
