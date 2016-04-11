package edu.pk.dst.graph;

import edu.pk.dst.graph.adt.Graph;
import edu.pk.dst.graph.adt.Vertex;
import edu.pk.dst.graph.util.GraphUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * This algorithm finds Single Source Shortest Path of a Graph using Dijkstra's Algorithm.
 * Dijkstra’s algorithm is a Greedy algorithm and time complexity is O(VLogV) (with the use of Fibonacci heap).
 * Dijkstra does n’t work for Graphs with negative weight
 */
public class DijkstraShortestPath {
    public static void main(String[] args) {
        Graph<Integer> graph = GraphUtil.createSampleGraph();
        Set<Vertex<? extends Comparable>> spVertices = findShortestPathByDijkstra(graph, 0);
        GraphUtil.printVerticesWithWeight(spVertices);
    }

    /**
     * Finding Single Source Shortest path of a graph
     * @param graph
     * @param source vertex
     * @return set of vertices makes Shortest Path
     */
    public static Set<Vertex<? extends Comparable>> findShortestPathByDijkstra(Graph<? extends Comparable> graph, int source) {
        Set<Vertex<? extends Comparable>> set = new HashSet<>();

        // Initializing all vertices and Single Source
        GraphUtil.initializeSingleSource(graph, graph.getVertexByLabel(source));

        Set<? extends Vertex<? extends Comparable>> vSet = new HashSet<>(graph.getVertices());
        while (!vSet.isEmpty()) {
            Vertex<? extends Comparable> v = GraphUtil.extractMin(vSet);
            set.add(v);

            /* For each adjacent vertex av of v, update the rank and parent if av can be reachable
               with shortest distance from v */
            v.getAdjacents().forEach(av -> {
                GraphUtil.relax(v, av.getVertex(), av.getWeight());
            });
        }
        return set;
    }
}
