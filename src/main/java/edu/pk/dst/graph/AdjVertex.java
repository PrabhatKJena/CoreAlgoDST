package edu.pk.dst.graph;

public class AdjVertex {

    private Vertex<? extends Comparable> vertex;
    private int weight;

    public AdjVertex(Vertex v, int w) {
        this.vertex = v;
        this.weight = w;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }

    @Override
    public String toString() {
        return "AdjVertex{" +
                "vertex=" + vertex +
                ", weight=" + weight +
                '}';
    }
}
