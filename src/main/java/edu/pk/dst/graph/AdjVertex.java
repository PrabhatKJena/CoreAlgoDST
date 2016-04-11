package edu.pk.dst.graph;

import edu.pk.dst.graph.adt.Vertex;

/**
 * It is a wrapper class of Vertex.
 */
public class AdjVertex<E extends Comparable> {

    private Vertex<E> vertex;
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

    public Vertex<E> getParent() {
        return vertex.getParent();
    }

    public void setParent(Vertex<E> parent) {
        this.vertex.setParent(parent);
    }

    public E getLabel() {
        return vertex.getLabel();
    }

    public void setLabel(E label) {
        this.vertex.setLabel(label);
    }

    public int getRank() {
        return vertex.getRank();
    }

    public void setRank(int rank) {
        this.vertex.setRank(rank);
    }

    public Vertex.State getState() {
        return this.vertex.getState();
    }

    public void setState(Vertex.State state) {
        this.setState(state);
    }
}
