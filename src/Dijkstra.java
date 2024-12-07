import java.util.*;

public class Dijkstra {

    static class Edge {
        int target, weight;
        public Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public static void dijkstra(int vertices, List<List<Edge>> graph, int source) {
        int[] distances = new int[vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(i -> distances[i]));
        pq.add(source);

        while (!pq.isEmpty()) {
            int u = pq.poll();

            for (Edge edge : graph.get(u)) {
                int v = edge.target;
                int newDist = distances[u] + edge.weight;
                if (newDist < distances[v]) {
                    distances[v] = newDist;
                    pq.add(v);
                }
            }
        }

        System.out.println("Shortest distances from node " + source + ":");
        for (int i = 0; i < vertices; i++) {
            System.out.println("To " + i + " : " + distances[i]);
        }
    }

    public static void main(String[] args) {
        int vertices = 5;
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(new Edge(1, 10));
        graph.get(0).add(new Edge(2, 3));
        graph.get(1).add(new Edge(3, 15));
        graph.get(1).add(new Edge(4, 2));
        graph.get(2).add(new Edge(3, 6));
        graph.get(3).add(new Edge(4, 1));

        dijkstra(vertices, graph, 0);
    }
}
