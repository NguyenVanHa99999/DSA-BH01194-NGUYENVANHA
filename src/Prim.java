import java.util.*;

public class Prim {

    static class Edge {
        int target, weight;
        public Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public static void prim(int vertices, List<List<Edge>> graph) {
        boolean[] inMST = new boolean[vertices];
        int[] parent = new int[vertices];
        int[] key = new int[vertices];
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(i -> key[i]));
        pq.add(0);

        while (!pq.isEmpty()) {
            int u = pq.poll();
            inMST[u] = true;

            for (Edge edge : graph.get(u)) {
                int v = edge.target;
                if (!inMST[v] && edge.weight < key[v]) {
                    key[v] = edge.weight;
                    parent[v] = u;
                    pq.add(v);
                }
            }
        }

        System.out.println("Minimum Spanning Tree:");
        for (int i = 1; i < vertices; i++) {
            System.out.println("Edge: " + parent[i] + " - " + i + " with weight " + key[i]);
        }
    }

    public static void main(String[] args) {
        int vertices = 4;
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(new Edge(1, 2));
        graph.get(0).add(new Edge(2, 3));
        graph.get(1).add(new Edge(3, 1));
        graph.get(2).add(new Edge(3, 2));

        prim(vertices, graph);
    }
}
