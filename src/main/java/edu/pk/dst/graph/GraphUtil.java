package edu.pk.dst.graph;

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
}
