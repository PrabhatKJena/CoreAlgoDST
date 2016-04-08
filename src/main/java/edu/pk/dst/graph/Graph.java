package edu.pk.dst.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int vCount;
    private int eCount;
    private List<Edge> edges;
    private List<Vertex> vertices;

    public Graph(int v, int e) {
        this.eCount = e;
        this.vCount = v;
        edges = new ArrayList<>(e);
        vertices = new ArrayList<>(v);
    }

    public void addEdge(int src, int dst, int w) {
        edges.add(new Edge(src, dst, w));
    }

    public int getvCount() {
        return vCount;
    }

    public void setvCount(int vCount) {
        this.vCount = vCount;
    }

    public int geteCount() {
        return eCount;
    }

    public void seteCount(int eCount) {
        this.eCount = eCount;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "vCount=" + vCount +
                ", eCount=" + eCount +
                ", edges=" + edges +
                ", vertices=" + vertices +
                '}';
    }
}
