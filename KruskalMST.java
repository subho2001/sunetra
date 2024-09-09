import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Edge {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

public class KruskalMST {

    public static List<Edge> createGraph(int V) {
        return new ArrayList<>();
    }

    public static int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }

    public static void unionSets(int[] parent, int[] rank, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }

    public static void kruskalMST(int V, List<Edge> graph) {
        List<Edge> result = new ArrayList<>();
        int totalWeight = 0;

        Collections.sort(graph, Comparator.comparingInt(edge -> edge.weight));

        int[] parent = new int[V];
        int[] rank = new int[V];

        for (int v = 0; v < V; ++v) {
            parent[v] = v;
            rank[v] = 0; // Initialize rank to 0
        }

        for (Edge edge : graph) {
            int setU = find(parent, edge.src);
            int setV = find(parent, edge.dest);

            if (setU != setV) {
                result.add(edge);
                totalWeight += edge.weight;
                unionSets(parent, rank, setU, setV);
            }
        }

        System.out.println("Edges in the Minimum Spanning Tree:");
        for (Edge edge : result) {
            System.out.println((edge.src + 1) + " -- " + (edge.dest + 1) + " == " + edge.weight);
        }
        System.out.println("Total weight of the Minimum Spanning Tree: " + totalWeight);
    }

    public static void addEdge(List<Edge> graph, int src, int dest, int weight) {
        graph.add(new Edge(src, dest, weight));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V, E;

        try {
            System.out.print("Enter the number of vertices: ");
            V = sc.nextInt();
            System.out.print("Enter the number of edges: ");
            E = sc.nextInt();

            if (V <= 0 || E < 0) {
                System.out.println("Number of vertices and edges must be positive.");
                return;
            }

            List<Edge> graph = createGraph(V);

            System.out.println("Enter the edges (src dest weight) one per line:");
            for (int i = 0; i < E; ++i) {
                int src, dest, weight;
                src = sc.nextInt();
                dest = sc.nextInt();
                weight = sc.nextInt();

                if (src < 0 || src >= V || dest < 0 || dest >= V) {
                    System.out.println("Invalid edge: vertices must be within the valid range.");
                    return;
                }

                addEdge(graph, src, dest, weight);
            }

            kruskalMST(V, graph);
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter integers for the number of vertices, edges, and edge details.");
        } finally {
            sc.close();
        }
    }
}

