package edu.pk.dst.graph;

import java.util.Set;

public class GraphUtil {
    /**
     * Linking x to y
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
     Printing edges with weight like :   source---weight--->dest
     */
    public static void printVerticesWithWeight(Set<Vertex> vertices) {
        for (Vertex vertex : vertices) {
            Vertex parent = vertex.getParent();
            if (parent != null) {
                System.out.println(parent.getLabel() + "---"+parent.getAdjVertexByLabel(vertex.getLabel()).getWeight()+"--->" + vertex.getLabel());
            }
        }
    }
}
