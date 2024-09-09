import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Graph {
    private final List<List<Edge>> adjacencyList;
    private final int V;

    public Graph(int vertices) {
        this.V = vertices;
        this.adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest, int weight) {
        adjacencyList.get(src).add(new Edge(dest, weight));
        adjacencyList.get(dest).add(new Edge(src, weight)); // For undirected graph
    }

    public void dijkstra(int start) {
        int[] dist = new int[V];
        boolean[] processed = new boolean[V];
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;

        for (int count = 0; count < V - 1; count++) {
            // Find the vertex with the minimum distance
            int u = findMinDistanceVertex(dist, processed);
            processed[u] = true;

            // Update distances of adjacent vertices
            for (Edge edge : adjacencyList.get(u)) {
                int v = edge.dest;
                int weight = edge.weight;
                if (!processed[v] && dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }

        // Print the shortest distances from the start vertex
        System.out.println("Vertex \t Distance from Source");
        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println(i + " \t INF");
            } else {
                System.out.println(i + " \t " + dist[i]);
            }
        }
    }

    private int findMinDistanceVertex(int[] dist, boolean[] processed) {
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < V; i++) {
            if (!processed[i] && dist[i] < minDistance) {
                minDistance = dist[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    private static class Edge {
        int dest;
        int weight;

        Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }
}

public class DijkstraJavaNoPQ {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();
        System.out.print("Enter the number of edges: ");
        int E = scanner.nextInt();

        Graph graph = new Graph(V);

        System.out.println("Enter the edges and their weights (format: src dest weight):");
        for (int i = 0; i < E; i++) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();

            if (src < 0 || src >= V || dest < 0 || dest >= V || weight < 0) {
                System.err.println("Invalid edge input");
                return;
            }
            graph.addEdge(src, dest, weight);
        }

        System.out.print("Enter the start vertex: ");
        int start = scanner.nextInt();
        if (start < 0 || start >= V) {
            System.err.println("Invalid start vertex");
            return;
        }

        graph.dijkstra(start);
    }
}

