import java.util.*;

public class BFS {
    private int V; // number of vertices
    private List<Integer>[] adj; // adjacency lists

    // Constructor
    @SuppressWarnings("unchecked")
    BFS(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj[v - 1].add(w - 1);
        adj[w - 1].add(v - 1); // For undirected graph, add this line to add edge in both directions
    }

    // Enum for vertex colors
    private enum Color {
        WHITE, GRAY, BLACK
    }

    // Prints BFS traversal from a given source s and BFS spanning tree
    void bfs(int s) {
        // Mark all the vertices as not visited (WHITE)
        Color[] color = new Color[V];
        int[] d = new int[V];
        int[] p = new int[V];
        for (int i = 0; i < V; i++) {
            color[i] = Color.WHITE;
            d[i] = Integer.MAX_VALUE;
            p[i] = -1;
        }

        // Initialize the source vertex
        color[s - 1] = Color.GRAY;
        d[s - 1] = 0;
        p[s - 1] = -1;

        // Create a queue for BFS
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s - 1);

        // List to store BFS spanning tree edges
        List<String> spanningTreeEdges = new ArrayList<>();

        while (!queue.isEmpty()) {
            // Dequeue a vertex from queue
            int u = queue.poll();

            // Get all adjacent vertices of the dequeued vertex u
            // If an adjacent has not been visited, then mark it visited (GRAY)
            // and enqueue it
            for (int v : adj[u]) {
                if (color[v] == Color.WHITE) {
                    color[v] = Color.GRAY;
                    d[v] = d[u] + 1;
                    p[v] = u;
                    queue.add(v);

                    // Add the edge (u, v) to the spanning tree
                    spanningTreeEdges.add((u + 1) + " -> " + (v + 1));
                }
            }

            // Mark the dequeued vertex as processed (BLACK)
            color[u] = Color.BLACK;
        }

        // Print the BFS spanning tree edges
        System.out.println("BFS Spanning Tree Edges:");
        for (String edge : spanningTreeEdges) {
            System.out.println(edge);
        }

        // Print the BFS traversal
        System.out.println("\nVertex\tDistance from Source\tParent");
        for (int i = 0; i < V; i++) {
            System.out.println((i + 1) + "\t\t" + (d[i] == Integer.MAX_VALUE ? "INF" : d[i]) + "\t\t" + (p[i] != -1 ? (p[i] + 1) : "None"));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("********************");
        System.out.println("BFS Implementation: ");
        System.out.println("********************");

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();

        BFS g = new BFS(V);

        System.out.print("Enter the number of edges: ");
        int E = scanner.nextInt();

        System.out.println("Enter the edges (format: u v for edge u->v): ");
        for (int i = 0; i < E; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            g.addEdge(u, v);
        }

        System.out.print("Enter the starting vertex for BFS: ");
        int startVertex = scanner.nextInt();

        System.out.println("Following is Breadth First Traversal " +
                "(starting from vertex " + startVertex + ")\nand BFS Spanning Tree:");

        g.bfs(startVertex);

        scanner.close();
    }
}

