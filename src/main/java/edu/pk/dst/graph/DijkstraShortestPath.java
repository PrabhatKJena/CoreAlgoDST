package edu.pk.dst.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * This algorithm finds Single Source Shortest Path of a Graph using Dijkstra's Algorithm
 */
public class DijkstraShortestPath {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 7, 8);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 7, 11);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 8, 2);
        graph.addEdge(2, 5, 4);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 14);
        graph.addEdge(4, 5, 10);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 7, 1);
        graph.addEdge(6, 8, 6);
        graph.addEdge(7, 8, 7);

        Set<Vertex> spVertices = findShortestPathByDijkstra(graph, 0);
        GraphUtil.printVerticesWithWeight(spVertices);
    }

    /**
     * Finding Single Source Shortest path of a graph
     * @param graph
     * @param source vertex
     * @return set of vertices makes Shortest Path
     */
    public static Set<Vertex> findShortestPathByDijkstra(Graph<? extends Comparable> graph, int source) {
        Set<Vertex> set = new HashSet<>();

        // Initializing all vertices
        Set<? extends Vertex<? extends Comparable>> vertices = graph.getVertices();
        vertices.forEach(v -> {
            v.setParent(null);
            v.setRank(Integer.MAX_VALUE);
        });

        // Initialize Single Source
        Vertex srcV = graph.getVertexByLabel(source);
        srcV.setRank(0);

        while (!vertices.isEmpty()) {
            Vertex<? extends Comparable> v = GraphUtil.extractMin(vertices);
            set.add(v);

            /* For each adjacent vertex av of v, update the rank and parent if av can be reachable with shortest
               distance from v  */
            v.getAdjacents().forEach(av -> {
                if (av.getRank() > v.getRank() + av.getWeight()) {
                    av.setRank(v.getRank() + av.getWeight());
                    av.setParent(v);
                }
            });
        }
        return set;
    }
}
