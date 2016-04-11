package edu.pk.dst.graph.util;

import edu.pk.dst.graph.adt.Graph;
import edu.pk.dst.graph.adt.Vertex;

import java.util.Set;

public class GraphUtil {
    /**
     * Linking x to y
     *
     * @param x
     * @param y
     */
    public static void link(Vertex x, Vertex y) {
        if (x.getRank() > y.getRank())
            y.setParent(x);
        else {
            x.setParent(y);
            if (x.getRank() == y.getRank())
                y.setRank(y.getRank() + 1);
        }
    }

    /**
     * Finding root of the subset
     *
     * @param graph
     * @param src
     * @return Vertex
     */
    public static Vertex<? extends Comparable> findSet(Graph<? extends Comparable> graph, Comparable src) {
        Vertex<? extends Comparable> vertex = graph.getVertexByLabel(src);
        if (vertex.getParent().getLabel() != vertex.getLabel()) {
            return findSet(graph, vertex.getParent().getLabel());
        }
        return vertex.getParent();
    }

    /**
     * Join x and y subsets
     *
     * @param graph
     * @param x
     * @param y
     */
    public static void union(Graph<? extends Comparable> graph, Comparable x, Comparable y) {
        link(findSet(graph, x), findSet(graph, y));
    }

    /**
     * Finding Vertex with minimum rank using stream API
     */
    public static Vertex<? extends Comparable> extractMin(Set<? extends Vertex> vertices) {
        Vertex min = vertices.stream().min((a, b) -> {
            return Integer.valueOf(a.getRank()).compareTo(b.getRank());
        }).orElse(null);
        if (min != null) {
            vertices.remove(min);
        }
        return min;
    }

    /**
     * Printing edges with weight like :   source---weight--->dest
     *
     * @param vertices
     */
    public static void printVerticesWithWeight(Set<? extends Vertex<? extends Comparable>> vertices) {
        for (Vertex vertex : vertices) {
            Vertex parent = vertex.getParent();
            if (parent != null) {
                System.out.println(parent.getLabel() + "---" + parent.getAdjVertexByLabel(vertex.getLabel()).getWeight() +
                        "--->" + vertex.getLabel());
            }
        }
    }

    /**
     * Initializing all vertices and Single Source
     *
     * @param graph
     */
    public static void initializeSingleSource(Graph graph, Vertex s) {
        Set<? extends Vertex<? extends Comparable>> vertices = graph.getVertices();
        vertices.forEach(v -> {
            v.setParent(null);
            v.setRank(Integer.MAX_VALUE / 2);   // Since(MAX_VALUE + int weight)  leads to -ve number,
                                                // So setting infinite as MAX_VALUE/2 and assumed int weight < MAX_VALUe/2
        });
        s.setRank(0);
    }

    /**
     * Updates the rank and parent of the target vertex
     *
     * @param u      source vertex
     * @param v      target vertex
     * @param weight
     */
    public static void relax(Vertex u, Vertex v, int weight) {
        if (v.getRank() > u.getRank() + weight) {
            v.setRank(u.getRank() + weight);
            v.setParent(u);
        }
    }

    public static Graph<Integer> createSampleGraph() {
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

        return graph;
    }

}
