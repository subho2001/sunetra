import java.util.*;

public class FordFulkerson {

    private static final int INF = Integer.MAX_VALUE;

    // Helper function to print the path found
    private static void printPath(int[] parent, int v) {
        if (parent[v] == -1) {
            System.out.print(v);
            return;
        }
        printPath(parent, parent[v]);
        System.out.print(" -> " + v);
    }

    // Function to print the residual graph after each iteration
    private static void printResidualGraph(int[][] rGraph) {
        int V = rGraph.length;
        System.out.println("Residual Graph:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (rGraph[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(rGraph[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean bfs(int[][] rGraph, int s, int t, int[] parent) {
        int V = rGraph.length;
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = true;
        parent[s] = -1;

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int v = 0; v < V; v++) {
                if (!visited[v] && rGraph[u][v] > 0) {
                    if (v == t) {
                        parent[v] = u;
                        return true;
                    }
                    q.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        return false;
    }

    private static int fordFulkerson(int[][] graph, int s, int t) {
        int V = graph.length;
        int[][] rGraph = new int[V][V];
        for (int i = 0; i < V; i++) {
            System.arraycopy(graph[i], 0, rGraph[i], 0, V);
        }
        int[] parent = new int[V];
        int maxFlow = 0;
        int iteration = 1;

        while (bfs(rGraph, s, t, parent)) {
            // Find the maximum flow through the path found by BFS
            int pathFlow = INF;
            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
            }

            // Update residual capacities of the edges and reverse edges along the path
            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                rGraph[u][v] -= pathFlow;
                rGraph[v][u] += pathFlow;
            }

            maxFlow += pathFlow;

            // Print the path and flow for this iteration
            System.out.print("Iteration " + iteration++ + ": Path: ");
            printPath(parent, t);
            System.out.println(" | Flow: " + pathFlow);

            // Print the residual graph after the iteration
            printResidualGraph(rGraph);
        }

        return maxFlow;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();

        if (V <= 0) {
            System.err.println("Number of vertices must be positive.");
            return;
        }

        int[][] graph = new int[V][V];
        System.out.println("Enter the adjacency matrix (row by row):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                String input = scanner.next();
                if (input.equals("INF")) {
                    graph[i][j] = INF;
                } else {
                    graph[i][j] = Integer.parseInt(input);
                }
            }
        }

        System.out.print("Enter the source vertex: ");
        int source = scanner.nextInt();
        System.out.print("Enter the sink vertex: ");
        int sink = scanner.nextInt();

        if (source < 0 || source >= V || sink < 0 || sink >= V || source == sink) {
            System.err.println("Invalid source or sink vertex.");
            return;
        }

        System.out.println("The maximum possible flow is " + fordFulkerson(graph, source, sink));
    }
}

