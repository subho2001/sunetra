import java.util.*;

public class PrimMST {
    
    static class Edge implements Comparable<Edge> {
        int weight;
        int vertex;
        
        Edge(int weight, int vertex) {
            this.weight = weight;
            this.vertex = vertex;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public static void primMST(int V, List<List<Edge>> graph) {
        int[] minEdge = new int[V];
        int[] parent = new int[V];
        boolean[] inMST = new boolean[V];
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        Arrays.fill(minEdge, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        int startVertex = 0; // Start from an arbitrary vertex, in this case vertex 0
        minEdge[startVertex] = 0;
        pq.add(new Edge(0, startVertex));
        int totalWeight = 0;

        while (!pq.isEmpty()) {
            int u = pq.poll().vertex;

            if (inMST[u]) continue;
            inMST[u] = true;
            totalWeight += minEdge[u];

            for (Edge edge : graph.get(u)) {
                int weight = edge.weight;
                int v = edge.vertex;

                if (!inMST[v] && weight < minEdge[v]) {
                    minEdge[v] = weight;
                    pq.add(new Edge(minEdge[v], v));
                    parent[v] = u;
                }
            }
        }

        System.out.println("Edges in the Minimum Spanning Tree:");
        for (int v = 1; v < V; ++v) {
            if (parent[v] != -1) {
                System.out.println((parent[v]) + " -- " + (v) + " == " + minEdge[v]);
            }
        }
        System.out.println("Total weight of the Minimum Spanning Tree: " + totalWeight);
    }

    public static void addEdge(List<List<Edge>> graph, int src, int dest, int weight) {
        if (src < 0 || dest < 0 || src >= graph.size() || dest >= graph.size() || weight < 0) {
            throw new IllegalArgumentException("Invalid edge parameters.");
        }
        graph.get(src).add(new Edge(weight, dest)); // Directly use 0-based indexing
        graph.get(dest).add(new Edge(weight, src)); // Directly use 0-based indexing
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();
        if (V <= 0) {
            System.err.println("Number of vertices must be positive.");
            return;
        }
        
        System.out.print("Enter the number of edges: ");
        int E = scanner.nextInt();
        if (E < 0) {
            System.err.println("Number of edges cannot be negative.");
            return;
        }

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; ++i) {
            graph.add(new ArrayList<>());
        }

        System.out.println("Enter the edges (src dest weight) one per line:");
        for (int i = 0; i < E; ++i) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            try {
                addEdge(graph, src, dest, weight);
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
                return;
            }
        }

        try {
            primMST(V, graph);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}

