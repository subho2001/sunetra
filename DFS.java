import java.util.*;

public class DFS {
    private int V; // number of vertices
    private List<Integer>[] adj; // adjacency lists

    // Constructor
    @SuppressWarnings("unchecked")
    DFS(int v) {
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

    // Recursive DFS utility function
    void dfsVisit(int u, String[] color, int[] pre, int[] post, int[] p, List<String> spanningTreeEdges, int[] time) {
        color[u] = "GRAY";
        time[0]++;
        pre[u] = time[0];

        for (int v : adj[u]) {
            if (color[v].equals("WHITE")) {
                p[v] = u;
                spanningTreeEdges.add((u + 1) + " -> " + (v + 1));
                dfsVisit(v, color, pre, post, p, spanningTreeEdges, time);
            }
        }

        color[u] = "BLACK";
        time[0]++;
        post[u] = time[0];
    }

    // Function to perform DFS traversal
    void dfs() {
        // Mark all the vertices as not visited (WHITE)
        String[] color = new String[V];
        int[] pre = new int[V]; // Discovery time of nodes
        int[] post = new int[V]; // Finish time of nodes
        int[] p = new int[V]; // Parent of nodes
        int[] time = {0}; // Timer for discovery and finish times

        for (int i = 0; i < V; i++) {
            color[i] = "WHITE";
            pre[i] = Integer.MAX_VALUE;
            post[i] = Integer.MAX_VALUE;
            p[i] = -1;
        }

        List<String> spanningTreeEdges = new ArrayList<>();

        // Perform DFS for each vertex
        for (int u = 0; u < V; u++) {
            if (color[u].equals("WHITE")) {
                dfsVisit(u, color, pre, post, p, spanningTreeEdges, time);
            }
        }

        // Print the DFS spanning tree edges
        System.out.println("DFS Spanning Tree Edges:");
        for (String edge : spanningTreeEdges) {
            System.out.println(edge);
        }

        // Print the DFS traversal information
        System.out.println("\nVertex\tDiscovery Time\tFinish Time\tParent");
        for (int i = 0; i < V; i++) {
            System.out.println((i + 1) + "\t\t" + pre[i] + "\t\t" + post[i] + "\t\t" + (p[i] != -1 ? (p[i] + 1) : "None"));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("********************");
        System.out.println("DFS Implementation: ");
        System.out.println("********************");

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();

        DFS g = new DFS(V);

        System.out.print("Enter the number of edges: ");
        int E = scanner.nextInt();

        System.out.println("Enter the edges (format: u v for edge u->v): ");
        for (int i = 0; i < E; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            g.addEdge(u, v);
        }

        System.out.println("Following is Depth First Traversal " +
                "and DFS Spanning Tree:");

        g.dfs();

        scanner.close();
    }
}
