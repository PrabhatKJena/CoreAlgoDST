package edu.pk.dst.graph;

import java.util.*;

/**
 * This is a Graph DST, which supports following operations :
 * 1. Add Edge -
 * a. Connect the vertices either by creating new vertices or re-using the existing vertices
 * b. Assign the weight to the edge if supplied
 * b. Update the adjacency list for the two vertices of the edge
 * 2. Provides the vertex by label if exists
 * 3. Print the graph in Adjacency List form
 *
 *  @param <E>  is a Comparable type [i.e  Integer, Character, Double etc]
 */
public class Graph<E extends Comparable> {
    private List<Edge> edges;
    private Set<Vertex<E>> vertices;

    public Graph() {
        edges = new ArrayList<>();
        vertices = new TreeSet<Vertex<E>>(new Comparator<Vertex<E>>() {
            @Override
            public int compare(Vertex<E> o1, Vertex<E> o2) {
                return o1.getLabel().compareTo(o2.getLabel());
            }
        });
    }

    // Adding a new edge into graph with weight
    public void addEdge(E src, E dst, int w) {
        Vertex<E> srcV = createVertex(src);
        Vertex destV = createVertex(dst);
        createEdge(srcV, destV, w);
        updateAdjacent(srcV, destV, w);
    }

    private void createEdge(Vertex srcV, Vertex destV, int w) {
        Edge edge = new Edge(srcV, destV, w);
        if (!edges.contains(edge))
            edges.add(edge);
    }

    // Adding a new edge into graph with default wight 0
    public void addEdge(E src, E dst) {
        addEdge(src, dst, 0);
    }

    // Updating adjacency of the vertices
    private void updateAdjacent(Vertex srcV, Vertex destV, int w) {
        srcV.addAdjacent(new AdjVertex(destV, w));
        destV.addAdjacent(new AdjVertex(srcV, w));
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getVCount() {
        return vertices.size();
    }

    public int getECount() {
        return edges.size();
    }

    public Set<Vertex<E>> getVertices() {
        return vertices;
    }

    // Finding if Vertex already create for the label
    public Vertex getVertexByLabel(Comparable label) {
        Optional<Vertex<E>> first = vertices.stream().filter(v -> v.getLabel().equals(label)).findFirst();
        return first.orElse(null);
    }

    // Adding a new Vertex
    private Vertex createVertex(E label) {
        Vertex<E> v = getVertexByLabel(label);
        if (v == null) {
            v = new Vertex<E>(label);
            vertices.add(v);
        }
        return v;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "vCount=" + getVCount() +
                ", eCount=" + getECount() +
                ", edges=" + edges +
                ", vertices=" + vertices +
                '}';
    }

    /**
     * Print the graph in Adjacency List form
     */
    public void printAdjacencyList() {
        System.out.println("No of Vertices : " + this.getVertices().size());
        System.out.println("No of Edges : " + this.getEdges().size());
        this.getVertices().forEach(v -> {
            System.out.print("|" + v.getLabel() + "|");
            v.getAdjacents().forEach(a -> {
                System.out.print("->" + a.getVertex().getLabel() + "(" + a.getWeight() + ")");
            });
            System.out.println();
        });
    }
}
